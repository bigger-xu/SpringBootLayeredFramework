package com.efreight.common.global;

import java.util.Objects;
import java.util.Optional;

import cn.dev33.satoken.context.SaHolder;
import com.efreight.common.constants.HrsResultCode;
import com.efreight.common.global.login.LoginDeptVO;
import com.efreight.common.global.login.LoginOrgVO;
import com.efreight.common.global.login.LoginUserVO;
import com.efreight.common.global.login.LoginVO;
import com.efreight.common.utils.BizExceptionCheckUtils;

/**
 * 基础controller
 *
 * @author 马玉龙
 * @since 2023/8/14
 */
public abstract class BaseController {
    private final static String USER_DETAIL = "USER_DETAIL";

    private LoginVO currentLoginInfo() {
        Object userDetail = SaHolder.getStorage().get(USER_DETAIL);
        BizExceptionCheckUtils.isTrue(Objects.isNull(userDetail), HrsResultCode.INVALID_TOKEN_ERROR);
        return (LoginVO) userDetail;
    }

    public LoginUserVO currentLoginUser() {
        return currentLoginInfo().getLoginUserVO();
    }

    public LoginDeptVO currentLoginDept() {
        return Optional.ofNullable(currentLoginInfo().getLoginDeptVO()).orElse(new LoginDeptVO());
    }

    public LoginOrgVO currentLoginOrg() {
        return currentLoginInfo().getLoginOrgVO();
    }

    public Long currentLoginUserId() {
        return currentLoginUser().getId();
    }

    public Long currentLoginUserOrgId() {
        return currentLoginOrg().getId();
    }

    public LoginVO currentLoginVo(){
        return  currentLoginInfo();
    }

    public Long currentLoginUserMasterRoleId() {
        return currentLoginUser().getMasterRoleId();
    }
}
