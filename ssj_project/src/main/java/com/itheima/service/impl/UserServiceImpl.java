package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<UserInfo> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public void add(UserInfo user) throws Exception {
        user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        userDao.add(user);
    }

    @Override
    public void deleteUser(String[] ids) throws Exception {
        /*for (String uid : ids) {
            userDao.clearUser(uid);
        }*/
        userDao.deleteUser(ids);
    }

    @Override
    public UserInfo findUserById(String uid) throws Exception {
        return userDao.findUserById(uid);
    }
}
