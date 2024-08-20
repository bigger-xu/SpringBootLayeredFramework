package com.efreight.base.entity;


import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 视图表
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2024-08-13
 */
@Getter
@Setter
@ToString
@TableName("base_global_view")
public class BaseGlobalView implements Serializable {

    
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 数据库连接地址(必须是Mysql连接地址)
     */
    private String connectUrl;

    /**
     * 数据库连接名
     */
    private String connectUser;

    /**
     * 数据库连接密码
     */
    private String connectPassword;

    /**
     * 数据库：例 cargo
     */
    private String databaseName;

    /**
     * 订单类型
     */
    private String orderType;

    /**
     * 视图名称：例 v_af_order
     */
    private String viewName;

    /**
     * 视图注释
     */
    private String viewComment;

    /**
     * SQL脚本信息
     */
    private String sqlDetail;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long creatorId;

    /**
     * 创建人名称-中文姓名
     */
    @TableField(fill = FieldFill.INSERT)
    private String creatorName;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新人id
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long editorId;

    /**
     * 更新人名称-中文姓名
     */
    @TableField(fill = FieldFill.UPDATE)
    private String editorName;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime editTime;
}
