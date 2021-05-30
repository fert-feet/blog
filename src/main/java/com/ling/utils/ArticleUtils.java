package com.ling.utils;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/5/4
 * @ Time 15:26
 */

import com.ling.pojo.Tag;
import com.ling.vo.AddArticleVo;
import com.ling.vo.ArticleVo;
import com.ling.vo.TagVo;
import com.ling.vo.user.UserArticleVo;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleUtils {
    /**
     * 工具类 合并查询（现在查询的数据将tagName分开了,自行测试,之后可以封装成一个类
     * @param articleVoList
     * @return
     */
    public PagedListHolder changeToPage(List<ArticleVo> articleVoList, Integer current, Integer size){
        List<Integer> articleIdList=new ArrayList<>();
        List<ArticleVo> articleVoList1=new ArrayList<>();
        //取article的所有id存入列表
        for (ArticleVo articleVo :articleVoList){
            //判断id是否在列表里存在
            if (articleIdList.contains(articleVo.getId())==false) {
                articleIdList.add(articleVo.getId());
            }
        }
        //
        for (Integer articleId:articleIdList){
            //接收tagName
            List<String> tagsName=new ArrayList<>();
            //最终加入articleVoList的articleVo
            ArticleVo articleVo=new ArticleVo();
            for (ArticleVo articleVo2:articleVoList){
                if (articleId.equals(articleVo2.getId())){
                    tagsName.add(articleVo2.getTagName());
                    //让最后一个值赋给articleVo,因为其他值都相等
                    articleVo=articleVo2;
                }
            }
            //设置tagName列表
            articleVo.setTagsName(tagsName);
            articleVoList1.add(articleVo);
        }
        //清空原来的列表
        articleVoList.clear();
        articleVoList.addAll(articleVoList1);
        //因为PageImpl有问题，所以改用PagedListHolder
        PagedListHolder<ArticleVo> articleListVoPage=new PagedListHolder<>(articleVoList);
        articleListVoPage.setPage(current);
        articleListVoPage.setPageSize(size);
        return articleListVoPage;
    }

    /**
     * 首页文章合并分页类,可之后优化
     * @param userArticleVoList
     * @return
     */
    public PagedListHolder changeToHomePage(List<UserArticleVo> userArticleVoList,Integer current){
        List<Integer> articleIdList=new ArrayList<>();
        List<UserArticleVo> userArticleVos=new ArrayList<>();
        for (UserArticleVo userArticleVo:userArticleVoList){
            if(articleIdList.contains(userArticleVo.getId())==false){
                articleIdList.add(userArticleVo.getId());
            }
        }
        for (Integer articleId:articleIdList){
            List<TagVo> tagVoList=new ArrayList<>();
            UserArticleVo userArticle=new UserArticleVo();
            for (UserArticleVo userArticleVo:userArticleVoList){
                if (userArticleVo.getId().equals(articleId)){
                    TagVo tagVo = new TagVo(userArticleVo.getTagId(), userArticleVo.getTagName());
                    tagVoList.add(tagVo);
                    userArticle=userArticleVo;
                }
            }
            userArticle.setTagVoList(tagVoList);
            userArticleVos.add(userArticle);
        }
        userArticleVoList=userArticleVos;
        PagedListHolder<UserArticleVo> userAritcleListPage=new PagedListHolder<>(userArticleVoList);
        userAritcleListPage.setPage(current-1);
        userAritcleListPage.setPageSize(10);
        return userAritcleListPage;
    }


    /**
     * 将bean中的tag合并变为标签列表
     * @param article
     * @param tagList
     * @return
     */
    public AddArticleVo toTagList(AddArticleVo article, List<TagVo> tagList){
        List<Integer> tagIdList=new ArrayList<>();
        for (TagVo tag:tagList){
            tagIdList.add(tag.getId());
        }
        article.setTagIdList(tagIdList);
        return article;
    }
}
