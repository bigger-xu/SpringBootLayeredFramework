/*
 * @(#)  OperationLogsVo.java    2019-11-20 22:41:51
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.service;

import com.cto.freemarker.entity.OperationLogs;
import com.cto.freemarker.service.base.BaseService;
import com.cto.freemarker.utils.Page;
/**
 * 文件名 OperationLogsService.java
 * 
 * @author Zhang Yongwei
 * @date 2019-11-20 22:41:51
 */
public interface OperationLogsService extends BaseService<OperationLogs> {
    /**
     * 查询分页
     * @param operationLogs
     * @return
     */
    Page<OperationLogs> selectPage(OperationLogs operationLogs);
}
