package com.ling.config;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/6
 * @ Time 16:15
 */

import com.ling.auth.MyUserDetailsService;
import com.ling.auth.MyUsernamePasswordAuthenticationFilter;
import com.ling.handler.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;


    /**
     *Security配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //放行所有Login请求
        http.formLogin().loginProcessingUrl("/login").permitAll();
        http.csrf().disable().exceptionHandling();
        //解决跨域问题
        http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
        http.headers().frameOptions().disable();
        http.logout().logoutUrl("/logout").logoutSuccessHandler(myLogoutSuccessHandler);
        http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler).authenticationEntryPoint(myAuthenticationEntryPoint);
        //权限处理和认证失败处理
        http.addFilterAt(myUsernamePasswordAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);

        //注册放行
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/user/register").permitAll()
                .anyRequest()
                .access("@dynamicPermission.checkPermission(authentication)").and().csrf().disable();

    }


    /**
     *
     * @return 转换为读取json格式的账号密码
     */
    @Bean
    public MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception {
        MyUsernamePasswordAuthenticationFilter filter=new MyUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }
}
