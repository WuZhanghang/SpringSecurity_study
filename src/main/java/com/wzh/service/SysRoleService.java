package com.wzh.service;

import com.wzh.dao.SysRoleMapper;
import com.wzh.domain.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleService {
    @Autowired
    private SysRoleMapper roleMapper;

    public SysRole selectById(Integer id){
        return roleMapper.selectById(id);

    }

    public  SysRole selectByName(String name){
        return roleMapper.selectByName(name);
    }
}
