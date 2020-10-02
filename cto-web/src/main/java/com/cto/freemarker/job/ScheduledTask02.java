package com.cto.freemarker.job;

import com.cto.freemarker.entity.TimedTasks;
import com.cto.freemarker.service.ITimedTasksService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Zhang Wei
 * @version 1.0
 * @date 2020/7/28
 */
@Component
@Slf4j
@Data
public class ScheduledTask02 implements Job {
    @Autowired
    private ITimedTasksService iTimedTasksService;
    private Integer id;
    private String params;

    @Override
    public void execute(JobExecutionContext context){
        if (iTimedTasksService!=null){
            TimedTasks timedTasks = iTimedTasksService.getById(id);
            log.info("ScheduledTask => 02 quartzTask = {}", timedTasks);
        }
        log.info("ScheduledTask => 02 run 当前线程名称 {}, Data = {}", Thread.currentThread().getName(), params);
    }
}
