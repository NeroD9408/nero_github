package com.itheima.aop;

import com.itheima.dao.PermissionDao;
import com.itheima.dao.RoleDao;
import com.itheima.dao.SysLogDao;
import com.itheima.dao.UserDao;
import com.itheima.dao.impl.PermissionDaoImpl;
import com.itheima.dao.impl.RoleDaoImpl;
import com.itheima.dao.impl.SysLogDaoImpl;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.*;
import com.itheima.utils.PageBeanUtil;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class AopFindAll {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private SysLogDao sysLogDao;
    @Autowired
    private PermissionDao permissionDao;
    private PageBeanUtil pbu = new PageBeanUtil();

    @Pointcut("execution(* com.itheima.controller.*.findAll(String, String)) && args(currentPage_str,rows_str)))")
    public void pointcut(String currentPage_str, String rows_str){}

    @Before(value="pointcut(currentPage_str, rows_str)", argNames = "currentPage_str, rows_str")
    public void before(String currentPage_str, String rows_str) throws Exception {
        //获取需要使用的参数
        int currentPage = 1;
        int rows = 0;
        if (currentPage_str != null && currentPage_str.length() != 0 && !"null".equals(currentPage_str) && !"undefined".equals(currentPage_str)) {
            currentPage = Integer.parseInt(currentPage_str);
        }
        if (rows_str != null && rows_str.length() != 0 && !"null".equals(rows_str) && !"undefined".equals(currentPage_str)) {
            rows = Integer.parseInt(rows_str);
        }
        //获取访问的请求路径，用来判断走哪个分页
        String uri = request.getRequestURI();

        if (uri.contains("user/findAll")) {
            //给当前页和条目数赋默认值
            if (rows == 0) {
                rows = 5;
            }
            PageBean<UserInfo> pb = new PageBean<>();
            UserDaoImpl dao = (UserDaoImpl) userDao;
            pb = pbu.getPageBean(currentPage, rows, pb, dao);
            request.setAttribute("pb", pb);
        } else if(uri.contains("role/findAll")) {
            //给当前页和条目数赋默认值
            if (rows == 0) {
                rows = 5;
            }
            PageBean<Role> pb = new PageBean<>();
            RoleDaoImpl dao = (RoleDaoImpl) roleDao;
            pb = pbu.getPageBean(currentPage, rows, pb, dao);
            request.setAttribute("pb", pb);
        } else if (uri.contains("sysLog/findAll")) {
            //给当前页和条目数赋默认值
            if (rows == 0) {
                rows = 20;
            }
            PageBean<SysLog> pb = new PageBean<>();
            SysLogDaoImpl dao = (SysLogDaoImpl) sysLogDao;
            pb = pbu.getPageBean(currentPage, rows, pb, dao);
            request.setAttribute("pb", pb);
        } else if (uri.contains("permission/findAll")) {
            if (rows == 0) {
                rows = 5;
            }
            PageBean<Permission> pb = new PageBean<>();
            PermissionDaoImpl dao = (PermissionDaoImpl) permissionDao;
            pb = pbu.getPageBean(currentPage, rows, pb, dao);
            request.setAttribute("pb", pb);
        }
    }
}
