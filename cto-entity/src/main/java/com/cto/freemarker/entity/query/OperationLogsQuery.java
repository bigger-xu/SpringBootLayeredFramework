/*
 * @(#)  OperationLogsVo.java    2020-10-17 14:39:34
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.entity.query;

import com.cto.freemarker.entity.OperationLogs;
import lombok.Data;

/**
 * 用户操作日志表 OperationLogsQuery.java 检索类
 * 
 * @author 594cto版权所有
 * @date 2020-10-17 14:39:34
 */
@Data
public class OperationLogsQuery extends OperationLogs {
    private Integer pageNum = 1;
    private Integer pageSize = 10;

    private String userName;
}
