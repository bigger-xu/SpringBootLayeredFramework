package com.cto.freemarker.service.impl;

import com.cto.freemarker.entity.OperationLogs;
import com.cto.freemarker.mapper.OperationLogsMapper;
import com.cto.freemarker.service.IOperationLogsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户操作日志表 服务实现类
 * </p>
 *
 * @author Bigger-Xu
 * @since 2020-09-19
 */
@Service
public class OperationLogsServiceImpl extends ServiceImpl<OperationLogsMapper, OperationLogs> implements IOperationLogsService {

}
