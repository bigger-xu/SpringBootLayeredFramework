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
 * HRS 签约公司：用户关联表
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Getter
@Setter
@TableName("hrs_org_user")
public class HrsOrgUser implements Serializable {

    
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 公司id
     */
    private Long orgId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 是否默认签约公司：Y是，N否
     */
    private String isDefaultOrg;

    /**
     * 是否管理员：Y是，N否
     */
    private String isAdmin;

    /**
     * 是否启用：Y是，N否
     */
    private String used;

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

    /**
     * AE订单权限：SELF 个人，GROUP工作组,DEPT部门
     */
    private String orderPermission;

    /**
     * AI订单权限：SELF 个人，GROUP工作组,DEPT部门
     */
    private String orderPermissionAi;

    /**
     * AE用户数据分享人 多个英文逗号分隔
     */
    private String shareUserIds;

    /**
     * AI用户数据分享人 多个英文逗号分隔
     */
    private String shareUserIdsAi;
}
