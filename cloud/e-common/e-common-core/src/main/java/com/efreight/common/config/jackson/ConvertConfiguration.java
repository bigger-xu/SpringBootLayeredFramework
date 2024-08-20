package com.efreight.common.config.jackson;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import com.efreight.common.i18n.TimeZoneContext;
import com.efreight.common.utils.JacksonObjectMapperTimeModuleUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.Resource;
import org.jetbrains.annotations.NotNull;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 时间时区Jackson配置
 *
 * @author 张永伟
 * @since 2023/7/4
 */
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class ConvertConfiguration implements WebMvcConfigurer {
    
    @Resource
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Override
    public void extendMessageConverters(@NotNull List<HttpMessageConverter<?>> converters) {
        ObjectMapper objectMapper = new ObjectMapper();
        //取消时间的转化格式，默认是时间戳,同时需要设置要表现的时间格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        //前端多传参数导致报错
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //设置返回的NULL为空字符串
        JavaTimeModule javaTimeModule = JacksonObjectMapperTimeModuleUtil.getCustomJavaTimeModule();
        //Long转String  处理ID为Long类型前端丢失精度
        javaTimeModule.addSerializer(Long.class, new ToStringSerializer());
        objectMapper.registerModule(javaTimeModule);
        // 设置时区
        objectMapper.setTimeZone(TimeZone.getDefault());
        if (mappingJackson2HttpMessageConverter == null) {
            mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        }
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);
        converters.add(mappingJackson2HttpMessageConverter);
        //清除时区Context
        TimeZoneContext.remove();
    }
}
