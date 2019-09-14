package com.fmall.controller.portal;

import com.fmall.common.Const;
import com.fmall.common.ResponseCode;
import com.fmall.common.ServerResponse;
import com.fmall.pojo.Cart;
import com.fmall.pojo.User;
import com.fmall.service.ICartService;
import com.fmall.util.CookieUtil;
import com.fmall.util.JsonUtil;
import com.fmall.util.RedisPoolUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZSS
 * @description cart controller
 */
@Controller
@RequestMapping("/cart/")
public class CartController {

    private final ICartService iCartService;

    @Autowired
    public CartController(ICartService iCartService) {
        this.iCartService = iCartService;
    }

    /**
     * 展示购物车
     *
     * @param pn 当前页
     * @return ServerResponse
     */
    @RequestMapping(value = "show_cart.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> showCart(HttpServletRequest request, @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        String loginToken = getLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObject(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByErrorMessage("请先登录");
        }
        return iCartService.showCarts(user.getId(), pn);
    }

    /**
     * 添加购物车
     *
     * @param request   session
     * @param quantity  数量
     * @param productId 产品id
     * @return ServerResponse
     */
    @RequestMapping(value = "add_cart.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> addCart(HttpServletRequest request, Integer quantity, Integer productId) {
        String loginToken = getLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObject(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        Cart cart = new Cart();
        cart.setUserId(user.getId());
        cart.setProductId(productId);
        cart.setQuantity(quantity);
        // 默认已勾选
        cart.setChecked(Const.Check.CHECKED);
        return iCartService.addCart(cart);
    }

    /**
     * 添加userId双向验证，防止横向越权
     *
     * @param request request
     * @param cartId  购物车id
     * @return ServerResponse
     */
    @RequestMapping(value = "delete_cart_by_cartId.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> deleteCartByCartId(HttpServletRequest request, Integer cartId) {
        String loginToken = getLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObject(userJsonStr, User.class);
        if (user != null) {
            return iCartService.deleteCartByUserIdCartId(user.getId(), cartId);
        } else {
            return ServerResponse.createByError();
        }

    }

    /**
     * 修改购物车商品信息
     *
     * @param quantity 数量
     * @param cartId   购物车id
     * @param request  request
     * @param checked  勾选
     * @return ServerResponse
     */
    @RequestMapping(value = "edit_cart.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> editCart(HttpServletRequest request, Integer cartId, Integer quantity, Integer checked) {
        String loginToken = getLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObject(userJsonStr, User.class);
        if (user != null) {
            Cart cart = new Cart();
            cart.setUserId(user.getId());
            cart.setId(cartId);
            cart.setQuantity(quantity);
            cart.setChecked(checked);
            return iCartService.editCart(cart);
        } else {
            return ServerResponse.createByError();
        }
    }

    /**
     * 选择修改订单状态
     *
     * @param request 请求
     * @param cartId  购物车id
     * @param type    类型
     * @return ServerResponse
     */
    @RequestMapping("select_checked.do")
    @ResponseBody
    public ServerResponse<String> selectChecked(HttpServletRequest request, Integer cartId, Integer type) {
        String loginToken = getLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObject(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请登录");
        }
        return iCartService.select(user.getId(), cartId, type);
    }

    /**
     * 修改所有购物车订单状态
     *
     * @param request 请求
     * @param checked 勾选
     * @return ServerResponse
     */
    @RequestMapping(value = "select_check_all.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> selectCheckedAll(HttpServletRequest request, Integer checked) {
        String loginToken = getLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObject(userJsonStr, User.class);
        if (user != null) {
            return iCartService.updateAllChecked(user.getId(), checked);
        } else {
            return ServerResponse.createByError();
        }
    }

    /**
     * 购物车商品加一
     *
     * @param request 请求
     * @param cartId  购物车id
     * @return ServerResponse
     */
    @RequestMapping(value = "add_quantity.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addQuantity(HttpServletRequest request, Integer cartId) {
        String loginToken = getLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObject(userJsonStr, User.class);
        if (user != null) {
            return iCartService.addQuantity(user.getId(), cartId);
        } else {
            return ServerResponse.createByError();
        }
    }

    /**
     * 计算已选中的商品的总价钱
     *
     * @param request 请求
     * @return ServerResponse
     */
    @RequestMapping(value = "calcu_total_price.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> calcuTotalPrice(HttpServletRequest request) {
        String loginToken = getLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObject(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请登录");
        }
        // 计算总价钱
        return iCartService.calcuTotalPrice(user.getId());
    }

    /**
     * 购物车商品减一
     *
     * @param request 请求
     * @param cartId  购物车id
     * @return ServerResponse
     */
    @RequestMapping(value = "dec_quantity.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse decQuantity(HttpServletRequest request, Integer cartId) {
        String loginToken = getLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObject(userJsonStr, User.class);
        if (user != null) {
            return iCartService.decQuantity(user.getId(), cartId);
        } else {
            return ServerResponse.createByError();
        }
    }

    /**
     * 获取token
     *
     * @param request 请求
     * @return String
     */
    private String getLoginToken(HttpServletRequest request) {
        return CookieUtil.readLoginToken(request);
    }

}
