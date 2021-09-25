/*
 * @(#)  OperationLogsVo.java    2020-10-17 14:39:34
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.service.impl;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cto.freemarker.entity.OperationLogs;
import com.cto.freemarker.entity.dto.OperationLogsDTO;
import com.cto.freemarker.entity.query.OperationLogsQuery;
import com.cto.freemarker.mapper.OperationLogsMapper;
import com.cto.freemarker.service.IOperationLogsService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

/**
 * 用户操作日志表 OperationLogsServiceImpl.java 服务实现类
 * 
 * @author 594cto版权所有
 * @date 2020-10-17 14:39:34
 */
@Service
@Slf4j
public class OperationLogsServiceImpl extends ServiceImpl<OperationLogsMapper, OperationLogs> implements IOperationLogsService {
    @Resource
    private OperationLogsMapper operationLogsMapper;

    @Override
    public IPage<OperationLogsDTO> customPage(OperationLogsQuery query) {
        return operationLogsMapper.customPage(new Page<>(query.getPageNum(),query.getPageSize()),query);
    }

}