package com.ling.vo.user;/*
 * @ Created with IDEA
 * @ Author Ky2Fe
 * @ Date 2021/5/23
 * @ Time 15:46
 */


import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class ArchiveVo implements Serializable {
    private Integer id;
    private Date createTime;
    private String articleTitle;

    public ArchiveVo(Integer id, Date createTime, String articleTitle) {
        this.id = id;
        this.createTime = createTime;
        this.articleTitle = articleTitle;
    }
}
