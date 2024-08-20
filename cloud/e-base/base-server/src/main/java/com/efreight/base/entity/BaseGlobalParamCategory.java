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
 * 全局参数分类表
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2024-08-13
 */
@Getter
@Setter
@ToString
@TableName("base_global_param_category")
public class BaseGlobalParamCategory implements Serializable {

    
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 订单类型
     */
    private String orderType;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类英文
     */
    private String categoryNameEn;

    /**
     * 序号
     */
    private Integer sort;

    /**
     * 表格分类子集
     */
    private String childTable;

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
