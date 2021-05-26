package com.ling.dao;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/24
 * @ Time 18:05
 */

import com.ling.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author FEET
 */
public interface RoleRepositry extends JpaRepository<Role,Integer> {

    /**
     * 根据角色名查找Role
     * @param roleName
     * @return
     */
    Role findByRoleName(String roleName);
}
