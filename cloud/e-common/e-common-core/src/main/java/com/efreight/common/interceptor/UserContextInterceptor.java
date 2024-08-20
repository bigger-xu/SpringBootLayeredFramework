package com.efreight.common.interceptor;

import java.util.Objects;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.efreight.common.constants.CommonResultCode;
import com.efreight.common.utils.BizExceptionCheckUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author 马玉龙
 * @since 2023/9/11
 */
public class UserContextInterceptor implements HandlerInterceptor {
    
    private final static String USER_DETAIL = "USER_DETAIL";
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isNotBlank(accessToken) && accessToken.startsWith("Bearer")) {
            Object userDetail = StpUtil.getSession().get(SaSession.USER);
            BizExceptionCheckUtils.isTrue(Objects.isNull(userDetail), CommonResultCode.NEED_LOGIN);
            SaHolder.getStorage().set(USER_DETAIL, userDetail);
        }
        return true;
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable Exception ex) {
        String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isNotBlank(accessToken)) {
            SaHolder.getStorage().delete(USER_DETAIL);
        }
    }
}
