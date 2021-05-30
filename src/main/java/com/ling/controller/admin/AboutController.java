package com.ling.controller.admin;/*
 * @ Created with IDEA
 * @ Author Ky2Fe
 * @ Date 2021/5/27
 * @ Time 0:43
 */

import com.ling.common.Result;
import com.ling.pojo.About;
import com.ling.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AboutController {

    @Autowired
    private AboutService aboutService;

    @GetMapping("/getAbout")
    public Result getAbout(){
        About about = aboutService.getAbout();
        return Result.ok().data("data",about);
    }

    @PutMapping("/updateAbout")
    public Result updateAbout(@RequestParam(value = "aboutContent") String content){
        aboutService.updateAbout(content);
        return Result.ok();
    }
}
