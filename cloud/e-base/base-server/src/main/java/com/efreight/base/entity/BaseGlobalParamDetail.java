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
 * 全局参数详情表
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2024-08-13
 */
@Getter
@Setter
@ToString
@TableName("base_global_param_detail")
public class BaseGlobalParamDetail implements Serializable {

    
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 参数分类ID
     */
    private Long categoryId;

    /**
     * 订单类型
     */
    private String orderType;

    /**
     * 参数名称
     */
    private String paramName;

    /**
     * 视图ID
     */
    private Long viewId;

    /**
     * 视图字段ID
     */
    private Long fieldId;

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
