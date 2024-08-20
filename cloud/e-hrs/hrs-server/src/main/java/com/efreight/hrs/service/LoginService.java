package com.efreight.hrs.service;

import com.efreight.common.global.login.LoginVO;
import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * @author 马玉龙
 * @since 2023/8/18
 */
public interface LoginService {
    ImmutablePair<String, LoginVO> loginEndpoint(String loginWay, String loginName, String password);

    ImmutablePair<String, LoginVO>  switchLoginEndPoint(Long orgId,Long userId);
}
