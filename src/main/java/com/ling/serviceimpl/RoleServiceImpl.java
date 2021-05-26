package com.ling.serviceimpl;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/24
 * @ Time 18:07
 */

import com.ling.dao.ApiRepositry;
import com.ling.dao.RoleRepositry;
import com.ling.pojo.Api;
import com.ling.pojo.Role;
import com.ling.service.RoleService;
import com.ling.vo.ApiIdVo;
import com.ling.vo.ApiRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepositry roleRepositry;

    @Autowired
    private ApiRepositry apiRepositry;

    @Override
    public List<String> findAllRoles() {
        List<Role> roles = roleRepositry.findAll();
        List<String> roleList=new ArrayList<>();
        for (Role role:roles){
            roleList.add(role.getRoleName());
        }
        return roleList;
    }

    @Override
    public List<Role> listRole() {
        return roleRepositry.findAll();
    }

    @Override
    public List<Integer> getApiIdByUserRoleId(Integer roleId) {
        List<ApiIdVo> list=apiRepositry.findApiIdByRoleId(roleId);
        List<Integer> apiIdList=new ArrayList<>();
        for (ApiIdVo apiIdVo:list){
            apiIdList.add(apiIdVo.getId());
        }
        return apiIdList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveApi(Integer roleId, List<ApiRoleVo> apiRoleVoList) {
        var role=roleRepositry.findById(roleId).orElse(null);
        List<Api> apiList=new ArrayList<>();
        if (role!=null){
            for (ApiRoleVo apiRoleVo:apiRoleVoList){
                var api=apiRepositry.findById(apiRoleVo.getApiId()).orElse(null);
                apiList.add(api);
            }
            role.getApis().clear();
            role.getApis().addAll(apiList);
        }
    }
}
