package com.cto.common.handler;

import java.util.Objects;

import com.cto.common.annotation.IgnoreResponseAdvice;
import com.cto.common.response.MessageInfo;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局统一返回格式处理器
 * 如果没有IgnoreResponseAdvice注解，则统一封装成ResultDTO格式返回，如果需要特定格式返回 需要添加IgnoreResponseAdvice注解，可以在类或者方法上
 * {@link com.cto.common.annotation.IgnoreResponseAdvice}
 *
 * @author 张永伟
 * @since 2023/4/24
 */
@RestControllerAdvice(basePackages = "com.cto")
public class GlobalResultAdviceHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> clazz) {
        // 如果当前方法所在的类标识了 IgnoreResponseAdvice 注解, 则不需要处理
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        // 如果当前方法标识了 IgnoreResponseAdvice 注解, 则不需要处理 / 返回true则对响应进行处理, 执行 beforeBodyWrite 方法
        return !Objects.requireNonNull(methodParameter.getMethod()).isAnnotationPresent(IgnoreResponseAdvice.class);
    }

    @Override
    public Object beforeBodyWrite(Object object, MethodParameter methodParameter, MediaType mediaType,
            Class<? extends HttpMessageConverter<?>> clazz, ServerHttpRequest request, ServerHttpResponse response) {
        // 如果 object 是 null, response 不需要设置 data
        if (object == null) {
            return MessageInfo.ok();
            // 如果 object 已经是 MessageInfo 类型, 强转即可
        } else if (object instanceof MessageInfo) {
            return object;
        } else {
            return MessageInfo.ok(object);
        }
    }
}
