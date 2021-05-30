package com.ling.service;/*
 * @ Created with IDEA
 * @ Author Ky2Fe
 * @ Date 2021/5/27
 * @ Time 0:44
 */

import com.ling.pojo.About;

public interface AboutService {
    About getAbout();

    void updateAbout(String content);
}
