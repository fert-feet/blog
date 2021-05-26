package com.ling.controller.admin;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/18
 * @ Time 0:13
 */

import com.ling.common.Result;
import com.ling.pojo.Menu;
import com.ling.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "侧边菜单模块")
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "获取菜单展示列表")
    @GetMapping("/getMenuList")
    public Result getMenuList(){
        List<Menu> menuList=menuService.listMenu();
        if (menuList==null){
            return Result.error().message("无权限");
        }else {
            return Result.ok().data("menuList",menuList);
        }
    }
}
