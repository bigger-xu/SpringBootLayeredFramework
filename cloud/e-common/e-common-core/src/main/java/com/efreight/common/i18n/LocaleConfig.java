package com.efreight.common.i18n;

import java.util.Locale;

import com.efreight.common.constants.CommonConstant;
import com.efreight.common.interceptor.UserContextInterceptor;
import org.jetbrains.annotations.NotNull;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * 国际化语言默认设置
 *
 * @author 张永伟
 * @since 2023/7/3
 */
@Configuration
public class LocaleConfig {
    /**
     * 默认解析器 其中locale表示默认语言
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return localeResolver;
    }

    /**
     * 默认拦截器 其中lang表示切换语言的参数名
     */
    @Bean
    public WebMvcConfigurer localeInterceptor() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(@NotNull InterceptorRegistry registry) {
                CustomerLocaleChangeInterceptors localeInterceptor = new CustomerLocaleChangeInterceptors();
                UserContextInterceptor userContextInterceptor = new UserContextInterceptor();
                localeInterceptor.setParamName(CommonConstant.LANGUAGE_PARAM);
                registry.addInterceptor(localeInterceptor);
                registry.addInterceptor(userContextInterceptor);
            }
        };
    }
}
