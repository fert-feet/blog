package com.ling.pojo;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/17
 * @ Time 16:39
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "t_api")
public class Api implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = true)
    private Integer apiId;

    private String name;

    private String url;

    private String method;

    @Column(nullable = true)
    private String pid;

    private String description;

    private String sort;

    @JsonIgnore
    @ManyToMany(mappedBy = "apis",fetch = FetchType.EAGER)
    private List<Role> roles=new ArrayList<>();

    @Override
    public String toString() {
        return "Api{" +
                "id=" + id +
                ", apiId=" + apiId +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", pid='" + pid + '\'' +
                ", description='" + description + '\'' +
                ", sort='" + sort + '\'' +
                '}';
    }
}
