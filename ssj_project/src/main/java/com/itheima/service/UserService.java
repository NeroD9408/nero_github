package com.itheima.service;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;

import java.util.List;

public interface UserService {

    //获取表中所有用户数据
    List<UserInfo> findAll() throws Exception;

    //添加用户
    void add(UserInfo user) throws Exception;

    //删除选中用户
    void deleteUser(String[] ids) throws Exception;

    //根据id查询用户
    UserInfo findUserById(String uid) throws Exception;

}
