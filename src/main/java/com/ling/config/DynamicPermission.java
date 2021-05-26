package com.ling.config;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/17
 * @ Time 15:49
 */

import com.alibaba.fastjson.JSON;
import com.ling.handler.auth.MyaccessDeniedException;
import com.ling.pojo.Api;
import com.ling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Component
public class DynamicPermission {
    private static final String PERMISSION_KEY="PERMISSION_KEY";
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private UserService userService;

    public boolean checkPermission(Authentication authentication)throws MyaccessDeniedException{
        String name=authentication.getName();
        //给匿名用户get权限
        if (name.equals("anonymousUser")){
            String method=request.getMethod();
            if (method.equals("GET")){
                return true;
            }else {
                throw new MyaccessDeniedException("非法操作");
            }
        }
        //获取用户认证信息
        Object principal=authentication.getPrincipal();
        if (principal instanceof UserDetails){
            var userDetails= (UserDetails) principal;
            //获取username
            String username=userDetails.getUsername();
            //通过账号获取权限表
            List<Api> apiUrls=getApiByUserName(username);
            //获取当前访问url
            String requestUrl=request.getRequestURI();
            //获取提交类型
            String urlMethod=request.getMethod();
            //判断当前url在不在权限表里
            var hashAntPath=false;
            int hashMethod=-1;
            var antPathMatcher=new AntPathMatcher();
            for (Api item:apiUrls){
                if (antPathMatcher.match(item.getUrl(),requestUrl)){
                    hashAntPath=true;
                }
                hashMethod=item.getMethod().toUpperCase().indexOf(urlMethod.toUpperCase());
                if(hashAntPath && hashMethod!=-1){
                    break;
                }
            }
            boolean res=hashAntPath && hashMethod!=-1;
            if (res){
                return res;
            }else {
                throw new MyaccessDeniedException("用户权限不足");
            }

        }else {
            throw  new MyaccessDeniedException("不是UserDetails类型");
        }
    }

    private List<Api> getApiByUserName(String username) {
        List<Api> urlApis=null;
        String key=PERMISSION_KEY+"_"+username;
        String api= (String) redisTemplate.opsForValue().get(key);
        if (StringUtils.hasLength(api)){
            urlApis= JSON.parseArray(api,Api.class);
            System.out.println(urlApis);
            return urlApis;
        }
        List<Api> apis=userService.getApiUrlByUsername(username);
        //加入缓存并设置过期时间
        redisTemplate.opsForValue().set(key,JSON.toJSONString(apis), Duration.ofSeconds(1800L));
        return apis;
    }
}
