package com.efreight.common.global.login;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 马玉龙
 * @since 2023/8/18
 */
@Data
@Schema(description = "登陆结果信息")
public class LoginVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户分公司信息")
    private LoginOrgVO loginOrgVO;

    @Schema(description = "用户信息")
    private LoginUserVO loginUserVO;

    @Schema(description = "用户部门信息信息")
    private LoginDeptVO loginDeptVO;

    @Schema(description = "access_token")
    private String accessToken;
    
    @Schema(description = "当前服务器时间")
    private LocalDateTime currentDate;

}
