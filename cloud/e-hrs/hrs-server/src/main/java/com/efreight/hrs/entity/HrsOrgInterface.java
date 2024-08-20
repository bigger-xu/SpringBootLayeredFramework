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
 * HRS 签约公司对外接口
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Getter
@Setter
@TableName("hrs_org_interface")
public class HrsOrgInterface implements Serializable {

    
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 签约公司ID
     */
    private Long orgId;

    /**
     * AE\AI
     */
    private String orderType;

    /**
     * 接口类型 FWB运单 FFM舱单 CARGO货站
     */
    private String interfaceType;

    /**
     * 接口名称，代码中有对应枚举
     */
    private String interfaceName;

    /**
     * 接口描述
     */
    private String interfaceLable;

    /**
     * EF 翌飞  OTHER 其他
     */
    private String source;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否启用：Y,N
     */
    private String enable;

    /**
     * AppID 或者 申报单位代码
     */
    private String appid;

    /**
     * 接口地址
     */
    private String urlAuth;

    /**
     * 用户名
     */
    private String serverUserName;

    /**
     * 用户密码
     */
    private String serverPassword;

    /**
     * 用户code
     */
    private String serverCode;

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
