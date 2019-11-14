package com.itheima.dao.impl;

import com.itheima.dao.AdminDao;
import com.itheima.domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl implements AdminDao {

    @Autowired
    private JdbcTemplate template;

    //根据用户名和密码查找用户
    @Override
    public Admin findAdminByUsernameAndPassword(String username, String password) throws Exception {
        try {
            return template.queryForObject("select * from admin where username = ? and password = ?", new BeanPropertyRowMapper<>(Admin.class), username, password);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    //更新admin信息
    @Override
    public void updateAdmin(Admin admin) throws Exception {
        template.update("update admin set password = ?, imgpath = ? where id = ?", admin.getPassword(), admin.getImgpath(), admin.getId());
    }
}
