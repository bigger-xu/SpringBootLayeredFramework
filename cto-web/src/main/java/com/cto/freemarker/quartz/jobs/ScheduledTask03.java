package com.cto.freemarker.quartz.jobs;

import com.cto.freemarker.entity.TimedTasks;
import com.cto.freemarker.service.ITimedTasksService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

/**
 * @author Zhang Wei
 * @version 1.0
 * @date 2020/7/28
 */
@Data
@Slf4j
@Component
public class ScheduledTask03 implements Job {

    private ITimedTasksService iTimedTasksService;

    private Integer id;

    private String params;

    @Override
    public void execute(JobExecutionContext context){
        if (iTimedTasksService!=null){
            TimedTasks timedTasks = iTimedTasksService.getById(id);
            log.info("ScheduledTask => 03 quartzTask = {}", timedTasks);
        }
        log.info("ScheduledTask => 03 run 当前线程名称 {}, Data = {}", Thread.currentThread().getName(), params);
    }
}
