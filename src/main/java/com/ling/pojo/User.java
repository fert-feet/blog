package com.ling.pojo;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/8
 * @ Time 17:30
 */


import com.ling.vo.UsersVo;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "t_user")
@DynamicUpdate
@EqualsAndHashCode(callSuper = false)
@Data
@ApiModel(value="User对象", description="用户")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    @Temporal(TemporalType.DATE)
    private Date createTime;
    private Date updateTime;
    private String avatar;
    private String info;
    private Boolean isSilence;

    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private List<Role> roles=new ArrayList<>();


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", avatar='" + avatar + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
