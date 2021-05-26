package com.ling.dao;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/30
 * @ Time 9:28
 */

import com.ling.pojo.Api;
import com.ling.vo.ApiIdVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApiRepositry extends JpaRepository<Api,Integer> {

    @Query(value = "select new com.ling.vo.ApiIdVo(a.id) from Api a inner join a.roles b where b.id= :roleId")
    List<ApiIdVo> findApiIdByRoleId(@Param("roleId") Integer roleId);
}
