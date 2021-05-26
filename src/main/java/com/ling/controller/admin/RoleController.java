package com.ling.controller.admin;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/28
 * @ Time 17:16
 */

import com.ling.common.Result;
import com.ling.pojo.Role;
import com.ling.service.RoleService;
import com.ling.vo.ApiIdVo;
import com.ling.vo.ApiRoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("角色模块")
@RequestMapping("/role")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;


    @ApiOperation("查找角色字段")
    @GetMapping("/getRoleList")
    public Result getRoleList(){
        List<Role> roles=roleService.listRole();
        return Result.ok().data("data",roles);
    }

    @ApiOperation("根据角色查找api")
    @GetMapping("/getAssignedApiIdByUserRoleId")
    public Result getAssignedApiIdByUserRoleId(Integer actionId){
        List<Integer> apiId=roleService.getApiIdByUserRoleId(actionId);
        return Result.ok().data("data",apiId);
    }

    @ApiOperation("保存api更改")
    @PostMapping("/saveRolePermissionList")
    public Result saveApi(@RequestParam(value = "roleId") Integer roleId, @RequestBody List<ApiRoleVo> apiRoleVoList){
        try {
            roleService.saveApi(roleId,apiRoleVoList);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }

    }
}
