package com.ling.vo;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/24
 * @ Time 17:44
 */

import lombok.Data;

import java.io.Serializable;

@Data
public class AddUserVo implements Serializable {
    private String username;
    private String password;
    private String avatar;
    private String nickname;
    private String roleName;
}
