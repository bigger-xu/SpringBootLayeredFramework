package com.efreight.oauth2.resolve;

import cn.dev33.satoken.oauth2.logic.SaOAuth2Template;
import cn.dev33.satoken.oauth2.model.AccessTokenModel;
import cn.dev33.satoken.oauth2.model.RequestAuthModel;
import cn.dev33.satoken.oauth2.model.SaClientModel;

import org.springframework.stereotype.Component;

/**
 *
 * @author 马玉龙
 * @since 2023/8/11
 */
@Component
public class SaOAuth2TemplateImpl extends SaOAuth2Template {

    // 根据 id 获取 Client 信息
    @Override
    public SaClientModel getClientModel(String clientId) {
        // 此为模拟数据，真实环境需要从数据库查询
        if("efreight".equals(clientId)) {
            return new SaClientModel()
                    .setClientId("efreight")
                    .setClientSecret("aaaa-bbbb-cccc-dddd-eeee")
                    .setAllowUrl("*")
                    .setContractScope("userinfo")
                    .setIsAutoMode(true);
        }
        return null;
    }

    // 根据ClientId 和 LoginId 获取openid
    @Override
    public String getOpenid(String clientId, Object loginId) {
        // 此为模拟数据，真实环境需要从数据库查询
        return "gr_SwoIN0MC1ewxHX_vfCW3BothWDZMMtx__";
    }
    @Override
    public SaClientModel checkClientSecretAndScope(String clientId, String clientSecret, String scopes) {
        // 返回数据
        return getClientModel(clientId);
    }

    @Override
    public String randomAccessToken(String clientId, Object loginId, String scope) {
        return super.randomAccessToken(clientId, loginId, scope);
    }
    @Override
    public AccessTokenModel generateAccessToken(RequestAuthModel ra, boolean isCreateRt) {
        return new AccessTokenModel();
    }

}