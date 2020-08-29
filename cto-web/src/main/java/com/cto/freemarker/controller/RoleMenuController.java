/*
 * @(#)  RoleMenuController.java    2019-06-06 12:08:40
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.controller;

import com.cto.freemarker.controller.base.BaseController;
import com.cto.freemarker.entity.RoleMenu;
import com.cto.freemarker.entity.vo.RoleMenuVo;
import com.cto.freemarker.service.RoleMenuService;
import com.cto.freemarker.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 文件名RoleMenuController.java
 *
 * @author Zhang Yongwei
 * @date 2019-06-06 12:08:40
 */
@Controller
@RequestMapping("roleMenu")
public class RoleMenuController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleMenuController.class);
    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 获取菜单角色对应关系列表页
     * @return
     */
    @RequestMapping
    public String index(Model model) {
        return "roleMenu/index";
    }

    /**
     * 获取菜单角色对应关系分页数据
     *
     * @param search 查询条件
     * @return
     */
    @RequestMapping("page")
    @ResponseBody
    public Object list(RoleMenuVo search) {
        //TODO 设置查询属性
        return roleMenuService.selectPage(search);
    }


    /**
     * 获取菜单角色对应关系添加页
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(Model model) {
        return "roleMenu/add";
    }

    /**
     * 获取菜单角色对应关系编辑页
     * @return
     */
    @RequestMapping(value = "/edit")
    public String edit(Long id, Model model) {
        if(id != null){
            RoleMenu roleMenu = roleMenuService.selectEntityById(id);
            model.addAttribute("roleMenu", roleMenu);
        }
        return "roleMenu/edit";
    }

    /**
     * 创建或者更新菜单角色对应关系
     * @param roleMenu 菜单角色对应关系对象
     * @return
     */
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Object saveOrUpdate(RoleMenu roleMenu) {
        if (roleMenu.getId() == null) {
            roleMenu.setAddTime(new Date());
            roleMenuService.insert(roleMenu);
        } else {
            roleMenu.setUpdateTime(new Date());
            roleMenuService.updateBySelective(roleMenu);
        }
        return Result.ok();
    }

    /**
     * 删除指定ID的菜单角色对应关系信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(Long id, Model model) {
        roleMenuService.deleteById(id);
        return Result.ok();
    }
}
