/*
 * @(#)  OperationLogs.java    2020-10-17 14:39:34
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.entity;

import com.cto.freemarker.entity.base.BaseEntity;
import lombok.Data;

/**
 * 用户操作日志表 OperationLogs.java
 * @author 594cto版权所有
 * @date 2020-10-17 14:39:34
 *  
 */
@Data
public class OperationLogs extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 日志描述
	 */
	private String description;
	/**
	 * IP地址
	 */
	private String ipAddress;
	/**
	 * 日志级别
	 */
	private Integer leaves;
	/**
	 * 请求参数
	 */
	private String requestParams;
	/**
	 * 日志类型
	 */
	private String type;
	/**
	 * 请求URL
	 */
	private String requestUrl;
	/**
	 * 返回数据
	 */
	private String returnValue;
	/**
	 * 运行时长
	 */
	private Long runTime;
	/**
	 * 请求方式
	 */
	private String requestType;

}
