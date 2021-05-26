package com.ling.serviceimpl;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/5/4
 * @ Time 16:52
 */

import com.ling.dao.ArticleRepositry;
import com.ling.dao.CategoryRepositry;
import com.ling.pojo.Category;
import com.ling.service.CategoryService;
import com.ling.utils.ArticleUtils;
import com.ling.vo.CategoryVo;
import com.ling.vo.user.UserArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepositry categoryRepositry;

    @Autowired
    private ArticleRepositry articleRepositry;

    @Autowired
    private ArticleUtils articleUtils;

    @Override
    public List<Category> listCategory() {
        return categoryRepositry.findAll();
    }

    @Override
    public Page<Category> listCategoryPage(Integer current, Integer size, String categoryName) {
        Pageable pageable= PageRequest.of(current-1,size);
        if(StringUtils.hasLength(categoryName)){
           return categoryRepositry.findByCategoryNameLike("%"+categoryName+"%",pageable);
        }else {
            return categoryRepositry.findAll(pageable);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Integer id) {
        if (id!=null){
            categoryRepositry.deleteById(id);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCategory(CategoryVo categoryVo) {
        Integer id = categoryVo.getId();
        String categoryName = categoryVo.getCategoryName();
        //更新
        if (id!=null){
            Category category = categoryRepositry.findById(id).orElse(null);
            category.setCategoryName(categoryName);
            category.setUpdateTime(new Date());
            categoryRepositry.save(category);
        }
        //新增
        else {
            Category category = new Category();
            category.setCategoryName(categoryName);
            category.setCreateTime(new Date());
            category.setUpdateTime(new Date());
            categoryRepositry.save(category);
        }
    }

    @Override
    public List<CategoryVo> listCategoryVo() {
        return categoryRepositry.findCategories();
    }

    @Override
    public Long getCategoryCount() {

        return categoryRepositry.count();
    }

    @Override
    public PagedListHolder<UserArticleVo> listArticleVoByCategoryId(Integer categoryId, Integer current) {
        List<UserArticleVo> articleVoList = articleRepositry.findArticleByCategory(categoryId);
        PagedListHolder pagedListHolder = articleUtils.changeToHomePage(articleVoList, current);
        return pagedListHolder;
    }
}
