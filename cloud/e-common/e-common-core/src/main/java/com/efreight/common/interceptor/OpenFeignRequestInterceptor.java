package com.efreight.common.interceptor;

import com.efreight.common.constants.CommonConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;

import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Feign请求token透传处理器
 *
 * @author 张永伟
 * @since 2023/4/26
 */
public class OpenFeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        String traceId = MDC.get(CommonConstant.TRACE_ID);
        template.header(CommonConstant.TRACE_ID, traceId);
        // 从header获取token
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            ServletRequestAttributes attr = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest request = attr.getRequest();
            String token = request.getHeader(HttpHeaders.AUTHORIZATION);
            String timeZoneId = request.getHeader(CommonConstant.TIME_ZONE);
            String lang = request.getHeader(CommonConstant.LANGUAGE_PARAM);
            //feign传递token、时区、语言信息
            template.header(HttpHeaders.AUTHORIZATION, token);
            template.header(CommonConstant.TIME_ZONE, timeZoneId);
            template.header(CommonConstant.LANGUAGE_PARAM, lang);
        }
    }
}
