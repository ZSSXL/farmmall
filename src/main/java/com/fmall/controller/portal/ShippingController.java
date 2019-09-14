package com.fmall.controller.portal;

import com.fmall.common.Const;
import com.fmall.common.ResponseCode;
import com.fmall.common.ServerResponse;
import com.fmall.pojo.Shipping;
import com.fmall.pojo.User;
import com.fmall.service.IShippingService;
import com.fmall.util.CookieUtil;
import com.fmall.util.JsonUtil;
import com.fmall.util.RedisPoolUtil;
import com.github.pagehelper.PageInfo;
import com.google.api.Http;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author ZSS
 * @description shipping controller
 */
@Controller
@RequestMapping("/shipping/")
public class ShippingController {

    private final IShippingService iShippingService;

    @Autowired
    public ShippingController(IShippingService iShippingService) {
        this.iShippingService = iShippingService;
    }

    /**
     * 添加收获地址
     *
     * @param request  请求
     * @param shipping 收货地址实体
     * @return ServerResponse
     */
    @RequestMapping(value = "add_shipping.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> addShipping(HttpServletRequest request, Shipping shipping) {
        String loginToken = getLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObject(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请登录用户");
        }
        shipping.setUserId(user.getId());
        return iShippingService.addAddress(shipping);
    }

    /**
     * 展示个人收获地址
     *
     * @param request 请求
     * @param pn      当前页
     * @return ServerResponse
     */
    @RequestMapping(value = "show_shipping.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> showShippings(HttpServletRequest request, @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        String loginToken = getLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObject(userJsonStr, User.class);
        if (user != null) {
            return iShippingService.showAddresses(user.getId(), pn);
        } else {
            return ServerResponse.createByError();
        }
    }

    /**
     * 展示个人收获地址
     *
     * @param request    请求
     * @param shippingId 收货地址id
     * @return ServerResponse
     */
    @RequestMapping(value = "delete_shipping.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> deleteShipping(HttpServletRequest request, Integer shippingId) {
        String loginToken = getLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObject(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        return iShippingService.deleteShipping(user.getId(), shippingId);
    }

    /**
     * 通过id展示收获地址
     *
     * @param request    请求
     * @param shippingId shippindId
     * @return ServerResponse
     */
    @RequestMapping(value = "show_shipping_by_id.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<Shipping> showShippingById(HttpServletRequest request, Integer shippingId) {
        String loginToken = getLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObject(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        } else {
            return iShippingService.selectShippingById(shippingId);
        }

    }

    /**
     * 修改收获地址
     *
     * @param request  请求
     * @param shipping 收货地址实体
     * @return ServerResponse
     */
    @RequestMapping(value = "edit_shipping.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse editShipping(HttpServletRequest request, Shipping shipping) {
        String loginToken = getLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObject(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByError();
        }
        if (shipping != null) {
            shipping.setUserId(user.getId());
            return iShippingService.editShipping(shipping);
        }
        return ServerResponse.createByErrorMessage("出错啦！！！找程序员");
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
