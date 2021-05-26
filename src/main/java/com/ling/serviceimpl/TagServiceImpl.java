package com.ling.serviceimpl;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/5/4
 * @ Time 16:50
 */

import com.ling.dao.ArticleRepositry;
import com.ling.dao.TagRepositry;
import com.ling.handler.exception.MyRuntimeException;
import com.ling.pojo.Tag;
import com.ling.service.TagService;
import com.ling.utils.ArticleUtils;
import com.ling.vo.TagVo;
import com.ling.vo.user.UserArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepositry tagRepositry;

    @Autowired
    private ArticleRepositry articleRepositry;

    @Autowired
    private ArticleUtils articleUtils;

    @Override
    public List<Tag> listTag() {
        return tagRepositry.findAll();
    }

    @Override
    public List<TagVo> listTagVo() {
        return tagRepositry.findTags();
    }

    @Override
    public Page<Tag> listTagPage(Integer current, Integer size, String tagName) {
        Pageable pageable= PageRequest.of(current-1,size);
        if (StringUtils.hasLength(tagName)){
            return tagRepositry.findByTagNameLike("%"+tagName+"%",pageable);
        }else {
            return tagRepositry.findAll(pageable);
        }
    }

    @Override
    public Long getTagCount() {
        return tagRepositry.count();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Integer id) {
        try {
            tagRepositry.deleteById(id);
        } catch (Exception e) {
            throw new MyRuntimeException();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateById(TagVo tagVo) {
        Integer tagId = tagVo.getId();
        String tagName = tagVo.getTagName();
        if (tagId!=null){
            Tag tag = tagRepositry.findById(tagId).orElse(null);
            tag.setTagName(tagName);
            tagRepositry.save(tag);
        }else {
            Tag tag = new Tag();
            tag.setTagName(tagName);
            tag.setUpdateTime(new Date());
            tag.setCreateTime(new Date());
            tagRepositry.save(tag);
        }
    }

    @Override
    public PagedListHolder<TagVo> listArticleVoByTagId(Integer tagId, Integer current) {
        List<UserArticleVo> articleList = articleRepositry.findArticleByTag(tagId);
        return articleUtils.changeToHomePage(articleList,current);
    }
}
