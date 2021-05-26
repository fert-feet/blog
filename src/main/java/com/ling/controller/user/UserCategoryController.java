package com.ling.controller.user;/*
 * @ Created with IDEA
 * @ Author Ky2Fe
 * @ Date 2021/5/25
 * @ Time 22:33
 */

import com.ling.common.Result;
import com.ling.service.CategoryService;
import com.ling.vo.CategoryVo;
import com.ling.vo.user.UserArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class UserCategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/listCategories")
    public Result listCategories(){
        List<CategoryVo> categoryVos = categoryService.listCategoryVo();
        Long categoryCount = categoryService.getCategoryCount();
        return Result.ok().data("data",categoryVos).data("count",categoryCount);
    }

    @GetMapping("/{categoryId}")
    public Result categoryArticle(@PathVariable(value = "categoryId") Integer categoryId,@RequestParam(value = "current") Integer current){
        PagedListHolder<UserArticleVo> userArticleVoPage= categoryService.listArticleVoByCategoryId(categoryId, current);
        List<UserArticleVo> articleVoList = userArticleVoPage.getPageList();
        int pageCount = userArticleVoPage.getPageCount();
        if (current>pageCount) {
            articleVoList.clear();
        }
        return Result.ok().data("data",articleVoList);
    }
}
