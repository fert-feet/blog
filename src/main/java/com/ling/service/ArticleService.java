package com.ling.service;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/5/1
 * @ Time 17:16
 */

import com.ling.pojo.Article;
import com.ling.vo.ArticleVo;
import com.ling.vo.SaveOrUpdateArticleVO;
import com.ling.vo.user.ArchiveVo;
import com.ling.vo.user.UserArticleVo;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;

public interface ArticleService {
    /**
     * 分页查询所有文章
     * @param current
     * @param size
     * @param title
     * @return
     */
    PagedListHolder<ArticleVo> listArticle(Integer current, Integer size, String title);


    /**
     * 根据Id查找文章
     * @param id
     * @return
     */
    Article getArticleById(Integer id);

    /**
     * 改变文章置顶状态
     * @param flag
     * @param id
     */
    void updateIsTopById(Boolean flag, Integer id);


    /**
     * 删除文章
     */
    void deleteArticleById(Integer id);

    /**
     * 更新或新增文章
     * @param saveArticleVo
     */
    void saveOrUpdateArticle(SaveOrUpdateArticleVO saveArticleVo);

    /**
     * 分页查询主页文章
     * @param current
     * @return
     */
    PagedListHolder<UserArticleVo> listHomeArticle(Integer current);

    /**
     * 查询文章归档
     * @param current
     * @return
     */
    Page<ArchiveVo> getArchive(Integer current);

    Long getArticleCount();
}
