package com.itheima.dao;

import com.itheima.domain.Permission;

import java.util.List;

public interface PermissionDao {

    Integer findTotalCount() throws Exception;

    List<Permission> findAll(int start, int rows) throws Exception;

    void save(Permission permission) throws Exception;

    List<Permission> findRolePermissionsByRid(String rid) throws Exception;

    void clear(String roleId) throws Exception;

    void savePermissionByRid(String roleId, String pid) throws Exception;

    Permission findPermissionByPid(String pid) throws Exception;

    void deletePermissionByPid(String pid) throws Exception;

    void clearByPid(String pid) throws Exception;
}
