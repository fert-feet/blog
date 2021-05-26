package com.ling.serviceimpl;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/18
 * @ Time 0:28
 */

import com.ling.dao.MenuRepositry;
import com.ling.pojo.Menu;
import com.ling.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepositry menuRepositry;


    /**
     * 展示树形菜单操作
     * @return
     */
    @Override
    public List<Menu> listMenu() {
        //找到parentId为0的字段，即父字段
        List<Menu> parentList=menuRepositry.findAllByParentIdEquals(0);
        //找到parentId大于0的字段，即子字段
        List<Menu> childList=menuRepositry.findAllByParentIdGreaterThan(0);
        //分别遍历父子字段，找到子字段的parentId和父字段的menuId相等的字段
        for (Menu parentMenu:parentList){
            Integer parentMenuId = parentMenu.getMenuId();
            for (Menu childMenu:childList){
                Integer childMenuParentId = childMenu.getParentId();
                if (childMenuParentId.equals(parentMenuId)){
                    //将父子段的children属性和查出来的子字段合并
                    parentMenu.getChildren().add(childMenu);
                }
            }
        }
        //
        return parentList;
    }
}
