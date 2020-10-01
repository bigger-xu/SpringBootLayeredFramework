package com.cto.freemarker.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.cto.freemarker.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 系统菜单表
 * </p>
 *
 * @author Bigger-Xu
 * @since 2020-09-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

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
     * 更新人ID
     */
    private Long updateUserId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 系统url
     */
    private String url;

    /**
     *  父id 关联sys_menu.id
     */
    private Long parentId;

    /**
     * 权限标识符
     */
    private String permission;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否显示（0：是 1：否）
     */
    private Integer state;

    /**
     * 是否删除,0=未删除，1=已删除
     */
    private Integer deleteFlag;


}
