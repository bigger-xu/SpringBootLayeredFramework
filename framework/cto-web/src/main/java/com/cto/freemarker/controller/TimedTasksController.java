/*
 * @(#)  TimedTasksController.java    2020-10-02 01:40:37
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cto.freemarker.controller.base.BaseController;
import com.cto.freemarker.entity.TimedTasks;
import com.cto.freemarker.entity.query.TimedTasksQuery;
import com.cto.freemarker.service.IQuartzService;
import com.cto.freemarker.service.ITimedTasksService;
import com.cto.freemarker.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统菜单表 TimedTasksController.java 控制层
 *
 * @author 594cto版权所有
 * @date 2020-10-02 01:40:37
 */
@Controller
@RequestMapping("timedTasks")
@Slf4j
public class TimedTasksController extends BaseController {

    @Autowired
    private ITimedTasksService itimedTasksService;
    @Autowired
    private IQuartzService quartzService;

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
     * @param query 查询条件
     * @return IPage
     */
    @RequestMapping("page")
    @ResponseBody
    public Object list(TimedTasksQuery query) {
        try {
            //TODO 设置查询属性
            query.setAddUserId(getCurrentUser().getId());
            return itimedTasksService.customPage(query);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("请求错误:{}",e.getMessage());
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
    public String edit(String uuid,Model model) {
        if(StringUtils.isNotBlank(uuid)){
            TimedTasks timedTasks = itimedTasksService.getOne(Wrappers.<TimedTasks>lambdaQuery().eq(TimedTasks::getUuid,uuid),false);
            model.addAttribute("timedTasks", timedTasks);
        }
        return "timedTasks/edit";
    }

    /**
     * 创建或者更新系统菜单表
     * @param timedTasks 系统菜单表对象
     * @return Boolean
     */
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Object saveOrUpdate(TimedTasks timedTasks) {
        try {
            if (timedTasks.getId() == null) {
                timedTasks.setAddUserId(getCurrentUser().getId());
                itimedTasksService.save(timedTasks);
                quartzService.addJob(timedTasks);
            } else {
                boolean flag = itimedTasksService.updateById(timedTasks);
                if(flag && timedTasks.getStatus() == 1){
                    quartzService.updateJob(timedTasks);
                }
            }
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("请求错误:{}",e.getMessage());
            return Result.error();
        }
    }

    /**
     * 删除指定ID的系统菜单表信息
     * @param uuid UUID
     * @return Boolean
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String uuid) {
        try {
            TimedTasks timedTasks = itimedTasksService.getOne(Wrappers.<TimedTasks>lambdaQuery().eq(TimedTasks::getUuid,uuid),false);
            if(timedTasks != null){
                quartzService.deleteJob(timedTasks);
                itimedTasksService.remove(Wrappers.<TimedTasks>lambdaQuery().eq(TimedTasks::getUuid,uuid));
            }
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("请求错误:{}",e.getMessage());
            return Result.error();
        }
    }

    /**
     * 修改任务状态
     * @param uuid UUID
     * @return Boolean
     */
    @RequestMapping(value = "/editStatus")
    @ResponseBody
    public Object delete(String uuid, Integer status) {
        try {
            TimedTasks timedTasks = itimedTasksService.getOne(Wrappers.<TimedTasks>lambdaQuery().eq(TimedTasks::getUuid,uuid),false);
            timedTasks.setStatus(status);
            boolean flag = itimedTasksService.updateById(timedTasks);
            //根据status判断是新增job还是删除job
            if (flag && status == 1) {
                quartzService.addJob(timedTasks);
            } else {
                quartzService.deleteJob(timedTasks);
            }
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("请求错误:{}",e.getMessage());
            return Result.error();
        }
    }
}
