package com.ling.vo;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/5/1
 * @ Time 17:23
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ling.pojo.Tag;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ArticleVo implements Serializable {
    @ApiModelProperty(value = "//文章主键id")
    private Integer id;

    @ApiModelProperty(value = "//文章标题")
    private String articleTitle;

    @ApiModelProperty(value = "//分类类名")
    private String categoryName;

    @JsonIgnore
    private String tagName;

    @ApiModelProperty(value = "//标签名")
    private List<String> tagsName=new ArrayList<>();

    @ApiModelProperty(value = "//创建时间")
    private Date createTime;

    @ApiModelProperty(value = "//更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "//是否置顶 0：不置顶 1：置顶")
    private Boolean isTop;

    public ArticleVo(Integer id, String articleTitle, String categoryName, String tagName, Date createTime, Date updateTime, Boolean isTop) {
        this.id = id;
        this.articleTitle = articleTitle;
        this.categoryName = categoryName;
        this.tagName = tagName;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isTop = isTop;
    }

    public ArticleVo(){

    }
}
