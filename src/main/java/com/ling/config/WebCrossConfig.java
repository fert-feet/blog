package com.ling.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebCrossConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
           registry.addMapping("/**")
                   .allowCredentials(true)
                   .allowedMethods("*")
                   .allowedHeaders("*")
                   .allowedOriginPatterns("*")
                   .maxAge(3600);
    }



}
