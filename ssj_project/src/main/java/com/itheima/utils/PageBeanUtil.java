package com.itheima.utils;

import com.itheima.domain.PageBean;
import com.itheima.domain.PageDao;

public class PageBeanUtil<T> {

    public PageBean<T> getPageBean(int currentPage, int rows, PageBean<T> pb, PageDao<T> dao) throws Exception {

        pb.setRows(rows);
        //设置当前页数
        pb.setCurrentPage(currentPage);
        //设置数据库查询到的总条目数
        pb.setTotalCount(dao.findTotalCount());
        //设置总页数
        pb.setTotalPage((int) Math.ceil(pb.getTotalCount() * 1.0 / pb.getRows()));
        //设置查询到的数据
        pb.setList(dao.findAll((currentPage - 1) * rows, rows));

        return pb;
    }
}
