package com.fmall.controller.portal;

import com.fmall.common.Const;
import com.fmall.common.ResponseCode;
import com.fmall.common.ServerResponse;
import com.fmall.pojo.User;
import com.fmall.service.IUserService;
import com.fmall.util.CookieUtil;
import com.fmall.util.JsonUtil;
import com.fmall.util.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author ZSS
 * @description user controller
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    private final IUserService iUserService;

    @Autowired
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @param session  session
     * @param response 相应
     * @return ServerResponse
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session, HttpServletResponse response) {
        ServerResponse<User> serverResponse = iUserService.login(username, password);
        if (serverResponse.isSuccess()) {
            // 登录之后将用户信息存入redis
            CookieUtil.writeLoginToken(response, session.getId());
            RedisPoolUtil.setEx(session.getId(), JsonUtil.objectToString(serverResponse.getData()), Const.RedisCacheExtime.REDIS_SESSION_EXTIME);
        }
        return serverResponse;
    }

    /**
     * 用户登出
     *
     * @param response 响应
     * @param request  请求
     * @return ServerResponse
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse logout(HttpServletRequest request, HttpServletResponse response) {
        String loginToken = CookieUtil.readLoginToken(request);
        CookieUtil.delLoginToken(request, response);
        RedisPoolUtil.del(loginToken);
        return ServerResponse.createBySuccess();
    }

    /**
     * 用户注册
     *
     * @param user 用户实体
     * @return ServerResponse
     */
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse register(User user) {
        if (StringUtils.isBlank(user.getUsername())) {
            return ServerResponse.createByErrorMessage("用户名不能为空");
        } else if (StringUtils.isBlank(user.getPassword())) {
            return ServerResponse.createByErrorMessage("密码不能为空");
        } else if (StringUtils.isBlank(user.getPhone())) {
            return ServerResponse.createByErrorMessage("电话不能为空");
        }
        return iUserService.register(user);
    }

    /**
     * 获取密保问题
     *
     * @param username 用户名
     * @return ServerResponse
     */
    @RequestMapping(value = "forget_get_question.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username) {
        return iUserService.forgetGetQuestion(username);
    }

    /**
     * 校验密保答案
     *
     * @param username 用户名
     * @param question 问题
     * @param answer   答案
     * @return ServerResponse
     */
    @RequestMapping(value = "forget_check_answer.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {
        return iUserService.forgetCheckAnswer(username, question, answer);
    }

    /**
     * 重置密码
     *
     * @param username    用户名
     * @param passwordNew 新密码
     * @return SererResponse
     */
    @RequestMapping(value = "forget_reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetResetQuestion(String username, String passwordNew) {
        return iUserService.forgetResetQuestion(username, passwordNew);
    }

    /**
     * 重置密码
     *
     * @param username    用户名
     * @param passwordOld 旧密码
     * @param passwordNew 新密码
     * @return ServerResponse
     */
    @RequestMapping(value = "reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(String username, String passwordOld, String passwordNew) {
        System.out.println(username + ":" + passwordOld + ":" + passwordNew);
        return iUserService.resetPassword(username, passwordOld, passwordNew);
    }

    @RequestMapping(value = "apply_to_open_shop.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> applyToOpenningShop(HttpSession session, String username, String phone, String shopName) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        // 判断输入的用户名与session中的用户名是否一致
        if (!user.getUsername().equals(username)) {
            return ServerResponse.createByErrorMessage("输入的用户名与当前登录用户的用户名不一致，请重新输入");
        } else if (!user.getPhone().equals(phone)) {
            return ServerResponse.createByErrorMessage("输入的电话与当前登录用户的电话不一致，请重新输入");
        }
        // 如果已经是店家就也不能申请
        if (user.getRole() == Const.Role.ROLE_SELLER) {
            return ServerResponse.createByErrorMessage("你已经是店家");
        }
        // 更新数据库

        user.setShopName(shopName);
        user.setRole(Const.Role.APPLY_OPEN_SHOP);

        return iUserService.applyShop(user);
    }

    /**
     * 通过商品的店家id获取该店家的用户名和店名
     *
     * @param userId userId
     * @return ServerResponse
     */
    @RequestMapping(value = "get_seller_info.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> getSellerInfo(Integer userId) {
        return iUserService.getSellerInfo(userId);
    }

    /**
     * 查看当前登录用户信息
     *
     * @param request 请求
     * @return ServerResponse
     */
    @RequestMapping(value = "get_user_info.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpServletRequest request) {
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObject(userJsonStr, User.class);
        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
    }

}
