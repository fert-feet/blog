package com.ling.vo;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/20
 * @ Time 16:45
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
public class UsersVo implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "用户角色")
    private String roleName;


    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;



    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


    @ApiModelProperty(value = "0：不禁言  1：禁言")
    private Boolean isSilence;

    public UsersVo(Integer id, String roleName, String nickname, String avatar, Date createTime, Date updateTime, Boolean isSilence) {
        this.id = id;
        this.roleName = roleName;
        this.nickname = nickname;
        this.avatar = avatar;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isSilence = isSilence;
    }
}
