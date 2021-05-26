package com.ling.dao;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/5/1
 * @ Time 17:21
 */

import com.ling.pojo.Article;
import com.ling.vo.ArticleVo;
import com.ling.vo.user.ArchiveVo;
import com.ling.vo.user.UserArticleVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ArticleRepositry extends JpaRepository<Article,Integer> {
    @Query(value = "select new com.ling.vo.ArticleVo(a.id,a.articleTitle,a.category.categoryName,b.tagName,a.createTime,a.updateTime,a.isTop) from Article a inner join a.tags b where a.articleTitle like %:articleTitle%")
    List<ArticleVo> findArticleListByTitle(@Param("articleTitle") String title);

    @Query(value = "select new com.ling.vo.ArticleVo(a.id,a.articleTitle,a.category.categoryName,b.tagName,a.createTime,a.updateTime,a.isTop) from Article a inner join a.tags b")
    List<ArticleVo> findArticleList();

    @Query(value = "select new com.ling.vo.user.UserArticleVo(a.id,a.articleTitle,a.articleContent,a.articleCover,a.isTop,a.createTime,b.id,b.categoryName,c.id,c.tagName) from Article a inner join a.category b inner join a.tags c")
    List<UserArticleVo> findHomeArticleList();

    @Query(value = "select new com.ling.vo.user.ArchiveVo(a.id,a.createTime,a.articleTitle) from Article a")
    Page<ArchiveVo> findArchive(Pageable pageable);

    @Query(value = "select new com.ling.vo.user.UserArticleVo(a.id,a.articleTitle,a.articleContent,a.articleCover,a.isTop,a.createTime,b.id,b.categoryName,c.id,c.tagName) from Article a inner join a.category b inner join a.tags c where b.id= :categoryId")
    List<UserArticleVo> findArticleByCategory(@Param("categoryId") Integer categoryId);
    @Query(value = "select new com.ling.vo.user.UserArticleVo(a.id,a.articleTitle,a.articleContent,a.articleCover,a.isTop,a.createTime,b.id,b.categoryName,c.id,c.tagName) from Article a inner join a.category b inner join a.tags c where c.id= :tagId")
    List<UserArticleVo> findArticleByTag(@Param("tagId") Integer tagId);
}
