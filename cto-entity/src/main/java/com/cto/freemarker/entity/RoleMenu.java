package com.cto.freemarker.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.cto.freemarker.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 菜单角色对应关系
 * </p>
 *
 * @author Bigger-Xu
 * @since 2020-09-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleMenu extends BaseEntity {

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
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;


}
