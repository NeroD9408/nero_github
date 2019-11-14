package com.itheima.controller;

import com.itheima.domain.PageBean;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.PermissionService;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    /*@RequestMapping("/findAll")
    public String findAll(Model model,
                          @RequestParam(required = false, defaultValue = "1") int currentPage,
                          @RequestParam(required = false, defaultValue = "3") int rows) throws Exception{
        PageBean<Role> pb = roleService.findAll(currentPage, rows);
        model.addAttribute("pb", pb);
        return "role-list";
    }*/

    @RequestMapping("/findAll")
    public String findAll(String currentPage, String rows) throws Exception{
        return "role-list";
    }

    @RequestMapping("/save")
    public String save(Role role) throws Exception{
        roleService.save(role);
        return "redirect:/role/findAll";
    }

    @RequestMapping("/findRolePermissionById/{rid}")
    public String findRolePermissionById(@PathVariable("rid") String rid, Model model) throws Exception {
        PageBean<Permission> pb = permissionService.findAll(1, 99999);
        List<Permission> permissions = pb.getList();
        List<Permission> rolePermissions = permissionService.findRolePermissionsByRid(rid);
        //循环遍历总权限
        for (Permission permission : permissions) {
            //遍历拥有权限
            for (Permission rolePermission : rolePermissions) {
                if (rolePermission.getId().equals(permission.getId())) {
                    //如果角色拥有权限和总权限的id相同，标记flag为1
                    permission.setFlag(1);
                }
            }
        }
        pb.setList(permissions);
        model.addAttribute("pb", pb);
        return "role-permission-add";
    }

    @RequestMapping("/updateRolePermissions")
    public String updateRolePermissions(String roleId, String[] pids) throws Exception{
        permissionService.clear(roleId);
        permissionService.savePermissionByRid(roleId, pids);
        return "redirect:/role/findAll";
    }

    @RequestMapping("/deleteRole")
    public String deleteRole(String rid) throws Exception {
        //清除user-role中的数据
        roleService.clearByRid(rid);
        //清除role-permission中的数据
        permissionService.clear(rid);
        //删除角色信息
        roleService.deleteRole(rid);
        return "redirect:/role/findAll";
    }
}
