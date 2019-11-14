package com.itheima.service.impl;

import com.itheima.dao.SysLogDao;
import com.itheima.domain.PageBean;
import com.itheima.domain.Role;
import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;
    @Override
    public PageBean<SysLog> findAll(int currentPage, int rows) {
        //创建分页对象
        PageBean<SysLog> pb = new PageBean<>();
        //设置每页显示条数
        pb.setRows(rows);
        //设置当前页数
        pb.setCurrentPage(currentPage);
        //设置数据库查询到的总条目数
        pb.setTotalCount(sysLogDao.findTotalCount());
        //设置总页数
        pb.setTotalPage((int) Math.ceil(pb.getTotalCount() * 1.0 / pb.getRows()));
        //设置查询到的数据
        pb.setList(sysLogDao.findAll((currentPage - 1) * rows, rows));
        return pb;
    }
}
