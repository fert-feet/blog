package com.ling.auth;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/5
 * @ Time 23:14
 */

import com.ling.pojo.Role;
import com.ling.pojo.User;
import com.ling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.isEmpty()){
            throw new UsernameNotFoundException("用户名为空");
        }
        User user=userService.findByUsername(username);
        if (user!=null){
            List<GrantedAuthority> authorities=new ArrayList<>();
            List<Role> roles=user.getRoles();
            SimpleGrantedAuthority simpleGrantedAuthority=null;
            for (Role role:roles){
                simpleGrantedAuthority =new SimpleGrantedAuthority(role.getRoleName());
                authorities.add(simpleGrantedAuthority);
            }
            MyUserDetails myUserDetails=new MyUserDetails();
            myUserDetails.setUser(user);
            myUserDetails.setAuthorities(authorities);
            return myUserDetails;
        }else {
            throw new UsernameNotFoundException("没有该用户");
        }

    }
}
