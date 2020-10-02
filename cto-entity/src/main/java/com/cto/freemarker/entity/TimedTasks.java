/*
 * @(#)  TimedTasks.java    2020-10-02 01:13:26
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.cto.freemarker.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 系统菜单表 TimedTasks.java
 * @author 594cto版权所有
 * @date 2020-10-02 01:13:26
 *  
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TimedTasks extends BaseEntity {
	private static final long serialVersionUID = 1L;

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
