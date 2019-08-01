/*
 * @(#)  MenuVo.java    2019-06-05 10:16:11
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.service;

import com.cto.freemarker.entity.Menu;
import com.cto.freemarker.service.base.BaseService;
import com.cto.freemarker.utils.Page;

import java.util.List;

/**
 * 文件名 MenuService.java
 * 
 * @author 1
 * @date 2019-06-05 10:16:11
 */
public interface MenuService extends BaseService<Menu> {
    /**
     * 查询分页
     * @param menu
     * @return
     */
    Page<Menu> selectPage(Menu menu);

    /**
     * 根据用户ID获取菜单列表
     * @param id
     * @return
     */
    List<Menu> getMenuListByUserId(Long id);

    /**
     * 根据用户ID获取父节点菜单
     * @param id
     * @return
     */
    List<Menu> getParentMenuListByUserId(Long id);

    /**
     * 根据用户ID获取子节点菜单
     * @param id
     * @return
     */
    List<Menu> getChildMenuListByUserId(Long id);

    /**
     * 获取所有的父级菜单
     * @return
     */
    List<Menu> getParentMenuListAll();

    /**
     * 获取所有的子级菜单
     * @return
     */
    List<Menu> getChildMenuListAll();
}
