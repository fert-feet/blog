package com.ling.vo;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/30
 * @ Time 18:06
 */

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiRoleVo implements Serializable {
    private  Integer roleId;
    private  Integer apiId;
}
