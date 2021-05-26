package com.ling.vo;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/5/11
 * @ Time 21:20
 */

import lombok.Data;

@Data
public class CategoryVo {
    private Integer id;
    private String categoryName;

    public CategoryVo(Integer id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
}
