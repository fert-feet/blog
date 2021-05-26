package com.ling.serviceimpl;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/9
 * @ Time 8:50
 */

import com.ling.dao.RoleRepositry;
import com.ling.dao.UserRepositry;
import com.ling.pojo.Api;
import com.ling.pojo.Role;
import com.ling.pojo.User;
import com.ling.service.UserService;
import com.ling.vo.AddUserVo;
import com.ling.vo.UserAuthCountVo;
import com.ling.vo.UsersVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ling
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositry userRepositry;

    @Autowired
    private RoleRepositry roleRepositry;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public User findByUsername(String username) {
        return userRepositry.findByUsername(username);
    }

    @Override
    public boolean checkLogin(String username, String password) {
        var user =userRepositry.findByUsername(username);
        if (user==null){
            return false;
        }
        String userPassword=user.getPassword();
        return bCryptPasswordEncoder.matches(password,userPassword);
    }

    @Override
    public List<Api> getApiUrlByUsername(String username) {

        //可以优化
        var user =userRepositry.findByUsername(username);
        List<Role> roles=user.getRoles();
        List<Api> apisList=new ArrayList<>();
        for (Role role:roles){
            List<Api> apis=role.getApis();
            for (Api api:apis){
                apisList.add(api);
            }
        }
        return apisList;
    }

    /**
     * 复杂多条件查询，根据roleName和nickName查询分页用户
     * @param current
     * @param size
     * @param
     * @return
     */






    @Override
    public Page<UsersVo> findUsersPage(Integer current, Integer size, String roleName, String nickName) {
        Pageable pageable=PageRequest.of(current-1,size);
        //判断roleName和nickName存在情况
        if (StringUtils.hasLength(roleName)&&StringUtils.hasLength(nickName)){
            return userRepositry.findUsersPageByNicknameAndRoleName(roleName,nickName,pageable);
        }
        else if (StringUtils.hasLength(roleName)){
            return userRepositry.findUsersPageByRoleName(roleName,pageable);
        }
        else if (StringUtils.hasLength(nickName)){
            return userRepositry.findUsersPageByNickname(nickName,pageable);
        }
        else {
            return userRepositry.findAllUsersPage(pageable);
        }
    }

    @Override
    public List<UserAuthCountVo> getRoleCount(){
        return userRepositry.findRoleCount();
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addUser(AddUserVo addUserVo) {
        var user=new User();
        var role=roleRepositry.findByRoleName(addUserVo.getRoleName());
        String encodedPassword=bCryptPasswordEncoder.encode(addUserVo.getPassword());
        BeanUtils.copyProperties(addUserVo,user);
        //级联操作添加role
        try {
            user.getRoles().add(role);
            user.setPassword(encodedPassword);
            user.setUpdateTime(new Date());
            user.setCreateTime(new Date());
            userRepositry.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public User updateSilenceById(Boolean flag, Integer id) {
        var user=userRepositry.findById(id).orElse(null);
        if(user!=null){
            user.setIsSilence(flag);
            return userRepositry.save(user);
        }else {
            return null;
        }
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteUser(Integer id) {
        userRepositry.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUser(UsersVo usersVo) {
        var user=userRepositry.findById(usersVo.getId()).orElse(null);
            if (user!=null){
                BeanUtils.copyProperties(usersVo,user);
                user.getRoles().clear();
                Role role = roleRepositry.findByRoleName(usersVo.getRoleName());
                user.getRoles().add(role);
                user.setUpdateTime(new Date());
                userRepositry.save(user);
            }
    }
}
