package com.ling.pojo;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/5/1
 * @ Time 16:50
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel(value="Category对象", description="分类")
@Entity
@Table(name = "t_category")
@Data
public class Category implements Serializable {
    @ApiModelProperty(value = "//分类id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "//分类名称")
    private String categoryName;

    @ApiModelProperty(value = "//创建时间")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ApiModelProperty(value = "//更新时间")
    @Temporal(TemporalType.TIMESTAMP)
    private  Date updateTime;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Article> articles=new ArrayList<>();
}
