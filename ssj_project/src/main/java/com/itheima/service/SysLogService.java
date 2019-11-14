package com.itheima.service;

import com.itheima.domain.PageBean;
import com.itheima.domain.SysLog;

public interface SysLogService {
    PageBean<SysLog> findAll(int currentPage, int rows);
}
