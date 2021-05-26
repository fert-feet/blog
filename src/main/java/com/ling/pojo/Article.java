package com.ling.pojo;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/5/1
 * @ Time 16:47
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

@ApiModel(value="Article对象", description="文章实体类")
@Entity
@Table(name = "t_article")
@Data
public class Article implements Serializable {

    @ApiModelProperty(value = "//文章主键id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "//文章标题")
    private String articleTitle;

    @ApiModelProperty(value = "//文章内容")
    private String articleContent;

    @ApiModelProperty(value = "//文章封面")
    private String articleCover;


    @ApiModelProperty(value = "//是否置顶 0：不置顶 1：置顶")
    private Boolean isTop;

    @ApiModelProperty(value = "//是否草稿 0：不是草稿 1:草稿")
    private Boolean isDraft;

    @ApiModelProperty(value = "//创建时间")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ApiModelProperty(value = "//更新时间")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.PERSIST)
    List<Tag> tags=new ArrayList<>();

}
