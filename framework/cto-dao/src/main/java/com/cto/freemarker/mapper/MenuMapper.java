package com.cto.freemarker.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cto.freemarker.entity.Menu;
import com.cto.freemarker.entity.query.MenuQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 系统菜单表 Mapper 接口
 * </p>
 *
 * @author Bigger-Xu
 * @since 2020-09-19
 */
public interface MenuMapper extends BaseMapper<Menu> {
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
    /**
     * 分页
     * @param search
     * @return
     */
    IPage<Menu> selectPageVo(IPage<Menu> menuPage, @Param("search") MenuQuery search);


}
