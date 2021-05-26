package com.ling.vo;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/24
 * @ Time 17:11
 */

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserAuthCountVo implements Serializable {
    String userAuth;
    Long size;

    public UserAuthCountVo(String userAuth, Long size) {
        this.userAuth = userAuth;
        this.size = size;
    }
}
