package com.wzh.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUser implements Serializable {
    static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String password;

    // 省略getter/setter
}