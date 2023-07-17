package com.cto.freemarker.entity.base;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author Zhang Yongei
 * @version 1.0
 * @date 2019-03-25
 */
@Data
public class BaseEntity implements Serializable {
    private Long id;
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
    @TableField(fill = FieldFill.INSERT)
    private Long addUserId;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 更新人ID
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long updateUserId;
}
