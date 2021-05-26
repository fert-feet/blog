package com.ling.service;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/9
 * @ Time 8:48
 */

import com.ling.pojo.Api;
import com.ling.pojo.Role;
import com.ling.pojo.User;
import com.ling.vo.AddUserVo;
import com.ling.vo.UserAuthCountVo;
import com.ling.vo.UsersVo;
import org.springframework.data.domain.Page;
import springfox.documentation.spring.web.json.Json;

import java.util.List;


public interface UserService {

    /**
     * 根据用户名查找用户数据
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 登录验证
     * @param username
     * @param password
     * @return
     */
    boolean checkLogin(String username, String password);


    /**
     * 根据用户名查找权限表
     * @param username
     * @return Api
     */
    List<Api> getApiUrlByUsername(String username);

    /**
     * 分页查询用户
     * @param current
     * @param size
     * @return
     */
    Page<UsersVo> findUsersPage(Integer current, Integer size,String roleName,String nickName);

    /**
     * 更改用户禁言状态
     * @param flag
     * @param id
     * @return
     */
    User updateSilenceById(Boolean flag,Integer id);

    /**
     * 删除用户
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 更新
     * @param usersVo
     */
    void updateUser(UsersVo usersVo);

    /**
     * 角色计数
     * @return
     */
    List<UserAuthCountVo> getRoleCount();

    void addUser(AddUserVo addUserVo);

}
