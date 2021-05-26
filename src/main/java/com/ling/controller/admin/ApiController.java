package com.ling.controller.admin;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/28
 * @ Time 20:48
 */

import com.ling.common.Result;
import com.ling.pojo.Api;
import com.ling.service.ApiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@io.swagger.annotations.Api("Api模块")
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;


    @ApiOperation("分类条件查询查询api的信息,父pid查询模块所有api")
    @GetMapping("/listApiInfoBack")
    public Result listApiInfoBack(@RequestParam(value = "current",required = true,defaultValue = "1") Integer current,
                                  @RequestParam(value = "size",required = true,defaultValue = "100") Integer size,
                                  @RequestParam(value = "apiId",required = false) Integer apiId){
        Page<Api> apiPage=apiService.listApiPage(current,size,apiId);
        List<Api> recordList=apiPage.getContent();
        Integer total=apiPage.getTotalPages();
        return Result.ok().data("recordList",recordList).data("total",total);
    }


}
