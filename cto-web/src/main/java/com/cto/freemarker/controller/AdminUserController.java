/*
 * @(#)  AdminUserController.java    2019-06-06 12:08:40
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.controller;

import com.cto.freemarker.controller.base.BaseController;
import com.cto.freemarker.entity.AdminUser;
import com.cto.freemarker.entity.Role;
import com.cto.freemarker.entity.vo.AdminUserVo;
import com.cto.freemarker.service.AdminUserService;
import com.cto.freemarker.service.RoleService;
import com.cto.freemarker.utils.Result;
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
 * 文件名AdminUserController.java
 *
 * @author Zhang Yongwei
 * @date 2019-06-06 12:08:40
 */
@Controller
@RequestMapping("adminUser")
public class AdminUserController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminUserController.class);
    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private RoleService roleService;

    /**
     * 获取系统用户表列表页
     * @return
     */
    @RequestMapping
    @RequiresPermissions("adminUser")
    public String index(Model model) {
        return "adminUser/index";
    }

    /**
     * 获取系统用户表分页数据
     *
     * @param search 查询条件
     * @return
     */
    @RequestMapping("page")
    @ResponseBody
    public Object list(AdminUserVo search) {
        try {
            //TODO 设置查询属性
            return adminUserService.selectPage(search);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("请求错误:{}",e);
            return Result.error();
        }

    }


    /**
     * 获取系统用户表添加页
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(Model model) {
        Role role = new Role();
        role.setStatus("1");
        List<Role> roleList = roleService.selectListBySearch(role);
        model.addAttribute("roleList",roleList);
        return "adminUser/add";
    }

    /**
     * 获取系统用户表编辑页
     * @return
     */
    @RequestMapping(value = "/edit")
    public String edit(Long id, Model model) {
        if(id != null){
            AdminUser adminUser = adminUserService.selectEntityById(id);
            model.addAttribute("adminUser", adminUser);
        }
        Role role = new Role();
        role.setStatus("1");
        List<Role> roleList = roleService.selectListBySearch(role);
        model.addAttribute("roleList",roleList);
        return "adminUser/edit";
    }

    /**
     * 创建或者更新系统用户表
     * @param adminUser 系统用户表对象
     * @return
     */
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Object saveOrUpdate(AdminUser adminUser) {
        try {
            if (adminUser.getId() == null) {
                adminUser.setAddTime(new Date());
                adminUserService.create(adminUser);
            } else {
                adminUser.setUpdateTime(new Date());
                adminUserService.updateDefault(adminUser);
            }
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("请求错误:{}",e);
            return Result.error();
        }
    }

    /**
     * 删除指定ID的系统用户表信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(Long id, Model model) {
        try {
            adminUserService.deleteById(id);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("请求错误:{}",e);
            return Result.error();
        }
    }
}
