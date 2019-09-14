package com.fmall.controller.common;

import com.fmall.common.Const;
import com.fmall.pojo.User;
import com.fmall.util.CookieUtil;
import com.fmall.util.JsonUtil;
import com.fmall.util.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author ZSS
 * @date 2019/9/14 16:41
 * @description session有效期过滤器
 */
public class SessionExpireFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        if (StringUtils.isEmpty(loginToken)) {
            // 先判断loginToken是否为空或者""
            // 如果不为空，符合条件，继续拿user信息
            String userJsonStr = RedisPoolUtil.get(loginToken);
            User user = JsonUtil.stringToObject(userJsonStr, User.class);
            // 如果user不为空，则重置session的时间，即调用expire命令
            if (user != null) {
                RedisPoolUtil.setEx(loginToken, Const.RedisCacheExtime.REDIS_SESSION_EXTIME);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
