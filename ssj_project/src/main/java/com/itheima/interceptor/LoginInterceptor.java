package com.itheima.interceptor;

import com.itheima.domain.Admin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //查看当前的登录状态
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (handler instanceof DefaultServletHttpRequestHandler) {
            return true;
        }
        //判断登录用户是否存在
        if (admin == null) {
            //尚未登录,重定向到登录页面
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return false;
        }
        //用户已经登录，可以放行
        return true;
    }
}
