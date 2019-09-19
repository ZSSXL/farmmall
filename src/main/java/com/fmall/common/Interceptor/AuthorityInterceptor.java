package com.fmall.common.Interceptor;

import com.fmall.common.Const;
import com.fmall.common.ServerResponse;
import com.fmall.pojo.User;
import com.fmall.util.CookieUtil;
import com.fmall.util.JsonUtil;
import com.fmall.util.ShardedRedisPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * @author ZSS
 * @date 2019/9/18 10:30
 * @description 权限拦截器拦截
 */
@Slf4j
public class AuthorityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 请求中Controller中的方法名
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        // 解析HandlerMethod
        String methodName = handlerMethod.getMethod().getName();
        String className = handlerMethod.getBean().getClass().getSimpleName();

        // 解析参数，具体的参数key以及value是什么
        StringBuffer requestParamBuffer = new StringBuffer();
        Map paramMap = request.getParameterMap();
        for (Object o : paramMap.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            String mapKey = (String) entry.getKey();

            String mapValue = StringUtils.EMPTY;
            // request这个参数的map，里面的value返回的是一个String[]
            Object obj = entry.getValue();
            if (obj instanceof String[]) {
                String[] strs = (String[]) obj;
                mapValue = Arrays.toString(strs);
            }
            requestParamBuffer.append(mapKey).append("=").append(mapValue);
        }
        log.info("requestParamBuffer: {}", requestParamBuffer);
        User user = null;
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isNotBlank(loginToken)) {
            String userJsonStr = ShardedRedisPoolUtil.get(loginToken);
            user = JsonUtil.stringToObject(userJsonStr, User.class);
        }
        if (user == null || (user.getRole() != Const.Role.ROLE_ADMIN)) {
            // 返回false，即不会调用controller中的方法
            // 添加reset，否者报异常
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");

            PrintWriter out = response.getWriter();
            if (user == null) {
                out.print(JsonUtil.objectToString(ServerResponse.createByErrorMessage("拦截器拦截，用户未登录")));
            } else {
                out.print(JsonUtil.objectToString(ServerResponse.createByErrorMessage("拦截器拦截，权限操作")));
            }
            out.flush();
            // 关闭
            out.close();
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}


