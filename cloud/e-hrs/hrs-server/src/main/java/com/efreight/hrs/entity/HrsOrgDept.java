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
 * HRS 签约公司：部门
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Getter
@Setter
@TableName("hrs_org_dept")
public class HrsOrgDept implements Serializable {

    
    private static final long serialVersionUID = 1L;

    /**
     * 部门id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 公司id
     */
    private Long orgId;

    /**
     * 部门code
     */
    private String deptCode;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 父级部门id
     */
    private Long parentId;

    /**
     * 父子部门名称，/分割
     */
    private String parentChildName;

    /**
     * 部门负责人id
     */
    private Long managerId;

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
