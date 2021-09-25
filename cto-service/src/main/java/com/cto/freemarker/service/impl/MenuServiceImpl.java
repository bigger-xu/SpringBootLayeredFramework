package com.cto.freemarker.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cto.freemarker.entity.Menu;
import com.cto.freemarker.entity.query.MenuQuery;
import com.cto.freemarker.mapper.MenuMapper;
import com.cto.freemarker.service.IMenuService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author Bigger-Xu
 * @since 2020-09-19
 */
@Slf4j
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Resource
    private MenuMapper menuMapper;

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
    public IPage<Menu> selectPage(MenuQuery search) {
        return menuMapper.selectPageVo(new Page<Menu>(search.getPageNum(),search.getPageSize()),search);
    }
}
