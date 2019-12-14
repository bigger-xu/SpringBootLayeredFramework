/*
 * @(#)  OperationLogsVo.java    2019-11-20 22:41:51
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.service.impl;

import com.cto.freemarker.entity.OperationLogs;
import com.cto.freemarker.dao.OperationLogsMapper;
import com.cto.freemarker.service.OperationLogsService;
import com.cto.freemarker.service.base.BaseServiceImpl;
import com.cto.freemarker.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
/**
 * 文件名 OperationLogsServiceImpl.java 
 * 
 * @author Zhang Yongwei
 * @date 2019-11-20 22:41:51
 */
@Service
public class OperationLogsServiceImpl extends BaseServiceImpl<OperationLogs> implements OperationLogsService {
    @Autowired
    private OperationLogsMapper operationLogsMapper;
    @Override
    public OperationLogsMapper getNameSpace() {
        return operationLogsMapper;
    }

    @Override
    public Page<OperationLogs> selectPage(OperationLogs operationLogs) {
        Page<OperationLogs> page = new Page<>(operationLogsMapper.selectPageCount(operationLogs), operationLogs.getPageSize(), operationLogs.getPageNum());
        List<OperationLogs> result = operationLogsMapper.selectPageList(operationLogs);
        page.setRows(result == null ? new ArrayList<>() : result);
        return page;
    }
}