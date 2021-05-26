package com.ling.serviceimpl;/*
 * @ Created with IDEA
 * @ Author Ky2Fe
 * @ Date 2021/5/22
 * @ Time 18:45
 */

import com.ling.dao.ArticleRepositry;
import com.ling.dao.CategoryRepositry;
import com.ling.dao.TagRepositry;
import com.ling.dao.UserRepositry;
import com.ling.service.BlogInfoService;
import com.ling.vo.user.BlogInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BlogInfoServiceImpl implements BlogInfoService {

    @Autowired
    private UserRepositry userRepositry;

    @Autowired
    private ArticleRepositry articleRepositry;

    @Autowired
    private TagRepositry tagRepositry;

    @Autowired
    private CategoryRepositry categoryRepositry;

    @Value("${notice}")
    private String notice;
    @Override
    public BlogInfoVo getBlogInfo() {
        BlogInfoVo blogInfo = userRepositry.findBlogInfo();
        blogInfo.setArticleCount(articleRepositry.count());
        blogInfo.setCategoryCount(categoryRepositry.count());
        blogInfo.setTagCount(tagRepositry.count());
        blogInfo.setNotice(notice);
        return blogInfo;
    }
}
