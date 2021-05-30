package com.ling.pojo;/*
 * @ Created with IDEA
 * @ Author Ky2Fe
 * @ Date 2021/5/27
 * @ Time 0:41
 */

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "t_about")
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;

    public About(Integer id, String content) {
        this.id = id;
        this.content = content;
    }

    public About() {
    }
}
