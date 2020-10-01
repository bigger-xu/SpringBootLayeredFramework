package com.cto.freemarker.service;

import com.cto.freemarker.entity.TimedTasks;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统菜单表 服务类
 * </p>
 *
 * @author Bigger-Xu
 * @since 2020-10-01
 */
public interface ITimedTasksService extends IService<TimedTasks> {

    void deleteByUUId(String uuid);
}
