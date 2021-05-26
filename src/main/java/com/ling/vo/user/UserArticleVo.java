package com.ling.vo.user;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/5/13
 * @ Time 19:39
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ling.vo.TagVo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class UserArticleVo implements Serializable {
    private Integer id;

    private String articleTitle;

    private String articleContent;

    private String articleCover;

    private Boolean isTop;

    private Date createTime;

    private Integer categoryId;

    private String categoryName;

    @JsonIgnore
    private Integer tagId;

    @JsonIgnore
    private String tagName;

    private List<TagVo> tagVoList;

    public UserArticleVo(Integer id, String articleTitle, String articleContent, String articleCover, Boolean isTop, Date createTime, Integer categoryId, String categoryName, Integer tagId, String tagName) {
        this.id = id;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleCover = articleCover;
        this.isTop = isTop;
        this.createTime = createTime;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public UserArticleVo() {
    }
}
