package com.fmall.controller.portal;

import com.fmall.common.Const;
import com.fmall.common.ServerResponse;
import com.fmall.pojo.User;
import com.fmall.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author ZSS
 * @description user sping session controller
 */
@Controller
@RequestMapping("/user/springsession/")
public class UserSpringSessionController {

    private final IUserService iUserService;

    @Autowired
    public UserSpringSessionController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @param session  session
     * @return ServerResponse
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        ServerResponse<User> serverResponse = iUserService.login(username, password);
        if (serverResponse.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, serverResponse.getData());
        }
        return serverResponse;
    }

    /**
     * 用户登出
     *
     * @param session session
     * @return ServerResponse
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
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
     * 查看当前登录用户信息
     *
     * @param session session
     * @return ServerResponse
     */
    @RequestMapping(value = "get_user_info.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
    }

}
