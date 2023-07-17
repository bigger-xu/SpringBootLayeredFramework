package com.cto.freemarker.config.quartz;

import java.util.List;

import com.cto.freemarker.entity.TimedTasks;
import com.cto.freemarker.service.IQuartzService;
import com.cto.freemarker.service.ITimedTasksService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Zhang Wei
 * @version 1.0
 * @date 2020/7/28
 */
@Component
@Order(value = 1)
@Slf4j
public class ScheduledTaskRunner implements ApplicationRunner {

    @Autowired
    private IQuartzService iQuartzService;

    @Autowired
    private ITimedTasksService iTimedTasksService;

    /**
     * 程序启动完毕后,需要自启的任务
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(" >>>>>> 项目启动完毕, 开启 => 需要自启的任务 开始!");
        List<TimedTasks> activatedTaskList = iTimedTasksService.list();
        iQuartzService.initAllTask(activatedTaskList);
        log.info(" >>>>>> 项目启动完毕, 开启 => 需要自启的任务 结束！开启任务数为{}个",activatedTaskList.size());
    }
}
