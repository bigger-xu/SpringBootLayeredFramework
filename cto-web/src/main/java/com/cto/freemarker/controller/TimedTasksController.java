/*
 * @(#)  TimedTasksController.java    2020-10-01 23:44:41
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cto.freemarker.controller.base.BaseController;
import com.cto.freemarker.entity.TimedTasks;
import com.cto.freemarker.entity.query.TimedTasksQuery;
import com.cto.freemarker.service.ITimedTasksService;
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

/**
 * 文件名TimedTasksController.java
 *
 * @author 594cto版权所有
 * @date 2020-10-01 23:44:41
 */
@Controller
@RequestMapping("timedTasks")
public class TimedTasksController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimedTasksController.class);
    @Autowired
    private ITimedTasksService iTimedTasksService;

    /**
     * 获取系统菜单表列表页
     */
    @RequestMapping
    @RequiresPermissions("timedTasks")
    public String index(Model model) {
        return "timedTasks/index";
    }

    /**
     * 获取系统菜单表分页数据
     *
     * @param search 查询条件
     */
    @RequestMapping("page")
    @ResponseBody
    public Object list(TimedTasksQuery search) {
        try {
            //TODO 设置查询属性
            return iTimedTasksService.page(new Page<>(search.getPageNum(),search.getPageSize()), Wrappers.<TimedTasks>lambdaQuery().eq(TimedTasks::getAddUserId,getCurrentUser().getId()));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("请求错误:{}",e);
            return Result.error();
        }
    }


    /**
     * 获取系统菜单表添加页
     */
    @RequestMapping(value = "/add")
    public String add(Model model) {
        return "timedTasks/add";
    }

    /**
     * 获取系统菜单表编辑页
     */
    @RequestMapping(value = "/edit")
    public String edit(Long id,Model model) {
        if(id != null){
            TimedTasks timedTasks = iTimedTasksService.getById(id);
            model.addAttribute("timedTasks", timedTasks);
        }
        return "timedTasks/edit";
    }

    /**
     * 创建或者更新系统菜单表
     * @param timedTasks 系统菜单表对象
     * @return
     */
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Object saveOrUpdate(TimedTasks timedTasks) {
        try {
            iTimedTasksService.saveOrUpdate(timedTasks);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("请求错误:{}",e);
            return Result.error();
        }
    }

    /**
     * 删除指定ID的系统菜单表信息
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String uuid, Model model) {
        try {
            iTimedTasksService.deleteByUUId(uuid);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("请求错误:{}",e);
            return Result.error();
        }
    }
}
