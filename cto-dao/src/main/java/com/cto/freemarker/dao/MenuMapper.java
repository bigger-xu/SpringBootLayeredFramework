/*
 * @(#)  MenuVo.java    2019-06-05 10:16:11
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.dao;

import com.cto.freemarker.dao.base.BaseDao;
import com.cto.freemarker.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文件名 MenuMapper.java
 *
 * @author 1
 * @date 2019-06-05 10:16:11
 */
@Repository
public interface MenuMapper extends BaseDao<Menu> {
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