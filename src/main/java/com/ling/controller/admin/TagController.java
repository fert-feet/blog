package com.ling.controller.admin;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/5/4
 * @ Time 16:48
 */

import com.ling.common.Result;
import com.ling.pojo.Tag;
import com.ling.service.TagService;
import com.ling.vo.TagVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("标签模块")
@RequestMapping("/tag")
@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    @ApiOperation("获取标签列表")
    @GetMapping("/getTagList")
    public Result getTagList(){
        List<Tag> tags=tagService.listTag();
        return Result.ok().data("data",tags);
    }

    @ApiOperation("分页获取标签")
    @GetMapping("/listTags")
    public Result listTags(@RequestParam(value = "current",required = true,defaultValue = "1") Integer current,
                           @RequestParam(value = "size",required = true,defaultValue = "5") Integer size,
                           @RequestParam(value = "tagName",required = false) String tagName){
        Page<Tag> tags=tagService.listTagPage(current,size,tagName);
        List<Tag> content = tags.getContent();
        long totalElements = tags.getTotalElements();
        return Result.ok().data("data",content).data("total",totalElements);
    }

    @ApiOperation("删除标签")
    @DeleteMapping("/deleteTag")
    public Result deleteTag(@RequestParam(value = "tagId") Integer id){
        tagService.deleteById(id);
        return Result.ok();
    }

    @ApiOperation("更新标签")
    @PostMapping("/addOrEditTag")
    public Result addOrEditTag(@RequestBody TagVo tagVo){
        tagService.updateById(tagVo);
        return Result.ok();
    }
}
