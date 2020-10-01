package com.cto.freemarker.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.cto.freemarker.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 用户操作日志表
 * </p>
 *
 * @author Bigger-Xu
 * @since 2020-09-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OperationLogs extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * UUID
     */
    @TableField(fill = FieldFill.INSERT)
    private String uuid;

    /**
     * 添加时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date addTime;

    /**
     * 添加人ID
     */
    private Long addUserId;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 请求URL
     */
    private String requestUrl;

    /**
     * 请求方式
     */
    private String requestType;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 日志描述
     */
    private String description;

    /**
     * 日志级别
     */
    private Integer leaves;

    /**
     * 运行时长
     */
    private Long runTime;

    /**
     * 日志类型
     */
    private String type;

    /**
     * 返回数据
     */
    private String returnValue;

    /**
     * IP地址
     */
    private String ipAddress;


}
