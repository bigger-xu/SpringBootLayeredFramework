/*
 * @(#)  OperationLogsDTO.java    2020-10-17 14:39:34
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.entity.dto;

import java.io.Serializable;

import com.cto.freemarker.entity.OperationLogs;
import lombok.Data;

/**
 * 用户操作日志表 OperationLogsDTO.java 返回实体类
 * 
 * @author 594cto版权所有
 * @date 2020-10-17 14:39:34
 */
@Data
public class OperationLogsDTO extends OperationLogs implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userName;
}
