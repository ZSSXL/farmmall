package com.fmall.service.impl;

import com.alipay.api.AlipayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.utils.ZxingUtils;
import com.fmall.common.Const;
import com.fmall.common.ServerResponse;
import com.fmall.dao.*;
import com.fmall.pojo.Order;
import com.fmall.pojo.OrderItem;
import com.fmall.pojo.PayInfo;
import com.fmall.pojo.Product;
import com.fmall.service.IOrderService;
import com.fmall.util.BigDecimalUtil;
import com.fmall.util.DateFormat;
import com.fmall.util.FTPUtil;
import com.fmall.util.PropertiesUtil;
import com.fmall.vo.OrderItemShippingVo;
import com.fmall.vo.OrderItemUserVo;
import com.fmall.vo.OrderItemVo;
import com.fmall.vo.ProductIdAndQuantiry;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service("iOrderService")
public class OrderServiceImpl implements IOrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final ProductMapper productMapper;
    private final OrderItemMapper orderItemMapper;
    private final OrderMapper orderMapper;
    private final CartMapper cartMapper;
    private final PayInfoMapper payInfoMapper;

    @Autowired
    public OrderServiceImpl(ProductMapper productMapper, OrderItemMapper orderItemMapper, OrderMapper orderMapper, CartMapper cartMapper, PayInfoMapper payInfoMapper) {
        this.productMapper = productMapper;
        this.orderItemMapper = orderItemMapper;
        this.orderMapper = orderMapper;
        this.cartMapper = cartMapper;
        this.payInfoMapper = payInfoMapper;
    }

    @Override
    public ServerResponse<String> createOrder(Integer userId, Integer productId, Integer shippingId, Integer quantity){
        // 1、查询是否有这个商品
        // 2、查询是否库存足够
        int resultCount = productMapper.selectByProductId(productId);
        // 如果买的数量大于库存
        if(resultCount < quantity){
            return ServerResponse.createByErrorMessage("商品库存不足");
        }
        // 3、从库存中减少相应的数量
        resultCount = productMapper.updateStock(productId, resultCount - quantity);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("更新库存失败");
        }
        // 4、通过productId查询出该商品的name,mainImage,price
        Product product = productMapper.selectMsg(productId);

        // 创建一个订单详情表对象
        OrderItem orderItem = new OrderItem();
        // 5、生成订单号
        orderItem.setOrderNo(generateOrderNo());
        orderItem.setUserId(userId);
        orderItem.setProductName(product.getName());
        orderItem.setProductId(productId);
        orderItem.setProductImage(product.getMainImage());
        orderItem.setCurrentUnitPrice(product.getPrice());
        orderItem.setQuantity(quantity);
        orderItem.setTotalPrice(BigDecimalUtil.mul(product.getPrice().doubleValue(),quantity));

        // 6、将订单详情表存到数据库
        int result = orderItemMapper.insert(orderItem);
        if(result == 0){
            return ServerResponse.createByErrorMessage("提交订单详情失败");
        }

        // 生成订单
        Order order = new Order();
        order.setOrderNo(orderItem.getOrderNo());
        order.setUserId(userId);
        order.setShippingId(shippingId);
        order.setPayment(orderItem.getTotalPrice());
        order.setPaymentType(Const.Order.PAYMENT_TYPE);
        order.setPostage(0);
        order.setStatus(Const.Order.ORDER_UNPAY);
        int insert = orderMapper.insert(order);
        if(insert == 0){
            return ServerResponse.createByErrorMessage("提交订单失败");
        }
        return ServerResponse.createBySuccess("生成订单成功");
    }

    @Override
    public ServerResponse<String> deleteOrder(Integer userId,Long orderNo){
        int resultCount = orderMapper.deleteOrder(userId, orderNo);
        resultCount =resultCount + orderItemMapper.deleteOrderItem(userId, orderNo);
        if(resultCount > 1){
            return ServerResponse.createBySuccessMessage("删除订单成功");
        }
        return ServerResponse.createByErrorMessage("删除订单失败");
    }

    @Override
    public ServerResponse<PageInfo> showOrder(Integer userId,Integer pn,Integer status){
        PageHelper.startPage(pn,20);
        List<OrderItemVo> orderItemVoList =  orderMapper.showOrdersByUserId(userId,status);
        if(orderItemVoList == null){
            return ServerResponse.createByErrorMessage("查询失败");
        }
        PageInfo pageInfo = new PageInfo(orderItemVoList);
        return ServerResponse.createBySuccess("查询成功",pageInfo);
    }

    @Override
    public ServerResponse pay(Integer userId,Long orderNo,String path){
        Map<String,String> resultMap = Maps.newHashMap();
        // 1、查看是否有这个订单
        Order order = orderMapper.selectExist(userId, orderNo);
        if(order == null){
            return ServerResponse.createByErrorMessage("没有这个订单，找程序员解决，出BUG了");
        }
        resultMap.put("orderNo", String.valueOf(order.getOrderNo()));
        // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
        // 需保证商户系统端不能重复，建议通过数据库sequence生成，
        String outTradeNo = String.valueOf(order.getOrderNo());
        // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
        String subject = new StringBuilder().append("Farmmall扫码支付，订单号：").append(outTradeNo).toString();
        // (必填) 订单总金额，单位为元，不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
        String totalAmount = order.getPayment().toString();
        // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
        // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
        String undiscountableAmount = "0";
        // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        String sellerId = "";
        // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
        String body = new StringBuilder().append("订单").append(outTradeNo).append("购买商品共").append(totalAmount).append("元").toString();
        // 商户操作员编号，添加此参数可以为商户操作员做销售统计
        String operatorId = "test_operator_id";
        // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        String storeId = "test_store_id";
        // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088100200300400500");
        // 支付超时，定义为120分钟
        String timeoutExpress = "120m";
        // 商品明细列表，需填写购买商品详细信息，
        List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
        List<OrderItem> orderItemList = orderItemMapper.getByOrderNoUserId(userId,orderNo);
        for (OrderItem orderItem:orderItemList){
            GoodsDetail goods = GoodsDetail.newInstance(String.valueOf(orderItem.getProductId()), orderItem.getProductName(),
                    BigDecimalUtil.mul(orderItem.getCurrentUnitPrice().doubleValue(), 100d).longValue(), orderItem.getQuantity());
            goodsDetailList.add(goods);

        }
        // 创建扫码支付请求builder，设置请求参数
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                .setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo)
                .setUndiscountableAmount(undiscountableAmount).setSellerId(sellerId).setBody(body)
                .setOperatorId(operatorId).setStoreId(storeId).setExtendParams(extendParams)
                .setTimeoutExpress(timeoutExpress)
                .setNotifyUrl(PropertiesUtil.getProperty("alipay.callback.url"))//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
                .setGoodsDetailList(goodsDetailList);


        /* 一定要在创建AlipayTradeService之前调用Configs.init()设置默认参数
         *  Configs会读取classpath下的zfbinfo.properties文件配置信息，如果找不到该文件则确认该文件是否在classpath目录
         */
        Configs.init("zfbinfo.properties");

        /* 使用Configs提供的默认参数
         *  AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
         */
        AlipayTradeService tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();

        int resultCount = orderMapper.checkedShippingId(orderNo);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("该订单没有绑定收货地址，请删除该订单重新下单");
        }

        AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                logger.info("支付宝预下单成功: !!!");
                AlipayTradePrecreateResponse response = result.getResponse();
                dumpResponse(response);

                File folder = new File(path);
                if(folder.exists()){
                    folder.setWritable(true);
                    folder.mkdirs();
                }

                // 需要修改为运行机器上的路径
                // 细节
                String qrPath = String.format(path+"/qr-%s.png", response.getOutTradeNo());
                String qrFileName = String.format("qr-%s.png",response.getOutTradeNo());
                File qrCodeImge = ZxingUtils.getQRCodeImge(response.getQrCode(), 256, qrPath);

                File targetFile = new File(path,qrFileName);
                try {
                    FTPUtil.uploadFile(Lists.newArrayList(targetFile));
                } catch (IOException e) {
                    logger.error("上传二维码异常",e);
                    e.printStackTrace();
                }
                logger.info("qrPath:" + qrPath);
                String qrUrl = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFile.getName();
                resultMap.put("qrUrl",qrUrl);
                return ServerResponse.createBySuccess(resultMap);
            case FAILED:
                logger.error("支付宝预下单失败!!!");
                return ServerResponse.createByErrorMessage("支付宝预下单失败!!!");
            case UNKNOWN:
                logger.error("系统异常，预下单状态未知!!!");
                return ServerResponse.createByErrorMessage("系统异常，预下单状态未知!!!");
            default:
                logger.error("不支持的交易状态，交易返回异常!!!");
                return ServerResponse.createByErrorMessage("不支持的交易状态，交易返回异常!!!");
        }
    }

    @Override
    public ServerResponse alipayCallback(Map<String,String> params){
        Long orderNo = Long.valueOf(params.get("out_trade_no"));
        String tradeNo = params.get("trade_no");
        String tradeStatus = params.get("trade_status");
        Order order = orderMapper.selectByOrderNo(orderNo);
        if(order == null){
            return ServerResponse.createByErrorMessage("非本商城的订单，回调忽略");
        }
        if(order.getStatus() >= Const.OrderStatusEnum.PAID.getCode()){
            return ServerResponse.createBySuccess("支付宝重复调用");
        }
        if((Const.AlipayCallback.TRADE_STATUS_TRADE_SUCCESS).equals(tradeStatus)){
            order.setStatus(Const.OrderStatusEnum.PAID.getCode());
            order.setPaymentTime(DateFormat.strToDate(params.get("gmt_payment")));
            orderMapper.updateByPrimaryKeySelective(order);
        }
        PayInfo payInfo = new PayInfo();
        payInfo.setUserId(order.getUserId());
        payInfo.setOrderNo(order.getOrderNo());
        payInfo.setPayPlatform(Const.PayPlatFornEnum.ALIPAY.getCode());
        payInfo.setPlatformNumber(tradeNo);
        payInfo.setPlatformStatus(tradeStatus);

        payInfoMapper.insert(payInfo);
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse queryOrderPayStatus(Integer userId,Long orderNo){
        Order order = orderMapper.selectExist(userId, orderNo);
        if(order == null){
            return ServerResponse.createByErrorMessage("没有该订单");
        }
        if(order.getStatus() >= Const.OrderStatusEnum.PAID.getCode()){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }

    /**
     * 简单打印应答
     * @param response response
     */
    private void dumpResponse(AlipayResponse response) {
        if (response != null) {
            logger.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
            if (StringUtils.isNotEmpty(response.getSubCode())) {
                logger.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(),
                        response.getSubMsg()));
            }
            logger.info("body:" + response.getBody());
        }
    }

    /**
     * 生成订单号
     * @return long
     */
    private long generateOrderNo(){
        long currentTime = System.currentTimeMillis();
        return currentTime+new Random().nextInt(100);
    }

    @Override
    public List<ProductIdAndQuantiry> selectCheckedProductFromCart(Integer userId){
        return cartMapper.selectProductIdQuantiyByUserId(userId);
    }

    @Override
    public List<Integer> selectCheckedCarIdByUserId(Integer userId){
        return  cartMapper.selectCheckedCartIdByUserId(userId);
    }

    @Override
    public int deleteCartById(Integer cartId) {
        return cartMapper.deleteByPrimaryKey(cartId);
    }

    @Override
    public ServerResponse<PageInfo> selectOrderBySellerIdAndStatus(Integer buyyerId,Integer status,Integer pn){
        PageHelper.startPage(pn,20);
        // 1、查询
        List<OrderItemUserVo> orderItemUserVoList = orderItemMapper.selectOrderBySellerIdAndStatus(buyyerId,status);
        if(orderItemUserVoList == null){
            return ServerResponse.createByErrorMessage("你这家店铺可以倒闭了,因为没有人下单");
        }
        PageInfo pageInfo = new PageInfo(orderItemUserVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<String> deleteOrderBySeller(Long orderNo) {
        int resultCount = orderMapper.deleteOrderBySeller(orderNo);
        resultCount =resultCount + orderItemMapper.deleteOrderItemBySeller(orderNo);
        if(resultCount > 1){
            return ServerResponse.createBySuccessMessage("单方面删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public OrderItemShippingVo selectOrderDetail(Integer userId, Long orderNo){
        return orderItemMapper.selectByOrderNo(userId, orderNo);
    }

    @Override
    public ServerResponse<String> sendOrder(Long orderNo,Integer boxId){
        int resultCount = orderMapper.updateBoxIdAndStatusByOrderNo(orderNo,boxId,Const.Order.ORDER_SENDED);
        if(resultCount == 0){
            return  ServerResponse.createByErrorMessage("发货失败");
        }
        return ServerResponse.createBySuccessMessage("发货成功");
    }

    @Override
    public int queryByOrderNo(Long orderNo) {
        return orderItemMapper.queryByOrderNo(orderNo);
    }

    @Override
    public List<Order> queryAllOrders(int page, int size) {
        PageHelper.startPage(page,size);
        return orderMapper.queryAllOrders();
    }

    @Override
    public List<Order> queryOrdersByOrderNo(int page, int size, Long orderNo) {
        PageHelper.startPage(page,size);
        return orderMapper.queryOrdersByOrderNo(orderNo);
    }

    @Override
    public void deleteOrderById(String id) {
        orderMapper.deleteOrderById(id);
    }
}
