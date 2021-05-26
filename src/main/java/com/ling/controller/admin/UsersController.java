package com.ling.controller.admin;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/19
 * @ Time 20:33
 */

import com.ling.common.Result;
import com.ling.pojo.User;
import com.ling.service.RoleService;
import com.ling.service.UserService;
import com.ling.vo.AddUserVo;
import com.ling.vo.UserAuthCountVo;
import com.ling.vo.UsersVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "用户模块")
@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "根据用户角色和昵称分页查询用户列表")
    @GetMapping("/getUserList")
    public Result listUsers(@RequestParam(value = "current",required = true,defaultValue = "1") Integer current,
                            @RequestParam(value = "size",required = true,defaultValue = "5") Integer size,
                            @RequestParam(value = "roleName",required = false) String roleName,
                            @RequestParam(value = "nickname",required = false) String nickname){
        Page<UsersVo> page=userService.findUsersPage(current,size,roleName,nickname);
        List<String> roleList=roleService.findAllRoles();
        Long total=page.getTotalElements();
        List<UsersVo> data=page.getContent();
        List<UserAuthCountVo> userAuthList=userService.getRoleCount();
        return Result.ok().data("data",data).data("total",total).data("userAuthList",userAuthList).data("userRoleList",roleList);
    }

    @ApiOperation(value = "改变用户禁言状态")
    @PutMapping("/updateSilenceById")
    public Result updateSilenceById(Boolean flag,Integer id){
        User user=userService.updateSilenceById(flag,id);
        Boolean isSilence = user.getIsSilence();
        return Result.ok().data("flag",isSilence);
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/deleteUser")
    public Result deleteById(Integer id){
        userService.deleteUser(id);
        return Result.ok();
    }

    @ApiOperation(value = "更新用户信息")
    @PutMapping("/updateUserById")
    public Result updateUser(@RequestBody UsersVo usersVo){
        userService.updateUser(usersVo);
        return Result.ok();
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/addUser")
    public Result addUser(@RequestBody AddUserVo addUserVo){
        userService.addUser(addUserVo);
        return Result.ok();
    }




}
