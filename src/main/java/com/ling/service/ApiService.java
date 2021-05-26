package com.ling.service;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/30
 * @ Time 9:28
 */

import com.ling.pojo.Api;
import org.springframework.data.domain.Page;


public interface ApiService {
    Page<Api> listApiPage(Integer current,Integer size,Integer apiId);
}
