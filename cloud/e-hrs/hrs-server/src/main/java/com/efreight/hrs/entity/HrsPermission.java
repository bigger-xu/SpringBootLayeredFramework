package com.efreight.hrs.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * HRS 权限管理
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Getter
@Setter
@TableName("hrs_permission")
public class HrsPermission implements Serializable {

    
    private static final long serialVersionUID = 1L;

    /**
     * 权限id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 权限code
     */
    private String permissionCode;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 前端链接，如果 路径是 /unDevelop 则 跳转到 暂未开通页面
     */
    private String path;

    /**
     * vue链接
     */
    private String url;

    /**
     * 权限
     */
    private String permission;

    /**
     * 图标
     */
    private String icon;

    /**
     * 父级权限
     */
    private Long parentId;

    /**
     * 排序序列
     */
    private Integer sort;

    /**
     * Y启用，N未启用
     */
    private String status;

    /**
     * 权限类型
     */
    private String permissionType;

    /**
     * 业务模块
     */
    private String appCode;

    /**
     * 默认给管理员
     */
    private String adminDefault;

    /**
     * 页面名称
     */
    private String pageName;

    /**
     * 页面code
     */
    private String pageCode;

    /**
     * 页面模块
     */
    private String pageModule;

    /**
     * 创建人ID
     */
    @TableField(fill = FieldFill.INSERT)
    private Long creatorId;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String creatorName;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 操作人ID
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long editorId;

    /**
     * 操作人
     */
    @TableField(fill = FieldFill.UPDATE)
    private String editorName;

    /**
     * 操作时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime editTime;
}
