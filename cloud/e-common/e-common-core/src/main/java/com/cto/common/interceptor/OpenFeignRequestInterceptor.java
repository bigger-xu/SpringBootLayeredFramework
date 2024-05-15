package com.cto.common.interceptor;


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

    private static final String TRACE_ID = "traceId";

    @Override
    public void apply(RequestTemplate template) {
        String traceId = MDC.get(TRACE_ID);
        template.header(TRACE_ID, traceId);

        String token;
        // 从header获取token
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            ServletRequestAttributes attr = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest request = attr.getRequest();
            token = request.getHeader(HttpHeaders.AUTHORIZATION);
            //feign调用赋值token。这样的话，目标接口如果有鉴权也是可以获取到token的。
            template.header(HttpHeaders.AUTHORIZATION, token);
        }
    }
}
