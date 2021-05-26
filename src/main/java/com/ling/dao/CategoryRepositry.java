package com.ling.dao;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/5/4
 * @ Time 16:55
 */

import com.ling.pojo.Category;
import com.ling.vo.CategoryVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepositry extends JpaRepository<Category,Integer> {
    Page<Category> findByCategoryNameLike(String categoryName, Pageable pageable);


    @Query(value = "select new com.ling.vo.CategoryVo(a.id,a.categoryName)from Category a")
    List<CategoryVo> findCategories();
}
