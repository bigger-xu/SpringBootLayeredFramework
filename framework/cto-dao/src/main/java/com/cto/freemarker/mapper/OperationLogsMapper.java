/*
 * @(#)  OperationLogsVo.java    2020-10-17 14:39:34
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cto.freemarker.entity.OperationLogs;
import com.cto.freemarker.entity.dto.OperationLogsDTO;
import com.cto.freemarker.entity.query.OperationLogsQuery;
import org.apache.ibatis.annotations.Param;

/**
 * 用户操作日志表 OperationLogsMapper.java Mapper接口
 *
 * @author 594cto版权所有
 * @date 2020-10-17 14:39:34
 */
public interface OperationLogsMapper extends BaseMapper<OperationLogs> {

    /**
     * 自定义分页查询
     * @param query query
     * @return IPage
     */
    IPage<OperationLogsDTO> customPage(IPage<OperationLogsQuery> page, @Param("search") OperationLogsQuery query);
}