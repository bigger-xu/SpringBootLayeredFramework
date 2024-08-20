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
 * HRS 签约公司
 * </p>
 *
 * @author caiwd
 * @since 2024-08-13
 */
@Getter
@Setter
@TableName("hrs_org")
public class HrsOrg implements Serializable {

    
    private static final long serialVersionUID = 1L;

    /**
     * 签约公司ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 区域id
     */
    private Long areaId;

    /**
     * 签约公司全称
     */
    private String orgName;

    /**
     * 签约公司代码
     */
    private String orgCode;

    /**
     * 签约公司简称
     */
    private String shortName;

    /**
     * 签约公司英文全称
     */
    private String orgEname;

    /**
     * 签约公司英文简称
     */
    private String shortEname;

    /**
     * 社会信用代码
     */
    private String socialCreditCode;

    /**
     * 开户行账号 英文
     */
    private String bankAccountEn;

    /**
     * 开户行账号中文
     */
    private String bankAccountCn;

    /**
     * 开户行名称_中文
     */
    private String bankNameCn;

    /**
     * 开户行名称_英文
     */
    private String bankNameEn;

    /**
     * 开户行电话 中文
     */
    private String bankTelCn;

    /**
     * 开户行电话 英文
     */
    private String bankTelEn;

    /**
     * 签约公司地址信息_中文
     */
    private String orgAddressInfoCn;

    /**
     * 签约公司地址信息_英文
     */
    private String orgAddressInfoEn;

    /**
     * 签约公司logo
     */
    private String orgLogo;

    /**
     * 签约公司印章
     */
    private String orgSeal;

    /**
     * 本币位
     */
    private String localCurrency;

    /**
     * 指定城市
     */
    private String executionPort;

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
