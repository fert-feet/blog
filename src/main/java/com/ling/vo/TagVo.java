package com.ling.vo;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/5/8
 * @ Time 15:33
 */

import lombok.Data;

import java.io.Serializable;

@Data
public class TagVo implements Serializable {
    private Integer id;
    private String tagName;

    public TagVo(Integer id, String tagName) {
        this.id = id;
        this.tagName = tagName;
    }

    public TagVo(Integer id) {
        this.id = id;
    }
}
