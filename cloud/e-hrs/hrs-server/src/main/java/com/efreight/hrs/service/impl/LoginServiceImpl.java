package com.efreight.hrs.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.efreight.common.constants.HrsResultCode;
import com.efreight.common.exception.EfreightBizException;
import com.efreight.common.global.login.LoginDeptVO;
import com.efreight.common.global.login.LoginOrgVO;
import com.efreight.common.global.login.LoginUserVO;
import com.efreight.common.global.login.LoginVO;
import com.efreight.common.utils.BizExceptionCheckUtils;
import com.efreight.hrs.constants.HrsConstants;
import com.efreight.hrs.entity.HrsOrgUser;
import com.efreight.hrs.entity.HrsUser;
import com.efreight.hrs.entity.HrsUserRole;
import com.efreight.hrs.service.IHrsOrgService;
import com.efreight.hrs.service.IHrsOrgUserService;
import com.efreight.hrs.service.IHrsUserDeptService;
import com.efreight.hrs.service.IHrsUserRoleService;
import com.efreight.hrs.service.IHrsUserService;
import com.efreight.hrs.service.LoginService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jetbrains.annotations.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author 马玉龙
 * @since 2023/8/18
 */

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Resource
    private IHrsUserService hrsUserService;
    @Resource
    private IHrsOrgUserService hrsOrgUserService;

    @Resource
    private IHrsOrgService hrsOrgService;
    @Resource
    private IHrsUserRoleService hrsUserRoleService;

    @Resource
    private IHrsUserDeptService hrsUserDeptService;


    private final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    @Override
    public ImmutablePair<String, LoginVO> loginEndpoint(String loginWay, String loginName, String password) {
        LambdaQueryWrapper<HrsUser> queryWrapper = Wrappers.<HrsUser>lambdaQuery()
                .eq(HrsUser::getUserStatus, HrsConstants.Y)
                .and(item->item.eq(HrsUser::getUserEmail,loginName)
                        .or()
                        .eq(HrsUser::getPhoneNumber,loginName)
                        .or()
                        .eq(HrsUser::getLoginName,loginName)
                    );
        List<HrsUser> users = hrsUserService.list(queryWrapper);
        BizExceptionCheckUtils.isTrue(CollectionUtil.isEmpty(users), HrsResultCode.INVALID_USER);
        BizExceptionCheckUtils.isTrue(users.size() > 1, HrsResultCode.USER_LOGINNAME_MULTIPLE);
        BizExceptionCheckUtils.isTrue(!ENCODER.matches(password, users.get(0).getPassWord()), HrsResultCode.INVALID_PWD);
        return convert(loginWay, users.get(0), null);
    }

    @NotNull
    private ImmutablePair<String, LoginVO> convert(String loginWay, HrsUser hrsUser, Long orgId) {
        LoginVO loginVO = new LoginVO();
        loginVO.setCurrentDate(LocalDateTime.now());
        LoginUserVO loginUserVO = new LoginUserVO();
        loginUserVO.setLoginWay(loginWay);
        HrsOrgUser hrsOrgUser;
        if (Objects.isNull(orgId)) {
            hrsOrgUser = hrsOrgUserService.queryOrgIdByUserId(hrsUser.getId());
            BizExceptionCheckUtils.isTrue(Objects.isNull(hrsOrgUser), HrsResultCode.NO_DEFAULT_ORG);
        } else {
            hrsOrgUser = hrsOrgUserService.queryOrgIdByUidOid(hrsUser.getId(), orgId);
            BizExceptionCheckUtils.isTrue(Objects.isNull(hrsOrgUser), HrsResultCode.HRS_ORG_USER_INVALID);
        }
        LoginOrgVO loginOrgVO = hrsOrgService.getOrgAndOrgConfig(hrsOrgUser.getOrgId());
        HrsUserRole masterRole = hrsUserRoleService.getBaseMapper().selectOne(Wrappers.<HrsUserRole>lambdaQuery()
                                                                                       .eq(HrsUserRole::getRoleType, HrsConstants.MASTER)
                                                                                       .eq(HrsUserRole::getUserId, hrsUser.getId())
                                                                                       .eq(HrsUserRole::getOrgId, hrsOrgUser.getOrgId()));
        Optional.ofNullable(masterRole).orElseThrow(() -> {
            log.error("登陆人userId={}-登陆账号={}，没有主角色请当心！！！", hrsUser.getId(), hrsUser.getLoginName());
            return new EfreightBizException(HrsResultCode.MASTER_ROLE_NULL);
        });
        LoginDeptVO userDeptInfo = hrsUserDeptService.getUserDeptInfo(hrsOrgUser.getUserId(), hrsOrgUser.getOrgId());
        BeanUtil.copyProperties(hrsUser, loginUserVO);
        loginUserVO.setOrderPermission(hrsOrgUser.getOrderPermission());
        loginUserVO.setOrderPermissionAi(hrsOrgUser.getOrderPermissionAi());
        loginUserVO.setMasterRoleId(masterRole.getRoleId());
        loginVO.setLoginOrgVO(loginOrgVO);
        loginVO.setLoginUserVO(loginUserVO);
        loginVO.setLoginDeptVO(userDeptInfo);
        return ImmutablePair.of(String.format(HrsConstants.LOGIN_ID, hrsOrgUser.getOrgId(), hrsOrgUser.getUserId()), loginVO);
    }

    @Override
    public ImmutablePair<String, LoginVO> switchLoginEndPoint(Long orgId, Long userId) {
        HrsUser hrsUser = hrsUserService.getById(userId);
        return convert(null, hrsUser, orgId);
    }
}
