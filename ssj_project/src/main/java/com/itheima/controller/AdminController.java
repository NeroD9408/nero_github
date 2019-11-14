package com.itheima.controller;

import com.itheima.domain.Admin;
import com.itheima.service.AdminService;
import com.itheima.utils.UUIDUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/root")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session, String rememberMe, HttpServletResponse response) throws Exception {
        //调用service查询输入的账号密码是否存在
        Admin admin = adminService.login(username, password);
        //判断返回的用户信息是否为空
        if (admin == null) {
            //说明不存在该用户
            return "redirect:/login.jsp";
        }
        //登录成功，将该用户信息存放到session域中
        session.setAttribute("admin", admin);
        //查看用户是否勾选自动登录
        if (rememberMe != null && "autoLogin".equals(rememberMe)) {
            //已经勾选，则需要另外创建cookie
            Cookie cookie = new Cookie("autoLogin", admin.getUsername() + "@" + admin.getPassword());
            cookie.setMaxAge(7 * 24 * 60 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return "redirect:/index.jsp";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        //退出，清除session及cookie
        Cookie cookie = new Cookie("autoLogin", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        session.invalidate();
        return "redirect:/login.jsp";
    }

    @RequestMapping("/updateInfo")
    public String updateInfo(HttpSession session, String username, MultipartFile headImg) throws Exception {
        //接收用户传入的图片
        //获取图片名称
        System.out.println(username);
        System.out.println(headImg);
        String originalFilename = headImg.getOriginalFilename();
        String fileName = UUIDUtil.getUuid() + originalFilename;
        String path = "http://localhost:8088/imgServer/imgs/headImg/";
        //将图片地址赋值给当前登录用户
        Admin admin = (Admin) session.getAttribute("admin");
        admin.setImgpath(path + fileName);
        //调用service对数据库中的信息进行修改
        adminService.updateAdminInfo(admin);
        Client client = new Client();
        WebResource resource = client.resource(path + fileName);
        resource.put(headImg.getBytes());
        return "redirect:/index.jsp";
    }
}
