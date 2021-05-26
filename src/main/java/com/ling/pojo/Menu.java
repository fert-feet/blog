package com.ling.pojo;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/17
 * @ Time 17:53
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "t_menu")
@ApiModel(value="Menu对象", description="菜单类")
public class Menu implements Serializable {

    @ApiModelProperty(value = "//后台菜单id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuId;

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

    @Transient
    @OneToMany
    private List<Menu> children=new ArrayList<>();
}
