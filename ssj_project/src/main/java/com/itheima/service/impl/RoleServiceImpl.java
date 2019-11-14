package com.itheima.service.impl;

import com.itheima.dao.RoleDao;
import com.itheima.dao.impl.RoleDaoImpl;
import com.itheima.domain.PageBean;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import com.itheima.utils.PageBeanUtil;
import com.itheima.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public PageBean<Role> findAll(int currentPage, int rows) throws Exception{
        //创建分页对象
        PageBean<Role> pb = new PageBean<>();
        //设置每页显示条数
        PageBeanUtil pbu = new PageBeanUtil();
        RoleDaoImpl rd = (RoleDaoImpl) roleDao;
        return pbu.getPageBean(currentPage, rows, pb, rd);
    }

    @Override
    public void save(Role role) throws Exception{
        role.setId(UUIDUtil.getUuid());
        roleDao.save(role);
    }

    @Override
    public void updateRole(String uid) throws Exception{

    }

    @Override
    public List<Role> findRolesByUserId(String uid) throws Exception{
        return roleDao.findRolesByUid(uid);
    }

    @Override
    public void clear(String userId) throws Exception {
        roleDao.clear(userId);
    }

    @Override
    public void saveRoleByUid(String userId, String[] ids) throws Exception {
        if(ids != null && ids.length > 0) {
            for (String id : ids) {
                roleDao.saveRoleByUid(userId, id);
            }
        }
    }

    @Override
    public void clearByRid(String rid) throws Exception {
        roleDao.clearByRid(rid);
    }

    @Override
    public void deleteRole(String rid) throws Exception {
        roleDao.deleteRole(rid);
    }
}
