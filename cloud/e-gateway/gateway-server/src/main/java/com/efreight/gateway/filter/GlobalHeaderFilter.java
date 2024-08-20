package com.efreight.gateway.filter;

import java.util.function.Consumer;

import cn.hutool.http.Header;
import org.slf4j.MDC;
import reactor.core.publisher.Mono;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * header参数透传过滤器
 *
 * @author 张永伟
 * @since 2023/7/6
 */
@Component
public class GlobalHeaderFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            ServerHttpRequest request = exchange.getRequest();
            String timeZone = request.getHeaders().getFirst("timeZone");
            String lang = request.getHeaders().getFirst("lang");
            String authorization = request.getHeaders().getFirst(Header.AUTHORIZATION.getValue());
            // 添加header
            Consumer<HttpHeaders> httpHeaders = httpHeader -> {
                // 设置header，向后传递
                httpHeader.set("lang", lang);
                httpHeader.set("timeZone", timeZone);
                httpHeader.set(Header.AUTHORIZATION.getValue(), authorization);
            };
            ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers(httpHeaders).build();
            ServerWebExchange build = exchange.mutate().request(serverHttpRequest).build();
            return chain.filter(build);
        } finally {
            MDC.clear();
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
