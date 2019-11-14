package com.itheima.interceptor;

import com.itheima.dao.AdminDao;
import com.itheima.domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AutoLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private AdminDao adminDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof DefaultServletHttpRequestHandler) {
            return true;
        }
        //获取登录状态
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin == null) {
            //用户尚未登录
            //获取cookies
            Cookie[] cookies = request.getCookies();
            //查询cookies中是否存在自动登录的cookie
            Cookie autoLogin = getCookie(cookies, "autoLogin");
            if (autoLogin != null) {
                //有对应的cookie，接着去检查用户名密码是否正确，防止用户更改了账号密码
                String value = autoLogin.getValue();
                String[] values = value.split("@");
                Admin findAdmin = adminDao.findAdminByUsernameAndPassword(values[0], values[1]);
                if (findAdmin != null) {
                    //用户名密码也正确，在session中添加登录状态
                    request.getSession().setAttribute("admin", findAdmin);
                }
            }
        }
        //用户已经登录，放行
        return true;
    }

    private Cookie getCookie(Cookie[] cookies, String cookieName) {
        for (Cookie cookie : cookies) {
            if (cookieName.equals(cookie.getName())) {
                return cookie;
            }
        }
        return null;
    }
}
