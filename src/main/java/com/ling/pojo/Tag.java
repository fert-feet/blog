package com.ling.pojo;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/5/1
 * @ Time 16:55
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

@Entity
@Table(name = "t_tag")
@ApiModel(value="Tag对象", description="标签")
@Data
public class Tag implements Serializable {

    @ApiModelProperty(value = "//标签id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "//标签名称")
    private String tagName;
    @ApiModelProperty(value = "//创建时间")
    private Date createTime;
    @ApiModelProperty(value = "//更新时间")
    private  Date updateTime;

    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    private List<Article> articles=new ArrayList<>();
}
