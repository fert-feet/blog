package com.ling.controller.user;/*
 * @ Created with IDEA
 * @ Author Ky2Fe
 * @ Date 2021/5/26
 * @ Time 19:46
 */

import com.ling.common.Result;
import com.ling.service.TagService;
import com.ling.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class UserTagComtroller {

    @Autowired
    private TagService tagService;



    @GetMapping("/tags")
    public Result listTags(){
        List<TagVo> tagVoList = tagService.listTagVo();
        Long tagCount = tagService.getTagCount();
        return Result.ok().data("data",tagVoList).data("count",tagCount);
    }

    @GetMapping("/{tagId}")
    public Result categoryArticle(@PathVariable(value = "tagId") Integer tagId, @RequestParam(value = "current") Integer current){
        PagedListHolder<TagVo> tagVoPage = tagService.listArticleVoByTagId(tagId, current);
        List<TagVo> pageList = tagVoPage.getPageList();
        int pageCount = tagVoPage.getPageCount();
        if (current>pageCount) {
            pageList.clear();
        }
        return Result.ok().data("data",pageList);
    }

}
