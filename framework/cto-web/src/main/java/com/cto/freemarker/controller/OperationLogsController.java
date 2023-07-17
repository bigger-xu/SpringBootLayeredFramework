/*
 * @(#)  OperationLogsController.java    2020-10-17 14:39:34
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cto.freemarker.controller.base.BaseController;
import com.cto.freemarker.entity.CustomLogs;
import com.cto.freemarker.entity.OperationLogs;
import com.cto.freemarker.entity.query.OperationLogsQuery;
import com.cto.freemarker.enums.CustomLogsType;
import com.cto.freemarker.service.IOperationLogsService;
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
 * 用户操作日志表 OperationLogsController.java 控制层
 *
 * @author 594cto版权所有
 * @date 2020-10-17 14:39:34
 */
@Controller
@RequestMapping("operationLogs")
@Slf4j
public class OperationLogsController extends BaseController {

    @Autowired
    private IOperationLogsService ioperationLogsService;

    /**
     * 获取用户操作日志表列表页
     */
    @RequestMapping
    @CustomLogs(description = "查询用户操作日志", type = CustomLogsType.SELECT)
    @RequiresPermissions("operationLogs")
    public String index(Model model) {
        return "operationLogs/index";
    }

    /**
     * 获取用户操作日志表分页数据
     *
     * @param query 查询条件
     * @return IPage
     */
    @RequestMapping("page")
    @ResponseBody
    public Object list(OperationLogsQuery query) {
        try {
            //TODO 设置查询属性
            return ioperationLogsService.customPage(query);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("请求错误:{}",e.getMessage());
            return Result.error();
        }

    }


    /**
     * 获取用户操作日志表添加页
     */
    @RequestMapping(value = "/add")
    public String add(Model model) {
        return "operationLogs/add";
    }

    /**
     * 获取用户操作日志表编辑页
     */
    @RequestMapping(value = "/edit")
    public String edit(String uuid,Model model) {
        if(StringUtils.isNotBlank(uuid)){
            OperationLogs operationLogs = ioperationLogsService.getOne(Wrappers.<OperationLogs>lambdaQuery().eq(OperationLogs::getUuid,uuid),false);
            model.addAttribute("operationLogs", operationLogs);
        }
        return "operationLogs/edit";
    }

    /**
     * 创建或者更新用户操作日志表
     * @param operationLogs 用户操作日志表对象
     * @return Boolean
     */
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @CustomLogs(description = "添加或者修改用户操作日志", type = CustomLogsType.INSERT_UPDATE)
    @ResponseBody
    public Object saveOrUpdate(OperationLogs operationLogs) {
        try {
            ioperationLogsService.saveOrUpdate(operationLogs);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("请求错误:{}",e.getMessage());
            return Result.error();
        }
    }

    /**
     * 删除指定ID的用户操作日志表信息
     * @param uuid UUID
     * @return Boolean
     */
    @RequestMapping(value = "/delete")
    @CustomLogs(description = "删除用户操作日志", type = CustomLogsType.DELETE)
    @ResponseBody
    public Object delete(String uuid, Model model) {
        try {
            ioperationLogsService.remove(Wrappers.<OperationLogs>lambdaQuery().eq(OperationLogs::getUuid,uuid));
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("请求错误:{}",e.getMessage());
            return Result.error();
        }
    }
}
