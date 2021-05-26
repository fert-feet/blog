package com.ling.pojo;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/9
 * @ Time 14:02
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "t_role")
@Getter
@Setter
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String roleName;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    private String description;


    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
    private List<User> users=new ArrayList<>();

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)
    private List<Api> apis=new ArrayList<>();

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", createTime=" + createTime +
                '}';
    }


}
