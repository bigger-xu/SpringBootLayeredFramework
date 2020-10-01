package com.cto.freemarker.service.impl;

import com.cto.freemarker.entity.TimedTasks;
import com.cto.freemarker.mapper.TimedTasksMapper;
import com.cto.freemarker.service.ITimedTasksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author Bigger-Xu
 * @since 2020-10-01
 */
@Service
public class TimedTasksServiceImpl extends ServiceImpl<TimedTasksMapper, TimedTasks> implements ITimedTasksService {

}
