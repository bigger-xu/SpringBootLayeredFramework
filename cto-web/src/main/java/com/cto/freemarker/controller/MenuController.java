/*
 * @(#)  MenuController.java    2019-06-06 12:08:40
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cto.freemarker.controller.base.BaseController;
import com.cto.freemarker.entity.AdminUser;
import com.cto.freemarker.entity.CustomLogs;
import com.cto.freemarker.entity.Menu;
import com.cto.freemarker.entity.RoleMenu;
import com.cto.freemarker.entity.common.MenuTreeUtil;
import com.cto.freemarker.entity.common.TreeNode;
import com.cto.freemarker.entity.query.MenuQuery;
import com.cto.freemarker.enums.CustomLogsType;
import com.cto.freemarker.service.IMenuService;
import com.cto.freemarker.service.IRoleMenuService;
import com.cto.freemarker.utils.Result;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 文件名MenuController.java
 *
 * @author Zhang Yongwei
 * @date 2019-06-06 12:08:40
 */
@Controller
@RequestMapping("menu")
public class MenuController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IRoleMenuService roleMenuService;

    /**
     * 获取系统菜单表列表页
     * @return
     */
    @RequestMapping
    @RequiresPermissions("menu")
    public String index(Model model) {
        return "menu/index";
    }

    /**
     * 获取系统菜单表分页数据
     *
     * @param search 查询条件
     * @return
     */
    @RequestMapping("page")
    @ResponseBody
    public Object list(MenuQuery search) {
        //TODO 设置查询属性
        return menuService.page(new Page<>(search.getPageNum(),search.getPageSize()),Wrappers.<Menu>lambdaQuery().eq(Menu::getAddUserId,getCurrentUser().getId()));
    }


    /**
     * 获取系统菜单表添加页
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(Model model) {
        List<Menu> parentMenu = menuService.list(Wrappers.<Menu>lambdaQuery().eq(Menu::getParentId,0L).eq(Menu::getDeleteFlag,0));
        model.addAttribute("parentMenu",parentMenu);
        return "menu/add";
    }

    /**
     * 获取系统菜单表编辑页
     * @return
     */
    @RequestMapping(value = "/edit")
    @CustomLogs(description = "修改菜单",type = CustomLogsType.UPDATE)
    public String edit(Long id, Model model) {
        List<Menu> parentMenu = menuService.list(Wrappers.<Menu>lambdaQuery().eq(Menu::getParentId,0L).eq(Menu::getDeleteFlag,0));
        model.addAttribute("parentMenu",parentMenu);
        if(id != null){
            Menu menu = menuService.getById(id);
            model.addAttribute("menu", menu);
        }
        return "menu/edit";
    }

    /**
     * 创建或者更新系统菜单表
     * @param menu 系统菜单表对象
     * @return
     */
    @CustomLogs(description = "修改菜单成功",type = CustomLogsType.UPDATE)
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Object saveOrUpdate(Menu menu) {
        if (menu.getId() == null) {
            menu.setDeleteFlag(0);
            menu.setAddUserId(getCurrentUser().getId());
            menuService.save(menu);
        } else {
            menuService.updateById(menu);
        }
        return Result.ok();
    }

    /**
     * 删除指定ID的系统菜单表信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(Long id, Model model) {
        menuService.removeById(id);
        return Result.ok();
    }

    @RequestMapping(value = "/getMenuTree")
    @ResponseBody
    public Object getMenuTree(Long roleId){
        List<TreeNode> menuTree = treeMenu();
        if (roleId != null) {
            //根据角色id查询关联菜单id集合
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            List<RoleMenu> relList = roleMenuService.list(Wrappers.lambdaQuery(roleMenu));
            setMenuChecked(menuTree, relList);
        }
        return menuTree;
    }

    /**
     * 设置菜单选择
     * @param menuTree
     * @param relList
     */
    private void setMenuChecked(List<TreeNode> menuTree, List<RoleMenu> relList){
        for (TreeNode node : menuTree) {
            for (RoleMenu rel : relList) {
                if (Integer.valueOf(node.getAttributes().get("id").toString()) == rel.getMenuId().intValue()) {
                    node.setChecked(true);
                }
            }
            if (CollectionUtils.isNotEmpty(node.getChildren())) {
                setMenuChecked(node.getChildren(), relList);
            }
        }
    }

    /**
     * 构建树形菜单
     * @return
     */
    public List<TreeNode> treeMenu() {
        List<Menu> rootMenus = menuService.list(Wrappers.<Menu>lambdaQuery().eq(Menu::getParentId,0L).eq(Menu::getDeleteFlag,0));
        List<Menu> childMenus = menuService.list(Wrappers.<Menu>lambdaQuery().ne(Menu::getParentId,0L).eq(Menu::getDeleteFlag,0));
        MenuTreeUtil util = new MenuTreeUtil(rootMenus, childMenus);
        return util.getTreeNode();
    }
}
