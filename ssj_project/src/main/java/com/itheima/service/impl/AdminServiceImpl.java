package com.itheima.service.impl;

import com.itheima.dao.AdminDao;
import com.itheima.domain.Admin;
import com.itheima.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    //验证用户登录
    @Override
    public Admin login(String username, String password) throws Exception {
        return adminDao.findAdminByUsernameAndPassword(username, password);
    }

    //更新admin对象信息
    @Override
    public void updateAdminInfo(Admin admin) throws Exception {
        adminDao.updateAdmin(admin);
    }
}
