package com.ling.serviceimpl;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/5/1
 * @ Time 17:19
 */

import com.ling.dao.ArticleRepositry;
import com.ling.dao.CategoryRepositry;
import com.ling.dao.TagRepositry;
import com.ling.pojo.Article;
import com.ling.pojo.Category;
import com.ling.pojo.Tag;
import com.ling.service.ArticleService;
import com.ling.utils.ArticleUtils;
import com.ling.vo.ArticleVo;
import com.ling.vo.SaveOrUpdateArticleVO;
import com.ling.vo.user.ArchiveVo;
import com.ling.vo.user.UserArticleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author FEET
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepositry articleRepositry;

    @Autowired
    private TagRepositry tagRepositry;

    @Autowired
    private CategoryRepositry categoryRepositry;

    @Override
    public PagedListHolder<ArticleVo> listArticle(Integer current, Integer size, String title) {
        var articleUtils=new ArticleUtils();
        if (StringUtils.hasLength(title)){
            List<ArticleVo> articleVoList = articleRepositry.findArticleListByTitle(title);
            return articleUtils.changeToPage(articleVoList,current,size);
        }else {
            List<ArticleVo> articleVoList = articleRepositry.findArticleList();
            return articleUtils.changeToPage(articleVoList,current,size);
        }

    }

    @Override
    public Article getArticleById(Integer id) {
        var article=articleRepositry.findById(id).orElse(null);
        if (article!=null){
            return article;
        }else {
            return null;
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateIsTopById(Boolean flag, Integer id) {
        var article = articleRepositry.findById(id).orElse(null);
        if (article!=null){
            article.setIsTop(flag);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteArticleById(Integer id) {
        Article article = articleRepositry.findById(id).orElse(null);
        if(article!=null){
            articleRepositry.delete(article);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateArticle(SaveOrUpdateArticleVO saveArticleVo) {
        List<Tag> tags=new ArrayList<>();
        Integer articleId = saveArticleVo.getId();
        Integer categoryId = saveArticleVo.getCategoryId();
        for (Integer tagId:saveArticleVo.getTagIdList()){
            Tag tag = tagRepositry.findById(tagId).orElse(null);
            tags.add(tag);
        }
        Category category = categoryRepositry.findById(categoryId).orElse(null);
        if(articleId!=null){
            Article article = articleRepositry.findById(articleId).orElse(null);
            BeanUtils.copyProperties(saveArticleVo,article);
            article.getTags().clear();
            article.getTags().addAll(tags);
            article.setCategory(category);
            article.setCreateTime(new Date());
            article.setUpdateTime(new Date());
            articleRepositry.save(article);
        }else {
            Article article=new Article();
            BeanUtils.copyProperties(saveArticleVo,article);
            article.getTags().addAll(tags);
            article.setCategory(category);
            article.setCreateTime(new Date());
            article.setUpdateTime(new Date());
            articleRepositry.save(article);
        }
    }

    @Override
    public PagedListHolder<UserArticleVo> listHomeArticle(Integer current) {
        ArticleUtils articleUtils = new ArticleUtils();
        List<UserArticleVo> homeArticleList = articleRepositry.findHomeArticleList();
        return articleUtils.changeToHomePage(homeArticleList,current);
    }

    @Override
    public Page<ArchiveVo> getArchive(@RequestParam(value = "current") Integer current) {
        Pageable pageable = PageRequest.of(current-1, 5);
        return articleRepositry.findArchive(pageable);
    }

    @Override
    public Long getArticleCount() {
        return articleRepositry.count();
    }


}
