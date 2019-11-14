package com.itheima.service;

import com.itheima.domain.PageBean;
import com.itheima.domain.Permission;

import java.util.List;

public interface PermissionService {

    PageBean<Permission> findAll(int currentPage, int rows) throws Exception;

    void save(Permission permission) throws Exception;

    List<Permission> findRolePermissionsByRid(String rid) throws Exception;

    void clear(String roleId) throws Exception;

    void savePermissionByRid(String roleId, String[] pids) throws Exception;

    Permission findPermissionByPid(String pid) throws Exception;

    void deletePermissionByPid(String pid) throws Exception;
}
