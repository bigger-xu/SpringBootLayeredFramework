package com.cto.cloud.configurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

import javax.sql.DataSource;

/**
 * @author Zhang Yongwei
 * @version 1.0
 * @date 2020/2/12
 */
@Configuration
@EnableJdbcHttpSession
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public TokenStore tokenStore() {
//        return new JdbcTokenStore(dataSource);
        return new JwtTokenStore(jwtAccessTokenConverter());
    }
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey("qRU5LLDPOJ0s7niuE!LyjETh*EPKdOE9TBURAhWS2n3fAXXy#!mqdPDljOHInWGf");  //签名的key
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("594cto.jks"), "123456".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("594cto"));
        return converter;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.userDetailsService(userDetailsService)
                 .tokenStore(tokenStore())
                 .tokenEnhancer(jwtAccessTokenConverter())
                 .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
       /* clients.inMemory()
                .withClient("order")       //应用ID
                .secret(passwordEncoder.encode("123456"))    //应用密码
                .scopes("read","write")      //应用权限
                .accessTokenValiditySeconds(1800)     //token的失效时间
                .resourceIds("order-server")       //允许访问的服务ID
                .authorizedGrantTypes("password");     //授权方式*/
       clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("isAuthenticated()") //暴露验证服务/oauth/token_key   permitAll()为关闭
                .checkTokenAccess("isAuthenticated()");//暴露验证服务/oauth/check_token
    }
}
