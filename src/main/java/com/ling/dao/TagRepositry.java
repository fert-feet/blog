package com.ling.dao;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/5/4
 * @ Time 16:49
 */

import com.ling.pojo.Tag;
import com.ling.vo.CategoryVo;
import com.ling.vo.TagVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepositry extends JpaRepository<Tag,Integer> {


    /**
     * 根据标签名查询分页
     * @param tagName
     * @param pageable
     * @return
     */
    Page<Tag> findByTagNameLike(String tagName, Pageable pageable);


    @Query(value = "select new com.ling.vo.TagVo(a.id,a.tagName)from Tag a")
    List<TagVo> findTags();
}
