package com.efreight.hrs.model.web.vo;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 马玉龙
 * @since 2023/8/30
 */
@Getter
@Setter
@Schema(name = "OrgDeptVO")
public class OrgDeptVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户Id")
    private Long userId;
    @Schema(description = "公司id")
    private Long orgId;
    @Schema(description = "部门id")
    private Long deptId;
    @Schema(description = "部门名称")
    private String deptName;
    @Schema(description = "父级部门id")
    private Long parentDeptId;
    @Schema(description = "是否启用")
    private String used;

}
