package com.ling.dao;/*
 * @ Created with IDEA
 * @ Author Ky2Fe
 * @ Date 2021/5/27
 * @ Time 0:43
 */

import com.ling.pojo.About;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AboutRepositry extends JpaRepository<About,Integer> {
    @Query("select new com.ling.pojo.About(a.id,a.content) from About a")
    About getAbout();
}
