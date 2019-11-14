package com.itheima.dao.impl;

import com.itheima.dao.SysLogDao;
import com.itheima.domain.PageDao;
import com.itheima.domain.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SysLogDaoImpl extends PageDao<SysLog> implements SysLogDao {

    @Autowired
    private JdbcTemplate template;
    @Override
    public Integer findTotalCount() {
        return template.queryForObject("select count(*) from syslog", Integer.class);
    }

    @Override
    public List<SysLog> findAll(int i, int rows) {
        return template.query("select * from syslog order by visitTime desc limit ?,?", new BeanPropertyRowMapper<>(SysLog.class), i, rows);
    }

    @Override
    public void save(SysLog sysLog) {
        template.update("insert into syslog values(?,?,?,?,?,?,?)",
                sysLog.getId(),
                sysLog.getVisitTimeStr(),
                sysLog.getUsername(),
                sysLog.getIp(),
                sysLog.getUrl(),
                sysLog.getExecutionTime(),
                sysLog.getMethod());
    }
}
