package com.wzh.dao;

import com.wzh.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface SysRoleMapper {
    @Select("SELECT * FROM sys_role WHERE id = #{id}")
    SysRole selectById(Integer id);


    @Select("select * from sys_role where name=#{name}")
    SysRole selectByName(String name);
}
