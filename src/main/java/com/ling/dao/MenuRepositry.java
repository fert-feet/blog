package com.ling.dao;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/18
 * @ Time 0:22
 */

import com.ling.pojo.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepositry extends JpaRepository<Menu,Integer> {
    List<Menu> findAllByParentIdEquals(Integer pid);
    List<Menu> findAllByParentIdGreaterThan(Integer pid);
}
