package com.efreight.common.global.login;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * HRS 用户部门
 * </p>
 *
 * @author Ma YuLong
 * @since 2023-08-18
 */
@Getter
@Setter
@Schema(description = "HRS 签约公司")
public class LoginDeptVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "部门id")
    private Long id;

    @Schema(description = "部门名称")
    private String deptName;

}