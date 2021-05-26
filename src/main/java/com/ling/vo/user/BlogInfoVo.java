package com.ling.vo.user;/*
 * @ Created with IDEA
 * @ Author Ky2Fe
 * @ Date 2021/5/22
 * @ Time 18:24
 */

import lombok.Data;

import java.io.Serializable;

@Data
public class BlogInfoVo implements Serializable {
    /**
     * 博主昵称
     */
    private String nickname;

    /**
     * 博主头像
     */
    private String avatar;

    /**
     * 博主简介
     */
    private String intro;

    /**
     * 文章数量
     */
    private Long articleCount;

    /**
     * 分类数量
     */
    private Long categoryCount;

    /**
     * 标签数量
     */
    private Long tagCount;

    /**
     * 公告
     */
    private String notice;

    /**
     * 访问量
     */
    private String viewsCount;

    public BlogInfoVo(String nickname, String avatar, String intro) {
        this.nickname = nickname;
        this.avatar = avatar;
        this.intro = intro;
    }


}
