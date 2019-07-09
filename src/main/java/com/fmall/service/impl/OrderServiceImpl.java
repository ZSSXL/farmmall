package com.fmall.service.impl;

import com.fmall.common.Const;
import com.fmall.common.ServerResponse;
import com.fmall.dao.CartMapper;
import com.fmall.dao.OrderItemMapper;
import com.fmall.dao.OrderMapper;
import com.fmall.dao.ProductMapper;
import com.fmall.pojo.Order;
import com.fmall.pojo.OrderItem;
import com.fmall.pojo.Product;
import com.fmall.service.IOrderService;
import com.fmall.util.BigDecimalUtil;
import com.fmall.vo.OrderItemShippingVo;
import com.fmall.vo.OrderItemUserVo;
import com.fmall.vo.OrderItemVo;
import com.fmall.vo.ProductIdAndQuantiry;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service("iOrderService")
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CartMapper cartMapper;

    public ServerResponse<String> createOrder(Integer userId,Integer productId,Integer shippingId,Integer quantity){
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

    public ServerResponse<String> deleteOrder(Integer userId,Long orderNo){
        int resultCount = orderMapper.deleteOrder(userId, orderNo);
        resultCount =resultCount + orderItemMapper.deleteOrderItem(userId, orderNo);
        if(resultCount > 1){
            return ServerResponse.createBySuccessMessage("删除订单成功");
        }
        return ServerResponse.createByErrorMessage("删除订单失败");
    }

    public ServerResponse<PageInfo> showOrder(Integer userId,Integer pn,Integer status){
        PageHelper.startPage(pn,20);
        List<OrderItemVo> orderItemVoList =  orderMapper.showOrdersByUserId(userId,status);
        if(orderItemVoList == null){
            return ServerResponse.createByErrorMessage("查询失败");
        }
        PageInfo pageInfo = new PageInfo(orderItemVoList);
        return ServerResponse.createBySuccess("查询成功",pageInfo);
    }

    public ServerResponse<String> pay(Integer userId,Long orderNo){
        // 1、查看是否有这个订单
        Integer resultCount = orderMapper.selectExist(userId,orderNo);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("没有这个订单，找程序员解决，出BUG了");
        }
        // 2、绑定收货地址
        resultCount = orderMapper.checkedShippingId(orderNo);
        if(resultCount == null){
            return ServerResponse.createByErrorMessage("该订单没有绑定收货地址，请删除该订单重新下单");
        }

        // todo 完成与支付宝的对接

        // 3、更新订单状态
        resultCount = orderMapper.updateStatusByOrderNo(Const.Order.ORDER_PAYED,orderNo);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("付款失败");
        }

        return ServerResponse.createBySuccessMessage("付款成功");
    }

    // 生成订单号
    private long generateOrderNo(){
        long currentTime = System.currentTimeMillis();
        return currentTime+new Random().nextInt(100);
    }

    public List<ProductIdAndQuantiry> selectCheckedProductFromCart(Integer userId){
        List<ProductIdAndQuantiry> productIdAndQuantiryList = cartMapper.selectProductIdQuantiyByUserId(userId);
        return productIdAndQuantiryList;
    }

    public List<Integer> selectCheckedCarIdByUserId(Integer userId){
        List<Integer> cartList = cartMapper.selectCheckedCartIdByUserId(userId);
        return cartList;
    }

    @Override
    public int deleteCartById(Integer cartId) {
        int resultCount = cartMapper.deleteByPrimaryKey(cartId);
        return resultCount;
    }

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

    public OrderItemShippingVo selectOrderDetail(Integer userId, Long orderNo){
        OrderItemShippingVo orderItemShippingVo = orderItemMapper.selectByOrderNo(userId, orderNo);
        return orderItemShippingVo;
    }

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
