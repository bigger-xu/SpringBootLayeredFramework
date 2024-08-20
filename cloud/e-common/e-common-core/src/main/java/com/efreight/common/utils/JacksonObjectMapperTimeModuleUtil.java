package com.efreight.common.utils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.efreight.common.i18n.TimeZoneContext;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

/**
 * Local时间序列化工具类
 *
 * @author 张永伟
 * @since 2023/10/7
 */
public class JacksonObjectMapperTimeModuleUtil {
    
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    /**
     * 获取ObjectMapper
     *
     * @return ObjectMapper
     * @since 2023/10/7
     */
    public static ObjectMapper getDefaultObjectMapper() {
        return new ObjectMapper();
    }
    
    /**
     * 获取处理java8时间日期的ObjectMapper
     *
     * @return ObjectMapper
     * @since 2023/10/7
     */
    public static ObjectMapper getJavaTimeModuleObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        //前端多传参数导致报错
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(getDefaultJavaTimeModule());
        return objectMapper;
    }
    
    /**
     * Local时间序列化，里面带有时区
     *
     * @return JavaTimeModule
     * @since 2023/10/7
     */
    public static JavaTimeModule getCustomJavaTimeModule() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        // 默认序列化没有实现，反序列化有实现 时间处理
        javaTimeModule.addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer(DATE_TIME_FORMATTER));
        javaTimeModule.addSerializer(LocalDate.class, new CustomLocalDateSerializer(DATE_FORMATTER));
        javaTimeModule.addSerializer(LocalTime.class, new CustomLocalTimeSerializer(TIME_FORMATTER));
        javaTimeModule.addDeserializer(LocalDateTime.class, new CustomLocalDateTimeDeserializer(DATE_TIME_FORMATTER));
        javaTimeModule.addDeserializer(LocalDate.class, new CustomLocalDateDeserializer(DATE_FORMATTER));
        javaTimeModule.addDeserializer(LocalTime.class, new CustomLocalTimeDeserializer(TIME_FORMATTER));
        return javaTimeModule;
    }
    
    /**
     * Local时间序列化，默认时间默认时区
     *
     * @return JavaTimeModule
     * @since 2023/10/7
     */
    public static JavaTimeModule getDefaultJavaTimeModule() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DATE_FORMATTER));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(TIME_FORMATTER));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_TIME_FORMATTER));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DATE_FORMATTER));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(TIME_FORMATTER));
        return javaTimeModule;
    }
    
    
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
    
    /**
     * LocalDateTime序列化
     */
    public static class CustomLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        private final DateTimeFormatter formatter;
        
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
        private final DateTimeFormatter formatter;
        
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
    
    /**
     * LocalDate序列化
     */
    public static class CustomLocalDateSerializer extends JsonSerializer<LocalDate> {
        private final DateTimeFormatter formatter;
        
        public CustomLocalDateSerializer(DateTimeFormatter formatter) {
            super();
            this.formatter = formatter;
        }
        
        @Override
        public void serialize(LocalDate value, JsonGenerator generator, SerializerProvider provider) throws IOException {
            generator.writeString(value.format(formatter));
        }
    }
    
    /**
     * LocalDate反序列化
     */
    public static class CustomLocalDateDeserializer extends JsonDeserializer<LocalDate> {
        private final DateTimeFormatter formatter;
        
        public CustomLocalDateDeserializer(DateTimeFormatter formatter) {
            super();
            this.formatter = formatter;
        }
        
        @Override
        public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
            return LocalDate.parse(parser.getText(), formatter);
        }
    }
    
    /**
     * LocalTime序列化
     */
    public static class CustomLocalTimeSerializer extends JsonSerializer<LocalTime> {
        private final DateTimeFormatter formatter;
        
        public CustomLocalTimeSerializer(DateTimeFormatter formatter) {
            super();
            this.formatter = formatter;
        }
        
        @Override
        public void serialize(LocalTime value, JsonGenerator generator, SerializerProvider provider) throws IOException {
            generator.writeString(value.format(formatter));
        }
    }
    
    /**
     * LocalTime反序列化
     */
    public static class CustomLocalTimeDeserializer extends JsonDeserializer<LocalTime> {
        private final DateTimeFormatter formatter;
        
        public CustomLocalTimeDeserializer(DateTimeFormatter formatter) {
            super();
            this.formatter = formatter;
        }
        
        @Override
        public LocalTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
            return LocalTime.parse(parser.getText(), formatter);
        }
    }
}
