/*
 * @(#)  TimedTasksService.java  2020-10-02 01:13:26
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cto.freemarker.entity.TimedTasks;
import com.cto.freemarker.entity.query.TimedTasksQuery;

/**
 * 系统菜单表 TimedTasksService.java 服务类
 * 
 * @author 594cto版权所有
 * @date 2020-10-02 01:13:26
 */
public interface ITimedTasksService extends IService<TimedTasks> {
    /**
     * 自定义分页查询
     * @param query query
     * @return IPage
     */
    IPage<TimedTasks> customPage(TimedTasksQuery query);
}
