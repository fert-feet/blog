package com.ling.vo;/*
 * @ Created with IDEA
 * @ Author Ky2Fe
 * @ Date 2021/5/30
 * @ Time 21:06
 */

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AddArticleVo implements Serializable {
    private Integer id;
    private Integer categoryId;
    private String articleTitle;
    private String articleContent;
    private String articleCover;
    private List<Integer> tagIdList;
    private Boolean isDraft;

    public AddArticleVo(Integer id, Integer categoryId, String articleTitle, String articleContent, String articleCover, Boolean isDraft) {
        this.id = id;
        this.categoryId = categoryId;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleCover = articleCover;
        this.isDraft = isDraft;
    }
}
