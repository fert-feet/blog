package com.ling.vo;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/24
 * @ Time 0:04
 */

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateUserVo implements Serializable {
    private Integer id;
    private String avatar;
    private String nickname;
    private String roleName;

    public UpdateUserVo(Integer id, String avatar, String nickname, String roleName) {
        this.id = id;
        this.avatar = avatar;
        this.nickname = nickname;
        this.roleName = roleName;
    }
}
