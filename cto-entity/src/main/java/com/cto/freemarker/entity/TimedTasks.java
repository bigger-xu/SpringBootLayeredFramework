package com.cto.freemarker.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统菜单表
 * </p>
 *
 * @author Bigger-Xu
 * @since 2020-10-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TimedTasks implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 是否开启（0：否 1：是）
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
