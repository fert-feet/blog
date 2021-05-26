package com.ling.vo;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/30
 * @ Time 15:17
 */

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiIdVo implements Serializable {
    private Integer id;

    public ApiIdVo(Integer id) {
        this.id = id;
    }
}
