package com.ling.controller.admin;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/5/4
 * @ Time 16:48
 */

import com.ling.common.Result;
import com.ling.pojo.Category;
import com.ling.service.CategoryService;
import com.ling.vo.CategoryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("分类模块")
@RequestMapping("/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("获取分类列表")
    @GetMapping("/getCategoryList")
    public Result getTagList(){
        List<Category> categories = categoryService.listCategory();
        return Result.ok().data("data",categories);
    }

    @ApiOperation("分页获取分类")
    @GetMapping("/listCategory")
    public Result categoryList(@RequestParam(value = "current",required = true,defaultValue = "1") Integer current,
                           @RequestParam(value = "size",required = true,defaultValue = "5") Integer size,
                           @RequestParam(value = "categoryName",required = false) String categoryName){
        Page<Category> categories = categoryService.listCategoryPage(current, size, categoryName);
        List<Category> content = categories.getContent();
        long totalElements = categories.getTotalElements();
        return Result.ok().data("data",content).data("total",totalElements);
    }

    @ApiOperation("删除分类")
    @DeleteMapping("/deleteCategory")
    public Result deleteCategory(@RequestParam(value = "categoryId") Integer id){
        categoryService.deleteById(id);
        return Result.ok();
    }

    @ApiOperation("更新或添加分类")
    @PostMapping("/addOrEditCategory")
    public Result addOrEditTag(@RequestBody CategoryVo categoryVo){
        categoryService.updateCategory(categoryVo);
        return Result.ok();
    }
}
