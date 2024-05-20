package com.cto.common.config.jackson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimeZone;

import com.cto.common.i18n.TimeZoneContext;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import org.springframework.beans.factory.annotation.Autowired;
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

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired(required = false)
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    /**
     * 时区转换
     *
     * @param localDateTime 转换的时间
     * @param originZoneId  源时间
     * @param targetZoneId  目标时间
     */
    public static LocalDateTime convertLocalDateTime(LocalDateTime localDateTime, ZoneId originZoneId, ZoneId targetZoneId) {
        return localDateTime.atZone(originZoneId).withZoneSameInstant(targetZoneId).toLocalDateTime();
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper objectMapper = new ObjectMapper();
        //空对象不要抛异常
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        //取消时间的转化格式，默认是时间戳,同时需要设置要表现的时间格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        JavaTimeModule javaTimeModule = new JavaTimeModule();   // 默认序列化没有实现，反序列化有实现
        javaTimeModule.addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer(DATE_TIME_FORMATTER));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DATE_FORMATTER));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(TIME_FORMATTER));
        javaTimeModule.addDeserializer(LocalDateTime.class, new CustomLocalDateTimeDeserializer(DATE_TIME_FORMATTER));
        objectMapper.registerModule(javaTimeModule);
        // 设置时区
        objectMapper.setTimeZone(TimeZone.getDefault());
        if (mappingJackson2HttpMessageConverter == null) {
            mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        }
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);
        converters.add(0, mappingJackson2HttpMessageConverter);
        //清除时区Context
        TimeZoneContext.remove();
    }

    /**
     * LocalDateTime序列化
     */
    public static class CustomLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        private DateTimeFormatter formatter;

        public CustomLocalDateTimeSerializer() {
        }

        public CustomLocalDateTimeSerializer(DateTimeFormatter formatter) {
            super();
            this.formatter = formatter;
        }

        @Override
        public void serialize(LocalDateTime value, JsonGenerator generator, SerializerProvider provider) throws IOException {
            String zoneId;
            if (TimeZoneContext.getContext() != null) {
                zoneId = TimeZoneContext.getContext().getID();
            } else {
                zoneId = ZoneId.systemDefault().getId();
            }
            generator.writeString(convertLocalDateTime(value, ZoneId.systemDefault(), ZoneId.of(zoneId)).format(formatter));
        }
    }

    /**
     * LocalDateTime反序列化
     */
    public static class CustomLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
        private DateTimeFormatter formatter;

        public CustomLocalDateTimeDeserializer() {
        }

        public CustomLocalDateTimeDeserializer(DateTimeFormatter formatter) {
            super();
            this.formatter = formatter;
        }

        @Override
        public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
            String zoneId;
            if (TimeZoneContext.getContext() != null) {
                zoneId = TimeZoneContext.getContext().getID();
            } else {
                zoneId = ZoneId.systemDefault().getId();
            }
            return convertLocalDateTime(LocalDateTime.parse(parser.getText(), formatter), ZoneId.of(zoneId), ZoneId.systemDefault());
        }
    }
}
