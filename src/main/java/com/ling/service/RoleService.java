package com.ling.service;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/24
 * @ Time 18:05
 */

import com.ling.pojo.Role;
import com.ling.vo.ApiIdVo;
import com.ling.vo.ApiRoleVo;

import java.util.List;

public interface RoleService {
    /**
     * 找到所有roleName
     * @return
     */
    List<String> findAllRoles();

    /**
     * 找到所有role
     * @return
     */
    List<Role> listRole();

    /**
     * 找到对应role分配的api菜单的apiId
     * @return
     */
    List<Integer> getApiIdByUserRoleId(Integer roleId);

    void saveApi(Integer roleId, List<ApiRoleVo> apiRoleVoList);
}
