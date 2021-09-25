package com.cto.freemarker.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cto.freemarker.entity.Menu;
import com.cto.freemarker.entity.query.MenuQuery;

/**
 * <p>
 * 系统菜单表 服务类
 * </p>
 *
 * @author Bigger-Xu
 * @since 2020-09-19
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 根据用户获取菜单
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

    IPage<Menu> selectPage(MenuQuery search);

}
