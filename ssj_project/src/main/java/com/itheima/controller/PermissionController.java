package com.itheima.controller;

import com.itheima.domain.PageBean;
import com.itheima.domain.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/findAll")
    public String findAll(Model model,
                          @RequestParam(required = false, defaultValue = "1") int currentPage,
                          @RequestParam(required = false, defaultValue = "10") int rows) throws Exception{
        PageBean<Permission> pb = permissionService.findAll(currentPage, rows);
        model.addAttribute("pb", pb);
        return "permission-list";
    }

    @RequestMapping("/save")
    public String save(Permission permission) throws Exception{
        permissionService.save(permission);
        return "redirect:/permission/findAll";
    }

    @RequestMapping("/findPermissionByPid")
    public String findPermissionByPid(String pid, Model model) throws Exception {
        Permission permission = permissionService.findPermissionByPid(pid);
        model.addAttribute("permission", permission);
        return "permission-show";
    }

    @RequestMapping("/deletePermissionByPid")
    public String deletePermissionByPid(String pid) throws Exception {
        permissionService.deletePermissionByPid(pid);
        return "redirect:/permission/findAll";
    }

}
