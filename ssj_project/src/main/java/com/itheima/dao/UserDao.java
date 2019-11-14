package com.itheima.dao;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;

import java.util.List;

public interface UserDao {

    //获取表中所有用户数据
    List<UserInfo> findAll() throws Exception;

    //添加用户
    void add(UserInfo user) throws Exception;

    //根据id删除用户
    void deleteUser(Object[] ids) throws Exception;

    //根据id查询用户信息
    UserInfo findUserById(String uid) throws Exception;

}
