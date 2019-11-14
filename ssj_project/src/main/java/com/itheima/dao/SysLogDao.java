package com.itheima.dao;

import com.itheima.domain.SysLog;

import java.util.List;

public interface SysLogDao {
    Integer findTotalCount();

    List<SysLog> findAll(int i, int rows);

    void save(SysLog sysLog);
}
