package com.efreight.gateway.filter;

import java.util.UUID;

import com.efreight.common.constants.CommonConstant;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * 全链路日志traceId
 *
 * @author 张永伟
 * @since 2023/7/7
 */
@Log4j2
@Component
public class LogTraceIdGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        Object traceId = request.getHeaders().get(CommonConstant.TRACE_ID);
        if (traceId == null) {
            traceId = UUID.randomUUID();
            request = exchange.getRequest().mutate().header(CommonConstant.TRACE_ID, traceId.toString()).build();
        }
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().add(CommonConstant.TRACE_ID, traceId.toString());
        exchange = exchange.mutate().request(request).response(response).build();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {return 0;}
}
