/*
 * @(#)  TimedTasks.java    2020-10-02 01:13:26
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;

/**
 * 系统菜单表 TimedTasks.java
 * @author 594cto版权所有
 * @date 2020-10-02 01:13:26
 *  
 */
@Data
public class TimedTasks implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * UUID
	 */
	private String uuid;
	/**
	 * 添加时间
	 */
	private Date addTime;
	/**
	 * 添加人ID
	 */
	private Long addUserId;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 更新人ID
	 */
	private Long updateUserId;
	/**
	 * 任务名称
	 */
	private String taskName;
	/**
	 * 任务分组
	 */
	private String taskGroupName;
	/**
	 * 是否显示（0：是 1：否）
	 */
	private Integer status;
	/**
	 * cron表达式
	 */
	private String cron;
	/**
	 * job所在类
	 */
	private String jobClass;
	/**
	 * 所需参数
	 */
	private String params;

}
