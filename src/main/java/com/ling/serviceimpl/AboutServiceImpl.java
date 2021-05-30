package com.ling.serviceimpl;/*
 * @ Created with IDEA
 * @ Author Ky2Fe
 * @ Date 2021/5/27
 * @ Time 0:44
 */

import com.ling.dao.AboutRepositry;
import com.ling.pojo.About;
import com.ling.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AboutServiceImpl implements AboutService {

    @Autowired
    private AboutRepositry aboutRepositry;

    @Override
    public About getAbout() {

        return aboutRepositry.getAbout();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateAbout(String content) {
        About about = aboutRepositry.getAbout();
        about.setContent(content);
        aboutRepositry.save(about);
    }
}
