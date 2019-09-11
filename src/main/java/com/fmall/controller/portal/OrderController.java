package com.fmall.controller.portal;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.demo.trade.config.Configs;
import com.fmall.common.Const;
import com.fmall.common.ResponseCode;
import com.fmall.common.ServerResponse;
import com.fmall.pojo.User;
import com.fmall.service.ICartService;
import com.fmall.service.IOrderService;
import com.fmall.vo.OrderItemShippingVo;
import com.fmall.vo.ProductIdAndQuantiry;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author ZSS
 * @description order controller
 */
@Controller
@RequestMapping("/order/")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final IOrderService iOrderService;
    private final ICartService iCartService;

    @Autowired
    public OrderController(IOrderService iOrderService, ICartService iCartService) {
        this.iOrderService = iOrderService;
        this.iCartService = iCartService;
    }

    /**
     * 生成订单
     * @param session session
     * @param productId 商品id
     * @param shippingId 收货地址id
     * @param quantity 数量
     * @return ServerResponse
     */
    @RequestMapping(value = "create.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse create(HttpSession session,Integer productId,Integer shippingId ,Integer quantity){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        // 生成订单
        return iOrderService.createOrder(user.getId(), productId, shippingId, quantity);
    }

    /**
     * 从购物车中创建订单
     *
     * @param session session
     * @param productId 商品id
     * @param shippingId 收货地址id
     * @param quantity 数量
     * @return ServerResponse
     */
    @RequestMapping(value = "create_from_cart.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> createFromCart(HttpSession session,Integer productId,Integer shippingId ,Integer quantity){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        // 生成订单
        ServerResponse<String> serverResponse = iOrderService.createOrder(user.getId(), productId, shippingId, quantity);
        if(!serverResponse.isSuccess()){
            return ServerResponse.createByErrorMessage("下单失败");
        }
        // 删除购物车中的商品 因为下单设定为一定要经过购物车，所以下单后要删除购物车中的商品
        ServerResponse<String> serverResponse1 = iCartService.deleteCartByUserIdProductIdQuantity(user.getId(),productId,quantity);
        if(!serverResponse1.isSuccess()){
            return serverResponse1;
        }
        return serverResponse1;
    }


    /**
     * 将购物车中已勾选的商品下单
     * @param session session
     * @param shippingId 收货地址id
     * @return ServerResponse
     */
    @RequestMapping(value = "create_order_from_cart.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> createOrderByCheckedFromCart(HttpSession session,Integer shippingId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        // 1、查出购物车中已勾选的商品的productId,quantity
        List<ProductIdAndQuantiry> productIdAndQuantiryList = iOrderService.selectCheckedProductFromCart(user.getId());
        if(productIdAndQuantiryList == null){
            return ServerResponse.createByErrorMessage("当前购物车中没有被选中的商品");
        }
        // 2、一一下单
        for(ProductIdAndQuantiry productIdAndQuantiry : productIdAndQuantiryList){
            ServerResponse<String> serverResponse = iOrderService.createOrder(user.getId(), productIdAndQuantiry.getProductId(), shippingId, productIdAndQuantiry.getQuantity());
            if(!serverResponse.isSuccess()){
                return ServerResponse.createByErrorMessage("下单失败了");
            }
        }
        // 下单后删除购物车中的商品
        List<Integer> checkedCartList = iOrderService.selectCheckedCarIdByUserId(user.getId());
        for(Integer checkedItem : checkedCartList){
            // 通过id删除购物车中的商品
            int resultCount = iOrderService.deleteCartById(checkedItem);
            if(resultCount == 0){
                return ServerResponse.createByErrorMessage("删除购物车商品这里出了问题");
            }
        }
        return ServerResponse.createBySuccessMessage("下单成功");
    }

    /**
     * 删除订单
     * @param session session
     * @param orderNo 订单编号
     * @return ServerResponse
     */
    @RequestMapping(value = "delete.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delete(HttpSession session,Long orderNo){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iOrderService.deleteOrder(user.getId(), orderNo);
}

    /**
     * 卖家单方面删除订单
     * @param orderNo 订单编号
     * @return ServerResponse
     */
    @RequestMapping(value = "delete_by_seller.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> deleteBySeller(Long orderNo){
        return iOrderService.deleteOrderBySeller(orderNo);
    }

    /**
     * 展示个人的订单,根据不同的选择，默认查询全部
     * @param session session
     * @param pn 当前页
     * @param status 状态
     * @return ServerResponse
     */
    @RequestMapping(value = "show_order.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> showOrder(HttpSession session,@RequestParam(value = "pn",defaultValue = "1") Integer pn
            ,@RequestParam(value = "status",defaultValue = "0") Integer status){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iOrderService.showOrder(user.getId(), pn,status);
    }

    /**
     * 支付接口
     * @param session session
     * @param request 请求
     * @param orderNo 订单编号
     * @return ServerResponse
     */
    @RequestMapping(value = "pay.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse pay(HttpSession session, HttpServletRequest request,Long orderNo){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        String path = request.getSession().getServletContext().getRealPath("upload");
        // 更新order表的收货地址
        // 更新订单状态
        return iOrderService.pay(user.getId(), orderNo,path);
    }

    @RequestMapping("alipay_callback.do")
    @ResponseBody
    public Object alipayCallback(HttpServletRequest request){
        Map<String,String> params = Maps.newHashMap();
        Map requestParams = request.getParameterMap();
        for(Iterator iter = requestParams.keySet().iterator();iter.hasNext();){
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for(int i = 0;i < values.length;i++){
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name,valueStr);
        }
        System.out.println("支付宝回调,参数："+params);
        logger.info("支付宝回调，sign:{},trade_status:{},参数:{}",params.get("sign"),params.get("trade_status"),params.toString());

        // Important 验证回调的重要性，是不是支付宝发的，并且还有避免重复通知。
        params.remove("sign_type");
        try {
            boolean alipayRsaCheckedV2 = AlipaySignature.rsaCheckV2(params, Configs.getAlipayPublicKey(),"utf-8",Configs.getSignType());
            if(!alipayRsaCheckedV2){
                return ServerResponse.createByErrorMessage("非法请求，验证不通过，再恶意请求就找网警了");
            }
        } catch (AlipayApiException e) {
            logger.error("支付宝回调异常：",e);
            e.printStackTrace();
        }
        // todo 验证各种数据

        ServerResponse serverResponse = iOrderService.alipayCallback(params);
        if(serverResponse.isSuccess()){
            return Const.AlipayCallback.RESPONSE_SUCCESS;
        }
        return Const.AlipayCallback.RESPONSE_FAILED;
    }

    @RequestMapping(value = "query_order_pay_status.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Boolean> queryOrderPayStatus(HttpSession session, Long orderNo){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        ServerResponse serverResponse = iOrderService.queryOrderPayStatus(user.getId(), orderNo);
        if(serverResponse.isSuccess()){
            return ServerResponse.createBySuccess(true);
        }
        return ServerResponse.createBySuccess(false);
    }

    /**
     * 查询订单详情
     * @param session session
     * @param orderNo 订单编号
     * @return ServerResponse
     */
    @RequestMapping(value = "show_order_detail.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> showOrderDetail(HttpSession session, Long orderNo){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"请先登录");
        }
        // 1、去查询
        OrderItemShippingVo orderItemShippingVo = iOrderService.selectOrderDetail(user.getId(), orderNo);
        System.out.println(orderItemShippingVo);
        if(orderItemShippingVo == null){
            return ServerResponse.createByErrorMessage("杀一个程序猿猴祭天");
        }
        session.setAttribute(Const.CURRENT_ORDER_DETAIL,orderItemShippingVo);
        return ServerResponse.createBySuccess();
    }


    /** ================================================================================
        ----------------------------- 卖家查询订单 --------------------------------------
        ================================================================================  **/

    @RequestMapping(value = "select_order_seller_id_type.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> selectOrderBySellerIdAndStatus(HttpSession session
            ,@RequestParam(value = "status",defaultValue = "0") Integer status
            ,@RequestParam(value = "pn",defaultValue = "1") Integer pn){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        if(user.getRole() != 1){
            return ServerResponse.createByErrorMessage("你不是卖家的身份");
        }
        // todo 查询除购买了该卖家商品的所有订单
        //IOrderService
        return iOrderService.selectOrderBySellerIdAndStatus(user.getId(), status, pn);
    }

    @RequestMapping(value = "send_order.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> sendOrder(Long orderNo,Integer boxId){
        return iOrderService.sendOrder(orderNo,boxId);
    }

}
