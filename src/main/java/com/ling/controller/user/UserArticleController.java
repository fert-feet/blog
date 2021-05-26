package com.ling.controller.user;/*
 * @ Created with IDEA
 * @ Author Ky2Fe
 * @ Date 2021/5/22
 * @ Time 16:39
 */

import com.ling.common.Result;
import com.ling.pojo.Article;
import com.ling.service.ArticleService;
import com.ling.vo.user.ArchiveVo;
import com.ling.vo.user.UserArticleVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("首页文章模块")
@RestController
public class UserArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/article/articles")
    public Result HomeArticleList(@RequestParam(value = "current",required = true,defaultValue = "1") Integer current){
        PagedListHolder<UserArticleVo> articleListPage = articleService.listHomeArticle(current);
        List<UserArticleVo> articleList = articleListPage.getSource();
        int pageCount = articleListPage.getPageCount();
        if (current>pageCount){
            articleList.clear();
        }
        return Result.ok().data("data",articleList);
    }

    @GetMapping("/articles/{articleId}")
    public Result getArticle(@PathVariable(value = "articleId") Integer articleId){
        Article article = articleService.getArticleById(articleId);
        return Result.ok().data("data",article);
    }

    @GetMapping("articles/archives")
    public Result getArchive(Integer current){
        Page<ArchiveVo> archive = articleService.getArchive(current);
        List<ArchiveVo> content = archive.getContent();
        Long articleCount = articleService.getArticleCount();
        return Result.ok().data("data",content).data("count",articleCount);
    }
}
