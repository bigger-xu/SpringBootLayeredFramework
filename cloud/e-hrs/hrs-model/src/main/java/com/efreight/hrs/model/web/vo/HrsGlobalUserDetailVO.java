package com.efreight.hrs.model.web.vo;

import java.io.Serializable;
import java.util.List;

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
@Schema(name = "HrsGlobalUserDetailVO详情")
public class HrsGlobalUserDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "工号")
    private String jobNumber;

    @Schema(description = "邮箱")
    private String userEmail;
    @Schema(description = "姓名 中文")
    private String userName;

    @Schema(description = "姓名 英文")
    private String userEname;

    @Schema(description = "电话号码")
    private String phoneNumber;
    @Schema(description = "公司部门信息")
    private List<OrgDeptVO> orgDeptVOList;
    @Schema(description = "备注")
    private String remark;

}