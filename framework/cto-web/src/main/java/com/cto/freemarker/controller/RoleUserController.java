/*
 * @(#)  RoleUserController.java    2019-06-06 12:08:40
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.controller;

import java.util.Date;

import com.cto.freemarker.controller.base.BaseController;
import com.cto.freemarker.entity.RoleUser;
import com.cto.freemarker.entity.query.RoleUserQuery;
import com.cto.freemarker.service.IRoleUserService;
import com.cto.freemarker.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 文件名RoleUserController.java
 *
 * @author Zhang Yongwei
 * @date 2019-06-06 12:08:40
 */
@Controller
@RequestMapping("roleUser")
public class RoleUserController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleUserController.class);
    @Autowired
    private IRoleUserService roleUserService;

    /**
     * 获取用户角色对应关系列表页
     * @return
     */
    @RequestMapping
    public String index(Model model) {
        return "roleUser/index";
    }

    /**
     * 获取用户角色对应关系分页数据
     *
     * @param search 查询条件
     * @return
     */
    @RequestMapping("page")
    @ResponseBody
    public Object list(RoleUserQuery search) {
        //TODO 设置查询属性
        return null;//roleUserService.selectPage(search);
    }


    /**
     * 获取用户角色对应关系添加页
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(Model model) {
        return "roleUser/add";
    }

    /**
     * 获取用户角色对应关系编辑页
     * @return
     */
    @RequestMapping(value = "/edit")
    public String edit(Long id, Model model) {
        if(id != null){
            RoleUser roleUser = roleUserService.getById(id);
            model.addAttribute("roleUser", roleUser);
        }
        return "roleUser/edit";
    }

    /**
     * 创建或者更新用户角色对应关系
     * @param roleUser 用户角色对应关系对象
     * @return
     */
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Object saveOrUpdate(RoleUser roleUser) {
        if (roleUser.getId() == null) {
            roleUser.setAddTime(new Date());
            roleUserService.save(roleUser);
        } else {
            roleUser.setUpdateTime(new Date());
            roleUserService.updateById(roleUser);
        }
        return Result.ok();
    }

    /**
     * 删除指定ID的用户角色对应关系信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(Long id, Model model) {
        roleUserService.removeById(id);
        return Result.ok();
    }
}
