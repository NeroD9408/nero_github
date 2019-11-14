package com.itheima.domain;

import com.itheima.dao.SysLogDao;
import com.itheima.utils.UUIDUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

//切面对象，存放增强方法
@Component
@Aspect
public class Aop {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogDao sysLogDao;

    @Pointcut("execution(* com.itheima.controller.*.*(..))")
    private void pid(){}

    private SysLog sysLog;
    private long visitTime;

    @Before("pid()")
    public void before() {
        sysLog = new SysLog();
        sysLog.setVisitTime(new Date());
        visitTime = new Date().getTime();
    }

    @After("pid()")
    public void after(JoinPoint joinPoint) {
        sysLog.setId(UUIDUtil.getUuid());
        sysLog.setUsername("aaa");
        sysLog.setIp(request.getRemoteAddr());
        sysLog.setExecutionTime(new Date().getTime() - visitTime);
        String requestURI = request.getRequestURI();
        String[] split = requestURI.split("/");
        String url = split[2] + "/" + split[3];
        sysLog.setUrl(url);
        sysLog.setMethod(joinPoint.getSignature().getName());
        if (!requestURI.contains("/sysLog/")) {
            sysLogDao.save(sysLog);
        }
    }
}
