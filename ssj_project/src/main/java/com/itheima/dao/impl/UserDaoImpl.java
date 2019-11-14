package com.itheima.dao.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.PageDao;
import com.itheima.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends PageDao<UserInfo> implements UserDao {

    @Autowired
    private JdbcTemplate template;

    @Override
    public List<UserInfo> findAll() throws Exception {
        String sql = "select * from users";
        return template.query(sql, new BeanPropertyRowMapper<>(UserInfo.class));
    }

    @Override
    public void add(UserInfo user) throws Exception {
        String sql = "insert into users values(?, ?, ?, ?, ?, ?)";
        template.update(sql, user.getId(), user.getEmail(), user.getUsername(), user.getPassword(), user.getPhoneNum(), user.getStatus());
    }

    @Override
    public void deleteUser(Object[] ids) throws Exception {
        StringBuilder sb = new StringBuilder("delete from users where id in(");
        for (int i = 0; i < ids.length; i++) {
            if (i == ids.length - 1) {
                sb.append("?)");
            } else {
                sb.append("?,");
            }
        }
//        System.out.println(sb.toString());
        template.update(sb.toString(), ids);
    }

    @Override
    public UserInfo findUserById(String uid) throws Exception {
        return template.queryForObject("select * from users where id = ?", new BeanPropertyRowMapper<>(UserInfo.class), uid);
    }

    @Override
    public Integer findTotalCount() throws Exception {
        return template.queryForObject("select count(*) from users", Integer.class);
    }

    @Override
    public List<UserInfo> findAll(int start, int rows) throws Exception {
        String sql = "select * from users limit ?,?";
        return template.query(sql, new BeanPropertyRowMapper<>(UserInfo.class), start, rows);
    }
}
