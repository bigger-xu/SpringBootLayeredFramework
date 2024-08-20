package com.efreight.common.filter;

import java.io.IOException;
import java.util.UUID;

import com.efreight.common.constants.CommonConstant;
import com.efreight.common.utils.EfRandomUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 请求增加traceId，方便日志排查问题
 *
 * @author libiao
 */
@Order(100)
@Component
public class RequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String traceId = request.getHeader(CommonConstant.TRACE_ID);
            if (StringUtils.isEmpty(traceId)) {
                traceId = UUID.randomUUID().toString();
            }
            MDC.put(CommonConstant.TRACE_ID, traceId);
            MDC.put(CommonConstant.REQUEST_ID, EfRandomUtils.randomRequestId());
            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}
