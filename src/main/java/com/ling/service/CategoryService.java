package com.ling.service;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/5/4
 * @ Time 16:52
 */

import com.ling.pojo.Category;
import com.ling.vo.CategoryVo;
import com.ling.vo.user.UserArticleVo;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    /**
     * 查询所有分类
     * @return
     */
    List<Category> listCategory();

    /**
     * 查询分页内容
     * @param current
     * @param size
     * @param categoryName
     * @return
     */
    Page<Category> listCategoryPage(Integer current, Integer size, String categoryName);

    /**
     * 删除分类
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 更新或添加分类
     * @param categoryVo
     */
    void updateCategory(CategoryVo categoryVo);

    /**
     * 查询展示页分类
     * @return
     */
    List<CategoryVo> listCategoryVo();

    /**
     * 查询分类总数
     * @return
     */
    Long getCategoryCount();

    /**
     * 按分类查询文章
     * @param categoryId
     * @param current
     * @return
     */
    PagedListHolder<UserArticleVo> listArticleVoByCategoryId(Integer categoryId,Integer current);

}
