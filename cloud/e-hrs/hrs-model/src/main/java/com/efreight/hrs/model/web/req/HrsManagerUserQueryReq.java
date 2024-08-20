package com.efreight.hrs.model.web.req;

import java.io.Serializable;
import java.util.List;

import com.efreight.common.global.BasePageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * HRS 用户表
 * </p>
 *
 * @author Ma YuLong
 * @since 2023-08-21
 */
@Getter
@Setter
@Schema(name = "管理用户查询入参对象")
public class HrsManagerUserQueryReq extends BasePageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "分公司id")
    private Long  orgId;
    @Schema(description = "邮箱")
    private String userEmail;

    @Schema(description = "电话号码")
    private String phoneNumber;

    @Schema(description = "姓名")
    private String userName;

    @Schema(description = "工号")
    private String jobNumber;

    @Schema(description = "角色ids")
    private List<Long> roleIds;
    @Schema(description = "数据集权限")
    private List<String> orderPermissions;
    @Schema(description = "进口数据集权限")
    private List<String> orderPermissionsAi;
    @Schema(description = "编辑人ID集合")
    private List<Long> editIds;
    @Schema(description = "编辑开始时间")
    private String editBeginDate;
    @Schema(description = "编辑结束时间")
    private String editEndDate;

}
