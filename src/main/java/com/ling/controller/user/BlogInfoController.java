package com.ling.controller.user;/*
 * @ Created with IDEA
 * @ Author Ky2Fe
 * @ Date 2021/5/22
 * @ Time 18:49
 */

import com.ling.common.Result;
import com.ling.service.BlogInfoService;
import com.ling.vo.user.BlogInfoVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("博客信息模块")
@RestController
public class BlogInfoController {

    @Autowired
    private BlogInfoService blogInfoService;

    @GetMapping("/blogInfo/getBlogInfo")
    public Result getBlogInfo(){
        BlogInfoVo blogInfo = blogInfoService.getBlogInfo();
        return Result.ok().data("data",blogInfo);
    }
}
