package com.cto.common.i18n;

import java.util.TimeZone;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * 自定义国际语言拦截器
 *
 * @author 张永伟
 * @since 2023/7/4
 */
public class CustomerLocaleChangeInterceptors extends LocaleChangeInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //获取时区
        String timeZoneId = request.getHeader("timeZone");
        if (StringUtils.isNotBlank(timeZoneId)) {
            TimeZoneContext.setContext(TimeZone.getTimeZone(timeZoneId));
        } else {
            TimeZoneContext.setContext(TimeZone.getDefault());
        }
        // 从URL中读取
        String newLocale1 = request.getParameter(this.getParamName());
        // 从headers头部中读取
        String newLocale2 = request.getHeader(this.getParamName());
        String newLocale = null;
        if (StringUtils.isNotBlank(newLocale1)) {
            newLocale = newLocale1;
        } else if (StringUtils.isNotBlank(newLocale2)) {
            newLocale = newLocale2;
        }
        if (newLocale != null && this.checkHttpMethod(request.getMethod())) {
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            if (localeResolver == null) {
                throw new IllegalStateException("在DispatcherServlet请求中找不到LocaleResolver");
            }

            try {
                localeResolver.setLocale(request, response, this.parseLocaleValue(newLocale));
            } catch (IllegalArgumentException var7) {
                if (!this.isIgnoreInvalidLocale()) {
                    throw var7;
                }
                this.logger.debug("忽略无效的区域 [" + newLocale + "]: " + var7.getMessage());
            }
        }
        return true;
    }

    private boolean checkHttpMethod(String currentMethod) {
        String[] configuredMethods = this.getHttpMethods();
        if (ObjectUtils.isEmpty(configuredMethods)) {
            return true;
        } else {
            for (String configuredMethod : configuredMethods) {
                if (configuredMethod.equalsIgnoreCase(currentMethod)) {
                    return true;
                }
            }
            return false;
        }
    }
}
