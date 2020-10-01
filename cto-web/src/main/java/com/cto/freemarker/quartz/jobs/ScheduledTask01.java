package com.cto.freemarker.quartz.jobs;

import com.cto.freemarker.entity.TimedTasks;
import com.cto.freemarker.service.ITimedTasksService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * @author Zhang Wei
 * @version 1.0
 * @date 2020/7/28
 */
@Component
@Data
@Slf4j
public class ScheduledTask01 implements Job {

    private ITimedTasksService iTimedTasksService;

    private Integer id;

    private String params;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        if (iTimedTasksService!=null){
            TimedTasks timedTasks = iTimedTasksService.getById(id);
            log.info("ScheduledTask => 01 quartzTask = {}", timedTasks);
        }
        log.info("ScheduledTask => 01 run 当前线程名称 {}, Data = {}", Thread.currentThread().getName(), params);
    }
}
