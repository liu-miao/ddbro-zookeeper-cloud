package com.ddbro.security.interceptor;

import com.ddbro.security.config.LoginUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class MyInterceptor implements HandlerInterceptor {


    // 删除当前用户信息
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        LoginUser.remove();
    }
}
