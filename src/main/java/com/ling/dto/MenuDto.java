package com.ling.dto;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/17
 * @ Time 20:16
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
public class MenuDto {
    @ApiModelProperty(value = "//后台菜单id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuId;
    @ApiModelProperty(value = "//用户角色")
    private String userRole;
    @ApiModelProperty(value = "//菜单列表名字")
    private String menuName;

    @ApiModelProperty(value = "//菜单的url")
    private String menuUrl;

    @ApiModelProperty(value = "//菜单的父ID")
    private Integer parentId;

    @ApiModelProperty(value = "//菜单排序")
    private Integer menuSort;

    @ApiModelProperty(value = "//描述")
    private String description;
    @ApiModelProperty(value = "//菜单图标")
    private String menuIcon;
    @ApiModelProperty(value = "//二级菜单目录")
    private List<MenuDto> children;
}