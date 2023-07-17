/*
 * @(#)  RoleController.java    2019-06-06 12:08:40
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.controller;

import java.util.Date;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cto.freemarker.controller.base.BaseController;
import com.cto.freemarker.entity.Role;
import com.cto.freemarker.entity.RoleMenu;
import com.cto.freemarker.entity.dto.RoleDto;
import com.cto.freemarker.entity.query.RoleQuery;
import com.cto.freemarker.service.IRoleMenuService;
import com.cto.freemarker.service.IRoleService;
import com.cto.freemarker.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 文件名RoleController.java
 *
 * @author Zhang Yongwei
 * @date 2019-06-06 12:08:40
 */
@Controller
@RequestMapping("role")
public class RoleController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IRoleMenuService roleMenuService;

    /**
     * 获取角色表列表页
     * @return
     */
    @RequestMapping
    @RequiresPermissions("role")
    public String index(Model model) {
        return "role/index";
    }

    /**
     * 获取角色表分页数据
     *
     * @param search 查询条件
     * @return
     */
    @RequestMapping("page")
    @ResponseBody
    public Object list(RoleQuery search) {
        //TODO 设置查询属性
        return roleService.page(new Page<>(search.getPageNum(),search.getPageSize()), Wrappers.<Role>lambdaQuery().eq(Role::getAddUserId,getCurrentUser().getId()));
    }


    /**
     * 获取角色表添加页
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(Model model) {
        return "role/add";
    }

    /**
     * 获取角色表编辑页
     * @return
     */
    @RequestMapping(value = "/edit")
    public String edit(Long id, Model model) {
        if(id != null){
            Role role = roleService.getById(id);
            model.addAttribute("role", role);
        }
        return "role/edit";
    }

    /**
     * 创建或者更新角色表
     * @param role 角色表对象
     * @return
     */
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Object saveOrUpdate(RoleDto role) {
        Date date = new Date();
        if (role.getId() == null) {
            role.setDeleteFlag("0");
            role.setAddUserId(getCurrentUser().getId());
            roleService.save(role);
            if(StringUtils.isNotEmpty(role.getRoleIds())){
                String[] roleIdList = role.getRoleIds().split(",");
                RoleMenu roleMenu;
                for(String s : roleIdList){
                    roleMenu = new RoleMenu();
                    roleMenu.setRoleId(role.getId());
                    roleMenu.setMenuId(Long.valueOf(s));
                    roleMenuService.save(roleMenu);
                }
            }
        } else {
            roleService.updateById(role);
            roleMenuService.remove(Wrappers.<RoleMenu>lambdaQuery().eq(RoleMenu::getRoleId,role.getId()));
            if(StringUtils.isNotEmpty(role.getRoleIds())){
                String[] roleIdList = role.getRoleIds().split(",");
                RoleMenu roleMenu;
                for(String s : roleIdList){
                    roleMenu = new RoleMenu();
                    roleMenu.setRoleId(role.getId());
                    roleMenu.setMenuId(Long.valueOf(s));
                    roleMenuService.save(roleMenu);
                }
            }
        }
        return Result.ok();
    }

    /**
     * 删除指定ID的角色表信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(Long id, Model model) {
        roleService.removeById(id);
        return Result.ok();
    }
}
