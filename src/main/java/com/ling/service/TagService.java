package com.ling.service;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/5/4
 * @ Time 16:50
 */

import com.ling.pojo.Tag;
import com.ling.vo.TagVo;
import com.ling.vo.user.UserArticleVo;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TagService {

    /**
     * 查询所有标签
     * @return
     */
    List<Tag> listTag();

    /**
     * 查询展示页分标签
     * @return
     */
    List<TagVo> listTagVo();

    /**
     * 查询所有标签分页
     * @param current
     * @param size
     * @param tagName
     * @return
     */
    Page<Tag> listTagPage(Integer current, Integer size, String tagName);

    /**
     * 查询标签总数
     * @return
     */
    Long getTagCount();

    /**
     * 删除标签
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 更新标签
     * @param tagVo
     */
    void updateById(TagVo tagVo);

    PagedListHolder<TagVo> listArticleVoByTagId(Integer tagId, Integer current);

}
