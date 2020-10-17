/*
 * @(#)  OperationLogsService.java  2020-10-17 14:39:34
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cto.freemarker.entity.OperationLogs;
import com.cto.freemarker.entity.dto.OperationLogsDTO;
import com.cto.freemarker.entity.query.OperationLogsQuery;

/**
 * 用户操作日志表 OperationLogsService.java 服务类
 * 
 * @author 594cto版权所有
 * @date 2020-10-17 14:39:34
 */
public interface IOperationLogsService extends IService<OperationLogs> {
    /**
     * 自定义分页查询
     * @param query query
     * @return IPage
     */
    IPage<OperationLogsDTO> customPage(OperationLogsQuery query);
}
