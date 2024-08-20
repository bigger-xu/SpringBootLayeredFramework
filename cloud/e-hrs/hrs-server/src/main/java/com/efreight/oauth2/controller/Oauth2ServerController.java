package com.efreight.oauth2.controller;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.oauth2.config.SaOAuth2Config;
import cn.dev33.satoken.oauth2.logic.SaOAuth2Handle;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaTokenConsts;
import cn.hutool.core.util.StrUtil;
import com.efreight.common.constants.HrsResultCode;
import com.efreight.common.global.login.LoginVO;
import com.efreight.common.response.MessageInfo;
import com.efreight.common.utils.BizExceptionCheckUtils;
import com.efreight.common.utils.RedisUtil;
import com.efreight.hrs.constants.HrsConstants;
import com.efreight.hrs.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 马玉龙
 * @since 2023/8/10
 */
@Slf4j
@RestController
@Tag(name = "登录")
public class Oauth2ServerController {
    @Resource
    private LoginService loginService;
    @Resource
    private RedisUtil redisUtil;

    /**
     * 登陆入口
     */
    @PostMapping("/oauth2/token")
    @Operation(summary = "登录接口")
    public MessageInfo<LoginVO> request(
            @Parameter(name = "grant_type", description = "授权方式", required = true, example = "password")
            @RequestParam(value = "grant_type", required = true, defaultValue = "password") String grant_type,
            @Parameter(name = "username", description = "用户名", required = true, example = "邮箱或者（区号_手机号)或者登录名")
            @RequestParam(value = "username", required = true, defaultValue = "") String username,
            @Parameter(name = "password", description = "密码", required = true, example = "你的密码")
            @RequestParam(value = "password", required = true, defaultValue = "") String password,
            @Parameter(name = "client_id", description = "客户端id", required = true, example = "test")
            @RequestParam(value = "client_id", required = true, defaultValue = "efreight") String client_id,
            @Parameter(name = "client_secret", description = "应用密钥", required = true, example = "自定义")
            @RequestParam(value = "client_secret", required = true, defaultValue = "aaaa-bbbb-cccc-dddd-eeee") String client_secret,
            @Parameter(name = "loginWay", description = "登录方式", required = true, example = "EMAIL")
            @RequestParam(value = "loginWay", required = true, defaultValue = "EMAIL") String loginWay,
            @RequestParam(value = "imageToken", required = false) String imageToken,
            @RequestParam(value = "imageCode", required = false) String imageCode,
            @Parameter(name = "source", description = "登录来源", required = true, example = "PC")
            @RequestParam(value = "source", required = false) String source
    ) {
        if (StringUtils.isNotEmpty(source)) {
            Object code = redisUtil.get(String.format(HrsConstants.IMAGE_KEY, imageToken));
            BizExceptionCheckUtils.isNull(code, HrsResultCode.IMAGE_CODE_ERROR);
            BizExceptionCheckUtils.isTrue(!imageCode.equals(code.toString()), HrsResultCode.IMAGE_CODE_ERROR);
        }
        SaOAuth2Handle.serverRequest();
        redisUtil.del(String.format(HrsConstants.IMAGE_KEY, imageToken));
        return MessageInfo.ok((LoginVO) SaManager.getSaTokenContext().getStorage().get("user_detail"));
    }

    @Autowired
    public void setSaOAuth2Config(SaOAuth2Config cfg) {

        cfg.setDoLoginHandle((username, pwd) -> {
            String loginWay = SaHolder.getRequest().getParam("loginWay");
            ImmutablePair<String, LoginVO> pair = loginService.loginEndpoint(loginWay, username, pwd);
            StpUtil.login(pair.getLeft());
            String accessToken = (String) SaHolder.getStorage().get(SaTokenConsts.JUST_CREATED_NOT_PREFIX);
            pair.getRight().setAccessToken(accessToken);
            SaHolder.getStorage().set("user_detail", pair.getRight());
            StpUtil.getSession().set(SaSession.USER, pair.getRight());
            return pair.getRight();
        }).setIsPassword(true);
    }

    @GetMapping(value = "/oauth2/logout")
    @Operation(summary = "登出接口")
    public MessageInfo<Boolean> logout(
            @Parameter(name = "Authorization", description = "access_token", required = true, example = "Bearer b167d141-ea62-430d-bdb0-bacf17372862")
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
        if (StrUtil.isBlank(authHeader)) {
            return MessageInfo.ok(Boolean.FALSE, "退出失败，token 为空");
        }
        StpUtil.logout();
        return MessageInfo.ok(Boolean.TRUE);
    }

    @PostMapping("/switchLogin")
    @Operation(summary = "切换登陆")
    public MessageInfo<LoginVO> switchLogin(@RequestParam Long orgId, @RequestParam Long userId) {
        ImmutablePair<String, LoginVO> pair = loginService.switchLoginEndPoint(orgId, userId);
        StpUtil.login(pair.getLeft());
        String accessToken = (String) SaHolder.getStorage().get(SaTokenConsts.JUST_CREATED_NOT_PREFIX);
        pair.getRight().setAccessToken(accessToken);
        StpUtil.getSession().set(SaSession.USER, pair.getRight());
        return MessageInfo.ok(pair.getRight());
    }

    @PostMapping("/oauth2/getLoginUser")
    @Operation(summary = "获取登陆用户")
    public MessageInfo<LoginVO> getLoginUser(@RequestParam Long orgId, @RequestParam Long userId) {
        ImmutablePair<String, LoginVO> pair = loginService.switchLoginEndPoint(orgId, userId);
        return MessageInfo.ok(pair.getRight());
    }

}
