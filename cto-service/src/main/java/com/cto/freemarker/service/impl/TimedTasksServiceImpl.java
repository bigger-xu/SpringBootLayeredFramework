/*
 * @(#)  TimedTasksVo.java    2020-10-02 01:40:37
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.service.impl;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cto.freemarker.entity.TimedTasks;
import com.cto.freemarker.entity.query.TimedTasksQuery;
import com.cto.freemarker.mapper.TimedTasksMapper;
import com.cto.freemarker.service.ITimedTasksService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

/**
 * 系统菜单表 TimedTasksServiceImpl.java 服务实现类
 * 
 * @author 594cto版权所有
 * @date 2020-10-02 01:40:37
 */
@Service
@Slf4j
public class TimedTasksServiceImpl extends ServiceImpl<TimedTasksMapper, TimedTasks> implements ITimedTasksService {
    @Resource
    private TimedTasksMapper timedTasksMapper;

    @Override
    public IPage<TimedTasks> customPage(TimedTasksQuery query) {
        LambdaQueryWrapper<TimedTasks> wrapper = Wrappers.lambdaQuery();
        if(query.getId() != null){
            wrapper.eq(TimedTasks::getId,query.getId());
        }
        if(StringUtils.isNotBlank(query.getUuid())){
            wrapper.like(TimedTasks::getUuid,query.getUuid());
        }
        if(query.getAddTime() != null){
            wrapper.eq(TimedTasks::getAddTime,query.getAddTime());
        }
        if(query.getAddUserId() != null){
            wrapper.eq(TimedTasks::getAddUserId,query.getAddUserId());
        }
        if(query.getUpdateTime() != null){
            wrapper.eq(TimedTasks::getUpdateTime,query.getUpdateTime());
        }
        if(query.getUpdateUserId() != null){
            wrapper.eq(TimedTasks::getUpdateUserId,query.getUpdateUserId());
        }
        if(StringUtils.isNotBlank(query.getTaskName())){
            wrapper.like(TimedTasks::getTaskName,query.getTaskName());
        }
        if(StringUtils.isNotBlank(query.getTaskGroupName())){
            wrapper.like(TimedTasks::getTaskGroupName,query.getTaskGroupName());
        }
        if(query.getStatus() != null){
            wrapper.eq(TimedTasks::getStatus,query.getStatus());
        }
        if(StringUtils.isNotBlank(query.getCron())){
            wrapper.like(TimedTasks::getCron,query.getCron());
        }
        if(StringUtils.isNotBlank(query.getJobClass())){
            wrapper.like(TimedTasks::getJobClass,query.getJobClass());
        }
        if(StringUtils.isNotBlank(query.getParams())){
            wrapper.like(TimedTasks::getParams,query.getParams());
        }
        return timedTasksMapper.selectPage(new Page<>(query.getPageNum(),query.getPageSize()),wrapper);
    }

}