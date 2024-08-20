package com.efreight.hrs.model.web.req;


import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "HrsOrgInterfaceReq对象", description = "HRS 签约公司对外接口")
public class HrsOrgInterfaceReq implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "签约公司ID")
    private Long orgId;

    @Schema(description = "AE/AI")
    private String orderType;

    @Schema(description = "接口类型 FWB运单 FFM舱单 CARGO货站")
    private String interfaceType;

    @Schema(description = "接口名称，代码中有对应枚举")
    private String interfaceName;

    @Schema(description = "接口描述")
    private String interfaceLable;

    @Schema(description = "EF 翌飞  OTHER 其他")
    private String source;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "是否启用：Y,N")
    private String enable;

    @Schema(description = "AppID 或者 申报单位代码")
    private String appid;

    @Schema(description = "接口地址")
    private String urlAuth;

    @Schema(description = "用户名")
    private String serverUserName;

    @Schema(description = "用户密码")
    private String serverPassword;

    @Schema(description = "用户code")
    private String serverCode;

    @Schema(description = "创建人ID")
    private Long creatorId;

    @Schema(description = "创建人")
    private String creatorName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "操作人ID")
    private Long editorId;

    @Schema(description = "操作人")
    private String editorName;

    @Schema(description = "操作时间")
    private LocalDateTime editTime;
}