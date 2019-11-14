package com.itheima.dao.impl;

import com.itheima.dao.RoleDao;
import com.itheima.domain.PageDao;
import com.itheima.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl extends PageDao implements RoleDao {

    @Autowired
    private JdbcTemplate template;

    @Override
    public Integer findTotalCount() throws Exception{
        return template.queryForObject("select count(*) from role", Integer.class);
    }

    @Override
    public List<Role> findAll(int start, int rows) throws Exception{
        return template.query("select * from role limit ?,?", new BeanPropertyRowMapper<>(Role.class), start, rows);
    }

    @Override
    public void save(Role role) {
        template.update("insert into role values(?, ?, ?)", role.getId(), role.getRoleName(), role.getRoleDesc());
    }

    @Override
    public List<Role> findRolesByUid(String uid) throws Exception {
        return template.query("select * from role where id in(select roleId from users_role where userId = ?)",
                                   new BeanPropertyRowMapper<>(Role.class), uid);
    }

    @Override
    public void clear(String userId) {
        template.update("delete from users_role where userId = ?", userId);
    }

    @Override
    public void saveRoleByUid(String userId, String id) {
        template.update("insert into users_role values(?,?)", userId, id);
    }

    @Override
    public void clearByRid(String rid) {
        template.update("delete from users_role where roleId = ?", rid);
    }

    @Override
    public void deleteRole(String rid) throws Exception {
        template.update("delete from role where id = ?", rid);
    }
}
