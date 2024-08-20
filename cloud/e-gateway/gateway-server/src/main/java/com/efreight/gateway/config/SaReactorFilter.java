package com.efreight.gateway.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.exception.BackResultException;
import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.exception.StopMatchException;
import cn.dev33.satoken.filter.SaFilter;
import cn.dev33.satoken.filter.SaFilterAuthStrategy;
import cn.dev33.satoken.filter.SaFilterErrorStrategy;
import cn.dev33.satoken.reactor.context.SaReactorHolder;
import cn.dev33.satoken.reactor.context.SaReactorSyncHolder;
import cn.dev33.satoken.reactor.error.SaReactorSpringBootErrorCode;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.util.SaTokenConsts;
import reactor.core.publisher.Mono;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;

/**
 * 重写saToken过滤器
 *
 * @author ZhangYongWei
 * @since 2024/8/19
 */
public class SaReactorFilter implements SaFilter, GlobalFilter, Ordered {
    
    // ------------------------ 设置此过滤器 拦截 & 放行 的路由
    
    /**
     * 拦截路由
     */
    public List<String> includeList = new ArrayList<>();
    
    /**
     * 放行路由
     */
    public List<String> excludeList = new ArrayList<>();
    
    /**
     * 认证函数：每次请求执行
     */
    public SaFilterAuthStrategy auth = r -> {
    };
    
    /**
     * 异常处理函数：每次[认证函数]发生异常时执行此函数
     */
    public SaFilterErrorStrategy error = e -> {
        throw new SaTokenException(e).setCode(SaReactorSpringBootErrorCode.CODE_20205);
    };
    
    /**
     * 前置函数：在每次[认证函数]之前执行
     * <b>注意点：前置认证函数将不受 includeList 与 excludeList 的限制，所有路由的请求都会进入 beforeAuth</b>
     */
    public SaFilterAuthStrategy beforeAuth = r -> {
    };
    
    @Override
    public SaReactorFilter addInclude(String... paths) {
        includeList.addAll(Arrays.asList(paths));
        return this;
    }
    
    
    // ------------------------ 钩子函数
    
    @Override
    public SaReactorFilter addExclude(String... paths) {
        excludeList.addAll(Arrays.asList(paths));
        return this;
    }
    
    @Override
    public SaReactorFilter setIncludeList(List<String> pathList) {
        includeList = pathList;
        return this;
    }
    
    @Override
    public SaReactorFilter setExcludeList(List<String> pathList) {
        excludeList = pathList;
        return this;
    }
    
    @Override
    public SaReactorFilter setAuth(SaFilterAuthStrategy auth) {
        this.auth = auth;
        return this;
    }
    
    @Override
    public SaReactorFilter setError(SaFilterErrorStrategy error) {
        this.error = error;
        return this;
    }
    
    @Override
    public SaReactorFilter setBeforeAuth(SaFilterAuthStrategy beforeAuth) {
        this.beforeAuth = beforeAuth;
        return this;
    }
    
    
    // ------------------------ filter
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 写入WebFilterChain对象
        exchange.getAttributes().put(SaReactorHolder.CHAIN_KEY, chain);
        
        // ---------- 全局认证处理
        try {
            // 写入全局上下文 (同步)
            SaReactorSyncHolder.setContext(exchange);
            
            // 执行全局过滤器
            beforeAuth.run(null);
            SaRouter.match(includeList).notMatch(excludeList).check(r -> {
                auth.run(null);
            });
            
        } catch (StopMatchException e) {
            // StopMatchException 异常代表：停止匹配，进入Controller
            
        } catch (Throwable e) {
            // 1. 获取异常处理策略结果
            String result = (e instanceof BackResultException) ? e.getMessage() : String.valueOf(error.run(e));
            
            // 2. 写入输出流
            // 		请注意此处默认 Content-Type 为 text/plain，如果需要返回 JSON 信息，需要在 return 前自行设置 Content-Type 为 application/json
            // 		例如：SaHolder.getResponse().setHeader("Content-Type", "application/json;charset=UTF-8");
            if(exchange.getResponse().getHeaders().getFirst(SaTokenConsts.CONTENT_TYPE_KEY) == null) {
                exchange.getResponse().getHeaders().set(SaTokenConsts.CONTENT_TYPE_KEY, SaTokenConsts.CONTENT_TYPE_TEXT_PLAIN);
            }
            SaHolder.getResponse().setHeader("Content-Type", "application/json;charset=UTF-8");
            return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(result.getBytes())));
            
        } finally {
            // 清除上下文
            SaReactorSyncHolder.clearContext();
        }
        
        // ---------- 执行
        
        // 写入全局上下文 (同步)
        SaReactorSyncHolder.setContext(exchange);
        
        // 执行
        return chain.filter(exchange).contextWrite(ctx -> {
            // 写入全局上下文 (异步)
            ctx = ctx.put(SaReactorHolder.CONTEXT_KEY, exchange);
            return ctx;
        }).doFinally(r -> {
            // 清除上下文
            SaReactorSyncHolder.clearContext();
        });
    }
    
    @Override
    public int getOrder() {
        return -9;
    }
    
    
}
