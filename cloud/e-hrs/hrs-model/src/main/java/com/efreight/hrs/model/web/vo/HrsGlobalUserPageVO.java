package com.efreight.hrs.model.web.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * HRS 签约公司
 * </p>
 *
 * @author Ma YuLong
 * @since 2023-08-18
 */
@Getter
@Setter
@Schema(name = "HrsGlobalUserPageVO对象", description = "HRS 签约公司")
public class HrsGlobalUserPageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "签约公司ID")
    private Long orgId;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "签约公司全称")
    private String orgName;

    @Schema(description = "工号")
    private String jobNumber;

    @Schema(description = "邮箱")
    private String userEmail;

    @Schema(description = "上级部门/部门拼接")
    private String parentChildDeptName;
    @Schema(description = "电话号码")
    private String phoneNumber;

    @Schema(description = "姓名 中文")
    private String userName;

    @Schema(description = "姓名 英文")
    private String userEname;

    @Schema(description = "创建人")
    private String creatorName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "操作人")
    private String editorName;

    @Schema(description = "操作时间")
    private LocalDateTime editTime;
    @Schema(description = "备注")
    private String remark;

}