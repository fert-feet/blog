package com.ling.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ling.handler.auth.MyAuthenticationException;
import com.ling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;


public class MyUsernamePasswordAuthenticationFilter  extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private UserService userService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        //携带头要是json格式得application/json;charset=UTF-8
        //前后端分离 这里是接收的是json串
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
                || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)){
            ObjectMapper objectMapper = new ObjectMapper();
            UsernamePasswordAuthenticationToken authRequest = null;
            Map<String, String> authenticationBean = null;

            try (InputStream is = request.getInputStream()) {
                //字符串转集合
                authenticationBean = objectMapper.readValue(is, Map.class);
            } catch (IOException e) {
                //将异常放到自定义的异常类中
                System.out.println(e.getMessage());
                throw new MyAuthenticationException(e.getMessage());
            }
            try {
                if (!authenticationBean.isEmpty()) {
                    //获得账号、密码
                    String username = authenticationBean.get(SPRING_SECURITY_FORM_USERNAME_KEY);
                    String password = authenticationBean.get(SPRING_SECURITY_FORM_PASSWORD_KEY);
                    //可以验证账号、密码
//                System.out.println("username = " + username);
//                System.out.println("password = " + password);
                    //检测账号、密码是否存在
                    if (userService.checkLogin(username, password)) {
                        //将账号、密码装入UsernamePasswordAuthenticationToken中
                        authRequest = new UsernamePasswordAuthenticationToken(username, password);
                        setDetails(request, authRequest);
                        return this.getAuthenticationManager().authenticate(authRequest);

                    }
                }
            } catch (AuthenticationException e) {
                e.printStackTrace();
                throw new MyAuthenticationException(e.getMessage());
            }
            throw new MyAuthenticationException("用户或者密码错误");
        }
        return this.attemptAuthentication(request,response);
    }


}
