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
@Schema(name = "全局用户查询入参对象", description = "HRS 用户表")
public class HrsGlobalUserQueryReq extends BasePageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "邮箱")
    private String userEmail;

    @Schema(description = "电话号码")
    private String phoneNumber;

    @Schema(description = "姓名")
    private String userName;

    @Schema(description = "工号")
    private String jobNumber;

    @Schema(description = "分公司id")
    private List<Long> orgId;

    @Schema(description = "部门 or 上级部门")
    private String deptName;

    @Schema(description = "创建人ID集合")
    private List<Long>  createIds;
    @Schema(description = "编辑人ID集合")
    private List<Long> editIds;

    @Schema(description = "编辑开始时间")
    private String editBeginDate;

    @Schema(description = "编辑结束时间")
    private String editEndDate;




}
