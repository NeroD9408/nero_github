package com.itheima.service;

import com.itheima.domain.Admin;

public interface AdminService {
    //验证用户登录
    Admin login(String username, String password) throws Exception;

    //更新admin对象信息
    void updateAdminInfo(Admin admin) throws Exception;
}
