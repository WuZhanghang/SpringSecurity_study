package com.wzh.service;

import com.wzh.domain.SysRole;
import com.wzh.domain.SysUser;
import com.wzh.domain.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //用来收集权限
        Collection<GrantedAuthority> authorities=new ArrayList<>();

        SysUser user=sysUserService.selectByName(s);

        // 判断用户是否存在
        if(user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        //添加权限
        List<SysUserRole> userRoles=sysUserRoleService.listByUserId(user.getId());
        for (SysUserRole sysUserRole:userRoles) {
            SysRole role=sysRoleService.selectById(sysUserRole.getRoleId());
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        // 返回UserDetails实现类
        return new User(user.getName(),user.getPassword(),authorities);
    }
}
