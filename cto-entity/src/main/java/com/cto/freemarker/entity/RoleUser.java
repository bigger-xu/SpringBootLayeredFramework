package com.cto.freemarker.entity;

import com.cto.freemarker.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 用户角色对应关系
 * </p>
 *
 * @author Bigger-Xu
 * @since 2020-09-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String uuid;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 添加人ID
     */
    private Long addUserId;

    /**
     * 更新时间
     */
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
     * 用户ID
     */
    private Long userId;


}
