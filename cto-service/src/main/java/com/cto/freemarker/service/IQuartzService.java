package com.cto.freemarker.service;

import java.util.List;

import com.cto.freemarker.entity.TimedTasks;

/**
 * <p>
 * 系统菜单表 服务类
 * </p>
 *
 * @author Bigger-Xu
 * @since 2020-10-01
 */
public interface IQuartzService{
    void initAllTask(List<TimedTasks> scheduledTaskBeanList) throws Exception;

    void addJob(TimedTasks tasks);

    void updateJob(TimedTasks tasks);

    void deleteJob(TimedTasks tasks);
}
