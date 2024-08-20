package com.efreight.hrs.model.web.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "HrsPermissionVO对象", description = "HRS 权限管理")
public class HrsPermissionVO implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @Schema(description = "权限id")
    private Long id;

    @Schema(description = "权限code")
    private String permissionCode;

    @Schema(description = "权限名称")
    private String permissionName;

    @Schema(description = "前端链接，如果 路径是 /unDevelop 则 跳转到 暂未开通页面")
    private String path;

    @Schema(description = "vue链接")
    private String url;

    @Schema(description = "权限")
    private String permission;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "父级权限")
    private Long parentId;

    @Schema(description = "排序序列")
    private Integer sort;

    @Schema(description = "Y启用，N未启用")
    private String status;

    @Schema(description = "权限类型")
    private String permissionType;

    @Schema(description = "业务模块")
    private String appCode;

    @Schema(description = "默认给管理员")
    private String adminDefault;

    @Schema(description = "页面名称")
    private String pageName;

    @Schema(description = "页面code")
    private String pageCode;

    @Schema(description = "页面模块")
    private String pageModule;

    @Schema(description = "创建人ID")
    private Long creatorId;

    @Schema(description = "创建人")
    private String creatorName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "操作人ID")
    private Long editorId;

    @Schema(description = "操作人")
    private String editorName;

    @Schema(description = "操作时间")
    private LocalDateTime editTime;
}