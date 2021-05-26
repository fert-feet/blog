package com.ling.serviceimpl;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/30
 * @ Time 9:28
 */

import com.ling.dao.ApiRepositry;
import com.ling.pojo.Api;
import com.ling.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiRepositry apiRepositry;

    @Override
    public Page<Api> listApiPage(Integer current, Integer size, Integer apiId) {
        Pageable pageable= PageRequest.of(current-1,size);
        return apiRepositry.findAll(pageable);

    }
}
