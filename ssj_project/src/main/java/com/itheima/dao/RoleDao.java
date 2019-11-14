package com.itheima.dao;

import com.itheima.domain.Role;

import java.util.List;

public interface RoleDao {
    //查询数据库中数据总条目数
    Integer findTotalCount() throws Exception;

    //分页查询数据信息
    List<Role> findAll(int i, int rows) throws Exception;

    void save(Role role) throws Exception;

    List<Role> findRolesByUid(String uid) throws Exception;

    void clear(String userId);

    void saveRoleByUid(String userId, String id);

    void clearByRid(String rid);

    void deleteRole(String rid) throws Exception;
}
