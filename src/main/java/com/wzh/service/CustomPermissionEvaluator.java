package com.wzh.service;

import com.wzh.domain.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Permission;
import java.util.Collection;
import java.util.List;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysPermissionService permissionService;


    //判断是否具有权限
    @Override
    public boolean hasPermission(Authentication authentication, Object targeturl, Object targetpermission) {
         //获取user
        User user=(User)authentication.getPrincipal();
         //获取user注入的角色
        Collection<GrantedAuthority> authorities=user.getAuthorities();


        //遍历用户的所有角色
        for (GrantedAuthority authority:authorities){
            //获取角色名
            String roleName=authority.getAuthority();
            Integer roleId=sysRoleService.selectByName(roleName).getId();

            //得到角色所有权限 角色->权限
            List<SysPermission> permissionList=permissionService.listByRoleId(roleId);


            for (SysPermission sysPermission:permissionList){
                //获取权限集
                  List permissions=sysPermission.getPermissions();
                  //遍历判断目标url与权限是否符合
                if(targeturl.equals(sysPermission.getUrl())&&permissions.contains(targetpermission)){
                      return true;
                }


            }




        }




        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
