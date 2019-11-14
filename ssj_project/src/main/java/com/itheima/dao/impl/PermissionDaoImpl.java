package com.itheima.dao.impl;

import com.itheima.dao.PermissionDao;
import com.itheima.domain.PageDao;
import com.itheima.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PermissionDaoImpl extends PageDao<Permission> implements PermissionDao {

    @Autowired
    private JdbcTemplate template;

    //查询权限总数
    @Override
    public Integer findTotalCount() throws Exception {
        return template.queryForObject("select count(*) from permission", Integer.class);
    }

    //查询所有权限并分页展示
    @Override
    public List<Permission> findAll(int start, int rows) throws Exception {
        return template.query("select * from permission limit ?,?", new BeanPropertyRowMapper<>(Permission.class), start, rows);
    }

    //添加权限
    @Override
    public void save(Permission permission) throws Exception {
        template.update("insert into permission values(?,?,?)", permission.getId(), permission.getPermissionName(), permission.getUrl());
    }

    //查询角色权限
    @Override
    public List<Permission> findRolePermissionsByRid(String rid) throws Exception {
        String sql = "select * from permission where id in(select permissionId from role_permission where roleId = ?)";
        return template.query(sql, new BeanPropertyRowMapper<>(Permission.class), rid);
    }

    //清除角色权限
    @Override
    public void clear(String roleId) throws Exception {
        template.update("delete from role_permission where roleId = ?", roleId);
    }

    //给指定id角色添加权限
    @Override
    public void savePermissionByRid(String roleId, String pid) throws Exception {
        template.update("insert into role_permission values(?,?)", pid, roleId);
    }

    @Override
    public Permission findPermissionByPid(String pid) throws Exception {
        return template.queryForObject("select * from permission where id = ?", new BeanPropertyRowMapper<>(Permission.class), pid);
    }

    @Override
    public void deletePermissionByPid(String pid) throws Exception {
        template.update("delete from permission where id = ?", pid);
    }

    @Override
    public void clearByPid(String pid) throws Exception {
        template.update("delete from role_permission where permissionId = ?", pid);
    }
}
