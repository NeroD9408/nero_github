package com.itheima.controller;

import com.itheima.domain.PageBean;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.PermissionService;
import com.itheima.service.RoleService;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/findAll")
    public String findAll(String currentPage, String rows) throws Exception {
        return "user-list";
    }

    @RequestMapping("/saveUser")
    public String saveUser(UserInfo user) throws Exception {
        userService.add(user);
        return "redirect:/user/findAll";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(String[] ids) throws Exception {
        //清除user-role表中数据
        for (String uid : ids) {
            roleService.clear(uid);
        }
        userService.deleteUser(ids);
        return "redirect:/user/findAll";
    }

    @RequestMapping("/findRolesByUserId/{uid}")
    public String findRolesByUserId(@PathVariable("uid")String uid, Model model) throws Exception {
        PageBean<Role> pb = roleService.findAll(1, 99999);
        List<Role> roles = pb.getList();
        List<Role> uRoles = roleService.findRolesByUserId(uid);

        //遍历角色的总集合
        for (Role role : roles) {
            for (Role uRole : uRoles) {
                //遍历用户角色的总集合, 如果roleId相同，就设置role中的flag为1
                if (uRole.getId().equals(role.getId())) {
                    role.setFlag(1);
                }
            }
        }
        pb.setList(roles);
        model.addAttribute("pb", pb);
        return "user-role-add";
    }

    @RequestMapping("/updateUserRoles")
    public String updateUserRoles(String userId, String[] ids) throws Exception{
        roleService.clear(userId);
        roleService.saveRoleByUid(userId, ids);
        return "redirect:/user/findAll";
    }

    @RequestMapping("/findUserById")
    public String findUserById(String uid, Model model) throws Exception{
        //封装UserInfo对象的基本数据
        UserInfo user = userService.findUserById(uid);
        //设置List<Role>
        List<Role> roleList = roleService.findRolesByUserId(uid);
        for (Role role : roleList) {
            List<Permission> permissionList = permissionService.findRolePermissionsByRid(role.getId());
            role.setPermissions(permissionList);
        }
        user.setRoles(roleList);
        model.addAttribute("user", user);
        return "user-show";
    }


}
