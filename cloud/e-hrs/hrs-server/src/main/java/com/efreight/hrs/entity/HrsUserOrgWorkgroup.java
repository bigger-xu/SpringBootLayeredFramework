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
 * HRS 用户：工作组
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Getter
@Setter
@TableName("hrs_user_org_workgroup")
public class HrsUserOrgWorkgroup implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 工作组明细ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 签约公司ID
     */
    private Long orgId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 工作组ID
     */
    private Long workgroupId;

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
