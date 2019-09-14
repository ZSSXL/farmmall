package com.fmall.util;

import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ZSS
 * @date 2019/9/14 10:48
 * @description cookie util
 */
@Slf4j
public class CookieUtil {

    private final static String COOKIE_DOMAIN = ".farmmall.com";
    private final static String COOKIE_NAME = "fmall_login_token";

    /**
     * 读取cookie
     *
     * @param request 请求
     * @return String
     */
    public static String readLoginToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                log.info("cookieName:{}, cookieValue:{}", cookie.getName(), cookie.getValue());
                if (StringUtils.equals(cookie.getName(), COOKIE_NAME)) {
                    log.info("return cookieName:{}, cookieValue:{}", cookie.getName(), cookie.getValue());
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 登录cookie写入
     *
     * @param response 相应
     * @param token    token
     */
    public static void writeLoginToken(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie(COOKIE_NAME, token);
        cookie.setDomain(COOKIE_DOMAIN);
        // 代表设置在根目录
        cookie.setPath("/");
        // 不许使用脚本访问cookie
        cookie.setHttpOnly(true);
        /*
         * 单位是秒
         * 如果这个macAge不设置的话，cookie就不会写入硬盘，而是写入内存，只在当前页面有效
         * 如果是-1，代表永久有效
         */
        cookie.setMaxAge(60 * 60 * 24 * 365);
        log.info("write cookieName:{}, cookieValue:{}", cookie.getName(), cookie.getValue());
        response.addCookie(cookie);
    }

    /**
     * 删除cookie
     *
     * @param request  请求
     * @param response 相应
     */
    public static void delLoginToken(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (StringUtils.equals(cookie.getName(), COOKIE_NAME)) {
                    cookie.setDomain(COOKIE_DOMAIN);
                    cookie.setPath("/");
                    // 将maxAge设置成0, 代表删除这个cookie
                    cookie.setMaxAge(0);
                    log.info("del cookieName:{}, cookieValue:{}", cookie.getName(), cookie.getValue());
                    response.addCookie(cookie);
                    return;
                }
            }
        }
    }


}
