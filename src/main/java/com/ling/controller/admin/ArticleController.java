package com.ling.controller.admin;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/5/1
 * @ Time 17:11
 */

import com.ling.common.Result;
import com.ling.pojo.Article;
import com.ling.service.ArticleService;
import com.ling.vo.AddArticleVo;
import com.ling.vo.ArticleVo;
import com.ling.vo.SaveOrUpdateArticleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "文章模块")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "根据文章标题分页查询文章列表")
    @GetMapping("/listArticle")
    public Result listArticle(@RequestParam(value = "current",required = true,defaultValue = "1") Integer current,
                              @RequestParam(value = "size",required = true,defaultValue = "5") Integer size,
                              @RequestParam(value = "articleTitle",required = false) String articleTitle){

        PagedListHolder<ArticleVo> articleListPage=articleService.listArticle(current,size,articleTitle);
        List<ArticleVo> articleList=articleListPage.getSource();
        int total=articleListPage.getNrOfElements();
        return Result.ok().data("data",articleList).data("total",total);
    }

    @ApiOperation(value = "根据Id查找文章")
    @GetMapping("/getArticleById")
    public Result getArticleById(@RequestParam(value = "articleId") Integer id){
        AddArticleVo article=articleService.getAddArticleInfo(id);
        return Result.ok().data("data",article);
    }

    @ApiOperation(value = "改变文章置顶状态")
    @PutMapping("/updateIsTopById")
    public Result updateIsTopById(Boolean flag,Integer id){
        articleService.updateIsTopById(flag,id);
        return Result.ok();
    }

    @ApiOperation(value = "删除文章")
    @DeleteMapping("/deleteArticleById")
    public Result deleteById(@RequestParam(value = "articleId") Integer id){
        articleService.deleteArticleById(id);
        return Result.ok();
    }


    @ApiOperation(value = "新增或编辑文章")
    @PostMapping("/saveOrUpdateArticle")
    public Result saveOrUpdateArticle(@RequestBody SaveOrUpdateArticleVO saveOrUpdateArticleVO){
        articleService.saveOrUpdateArticle(saveOrUpdateArticleVO);
        return Result.ok();
    }
}
