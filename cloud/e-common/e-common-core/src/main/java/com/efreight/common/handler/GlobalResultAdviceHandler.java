package com.efreight.common.handler;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.efreight.common.annotation.IgnoreResponseAdvice;
import com.efreight.common.annotation.ThousandConvert;
import com.efreight.common.response.MessageInfo;
import com.efreight.common.utils.NumberUtil;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import org.springframework.beans.BeanUtils;
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
 * {@link IgnoreResponseAdvice}
 *
 * @author 张永伟
 * @since 2023/4/24
 */
@RestControllerAdvice(basePackages = "com.efreight")
public class GlobalResultAdviceHandler implements ResponseBodyAdvice<Object> {
    
    @Override
    public boolean supports(MethodParameter methodParameter, @NotNull Class<? extends HttpMessageConverter<?>> clazz) {
        // 如果当前方法所在的类标识了 IgnoreResponseAdvice 注解, 则不需要处理
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        // 如果当前方法标识了 IgnoreResponseAdvice 注解, 则不需要处理 / 返回true则对响应进行处理, 执行 beforeBodyWrite 方法
        return !Objects.requireNonNull(methodParameter.getMethod()).isAnnotationPresent(IgnoreResponseAdvice.class);
    }
    
    @Override
    public Object beforeBodyWrite(Object object, @NotNull MethodParameter methodParameter, @NotNull MediaType mediaType,
            @NotNull Class<? extends HttpMessageConverter<?>> clazz, @NotNull ServerHttpRequest request, @NotNull ServerHttpResponse response) {
        // 如果 object 是 null, response 不需要设置 data
        if (object == null) {
            return MessageInfo.ok();
            // 如果 object 已经是 MessageInfo 类型, 强转即可
        } else if (object instanceof MessageInfo) {
            Object data = ((MessageInfo<?>) object).getData();
            convertObj(data);
            return object;
        } else {
            convertObj(object);
            return MessageInfo.ok(object);
        }
    }
    
    private static void convertObj(Object data) {
        if (Objects.isNull(data)) {
            return;
        }
        if (data instanceof Collection<?>) {
            for (Object obj : (Collection<?>) data) {
                if (Objects.nonNull(obj) && obj instanceof Collection<?>) {
                    convertObj(obj);
                    continue;
                }
                if (Objects.nonNull(obj) && !BeanUtils.isSimpleProperty(obj.getClass())) {
                    convertField(obj);
                }
            }
        } else if (data instanceof Map<?, ?>) {
            convertObj(((Map<?, ?>) data).values());
        } else if (!BeanUtils.isSimpleProperty(data.getClass())) {
            convertField(data);
        }
    }
    
    @SneakyThrows
    private static void convertField(Object data) {
        for (Field field : data.getClass().getDeclaredFields()) {
            ThousandConvert annotation = field.getAnnotation(ThousandConvert.class);
            if (Objects.nonNull(annotation)) {
                field.setAccessible(true);
                Object value = field.get(data);
                if (Objects.nonNull(value) && String.class.isAssignableFrom(field.getType())) {
                    String str = (String) value;
                    String newValue;
                    String prefix = annotation.prefix();
                    String suffix = annotation.suffix();
                    if (annotation.ignoreCase()) {
                        String prefixAdd = StrUtil.startWithIgnoreCase(str, prefix)
                                ? StrUtil.sub(str, 0, prefix.length()) : StrUtil.EMPTY;
                        String suffixAdd = StrUtil.endWithIgnoreCase(str, suffix)
                                ? StrUtil.sub(str, str.length() - suffix.length(), str.length()) : StrUtil.EMPTY;
                        String noPrefixValue = StrUtil.removePrefixIgnoreCase(str, prefix);
                        String realValue = StrUtil.removeSuffixIgnoreCase(noPrefixValue, suffix);
                        String convertValue = NumberUtil.thousandSymbol(realValue);
                        newValue = prefixAdd + convertValue + suffixAdd;
                    } else {
                        String noPrefixValue = StrUtil.removePrefix(str, prefix);
                        String prefixAdd = str.length() == noPrefixValue.length() ? StrUtil.EMPTY : prefix;
                        String realValue = StrUtil.removeSuffix(noPrefixValue, suffix);
                        String suffixAdd = noPrefixValue.length() == realValue.length() + suffix.length() ? suffix : StrUtil.EMPTY;
                        String convertValue = NumberUtil.thousandSymbol(realValue);
                        newValue = prefixAdd + convertValue + suffixAdd;
                    }
                    field.set(data, newValue);
                } else {
                    convertObj(value);
                }
            } else if (Page.class.isAssignableFrom(data.getClass()) && "records".equals(field.getName())) {
                field.setAccessible(true);
                Object records = field.get(data);
                convertObj(records);
            }
        }
    }
}
