package com.itheima.dao;

import com.itheima.domain.Admin;

public interface AdminDao {
    //根据用户名和密码查找用户
    Admin findAdminByUsernameAndPassword(String username, String password) throws Exception;

    //更新admin信息
    void updateAdmin(Admin admin) throws Exception;
}
