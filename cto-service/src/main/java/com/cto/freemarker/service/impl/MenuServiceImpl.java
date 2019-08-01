/*
 * @(#)  MenuVo.java    2019-06-05 10:16:11
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.service.impl;

import com.cto.freemarker.dao.MenuMapper;
import com.cto.freemarker.entity.Menu;
import com.cto.freemarker.service.MenuService;
import com.cto.freemarker.service.base.BaseServiceImpl;
import com.cto.freemarker.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名 MenuServiceImpl.java 
 * 
 * @author 1
 * @date 2019-06-05 10:16:11
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public MenuMapper getNameSpace() {
        return menuMapper;
    }

    @Override
    public Page<Menu> selectPage(Menu menu) {
        Page<Menu> page = new Page<>(menuMapper.selectPageCount(menu), menu.getPageSize(), menu.getPageNum());
        List<Menu> result = menuMapper.selectPageList(menu);
        page.setRows(result == null ? new ArrayList<>() : result);
        return page;
    }

    @Override
    public List<Menu> getMenuListByUserId(Long id) {
        return menuMapper.getMenuListByUserId(id);
    }

    @Override
    public List<Menu> getParentMenuListByUserId(Long id) {
        return menuMapper.getParentMenuListByUserId(id);
    }

    @Override
    public List<Menu> getChildMenuListByUserId(Long id) {
        return menuMapper.getChildMenuListByUserId(id);
    }

    @Override
    public List<Menu> getParentMenuListAll() {
        return menuMapper.getParentMenuListAll();
    }

    @Override
    public List<Menu> getChildMenuListAll() {
        return menuMapper.getChildMenuListAll();
    }
}