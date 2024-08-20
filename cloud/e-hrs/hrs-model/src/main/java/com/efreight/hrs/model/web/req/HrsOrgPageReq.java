package com.efreight.hrs.model.web.req;

import java.io.Serializable;
import java.util.List;

import com.efreight.common.global.BasePageParam;
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
@Schema(name = "HrsOrgQueryReq对象")
public class HrsOrgPageReq extends BasePageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "区域ids")
    private List<Long> areaIds;
    @Schema(description = "公司名称")
    private String orgName;
    @Schema(description = "公司id")
    private String orgId;
    @Schema(description = "签约公司代码")
    private String orgCode;
    @Schema(description = "社会信用代码")
    private String socialCreditCode;
    @Schema(description = "开户行账号")
    private String bankAccount;
    @Schema(description = "开户行名称")
    private String bankName;
    @Schema(description = "开户行电话")
    private String bankTel;
    @Schema(description = "开户行地址")
    private String orgAddress;

    @Schema(description = "编辑人ID集合")
    private List<Long> editIds;

    @Schema(description = "编辑开始时间")
    private String editBeginDate;

    @Schema(description = "编辑结束时间")
    private String editEndDate;


}
