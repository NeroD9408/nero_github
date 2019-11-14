package com.itheima.service.impl;

import com.itheima.dao.PermissionDao;
import com.itheima.dao.impl.PermissionDaoImpl;
import com.itheima.domain.PageBean;
import com.itheima.domain.Permission;
import com.itheima.service.PermissionService;
import com.itheima.utils.PageBeanUtil;
import com.itheima.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public PageBean<Permission> findAll(int currentPage, int rows) throws Exception {
        PageBean<Permission> pb = new PageBean<>();
        PermissionDaoImpl rd = (PermissionDaoImpl) permissionDao;
        //设置每页显示条数
        PageBeanUtil pbu = new PageBeanUtil();
        return pbu.getPageBean(currentPage, rows, pb, rd);
    }

    @Override
    public void save(Permission permission) throws Exception{
        permission.setId(UUIDUtil.getUuid());
        permissionDao.save(permission);
    }

    @Override
    public List<Permission> findRolePermissionsByRid(String rid) throws Exception{
        return permissionDao.findRolePermissionsByRid(rid);
    }

    @Override
    public void clear(String roleId) throws Exception {
        permissionDao.clear(roleId);
    }

    @Override
    public void savePermissionByRid(String roleId, String[] pids) throws Exception {
        if (pids != null && pids.length > 0) {
            for (String pid : pids) {
                permissionDao.savePermissionByRid(roleId, pid);
            }
        }
    }

    @Override
    public Permission findPermissionByPid(String pid) throws Exception {
        return permissionDao.findPermissionByPid(pid);
    }

    @Override
    public void deletePermissionByPid(String pid) throws Exception {
        permissionDao.clearByPid(pid);
        permissionDao.deletePermissionByPid(pid);
    }
}
