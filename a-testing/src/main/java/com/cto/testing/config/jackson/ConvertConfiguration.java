package com.cto.testing.config.jackson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.cto.testing.config.i18n.TimeZoneContext;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sun.istack.internal.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
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
     * 重写object 添加时间序列化
     *
     * @return ObjectMapper
     * @since 2023/9/28
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = getJavaTimeModule();
        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }
    
    /**
     * Local时间序列化
     *
     * @return JavaTimeModule
     * @since 2023/9/28
     */
    @NotNull
    private static JavaTimeModule getJavaTimeModule() {
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
        //取消时间的转化格式，默认是时间戳,同时需要设置要表现的时间格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        //前端多传参数导致报错
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //设置返回的NULL为空字符串
        objectMapper.setSerializerFactory(objectMapper.getSerializerFactory().withSerializerModifier(new BeanSerializerModifier() {
            @Override
            public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
                    List<BeanPropertyWriter> beanProperties) {
                //循环所有的beanPropertyWriter
                for (Object beanProperty : beanProperties) {
                    BeanPropertyWriter writer = (BeanPropertyWriter) beanProperty;
                    //判断字段的类型，如果是array，list，set则注册nullSerializer
                    if (isArrayType(writer)) {
                        //给writer注册一个自己的nullSerializer
                        writer.assignNullSerializer(arrayNullSerializer());
                    } else if (isBooleanType(writer)) {
                        writer.assignNullSerializer(booleanNullSerializer());
                    } else if (isMapType(writer)) {
                        writer.assignNullSerializer(objectNullSerializer());
                    } else if (isObjectType(writer)) {
                        writer.assignNullSerializer(objectNullSerializer());
                    } else {
                        writer.assignNullSerializer(stringNullSerializer());
                    }
                }
                return super.changeProperties(config, beanDesc, beanProperties);
            }
        }));
        JavaTimeModule javaTimeModule = getJavaTimeModule();
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
        converters.add(0, mappingJackson2HttpMessageConverter);
        //清除时区Context
        TimeZoneContext.remove();
    }
    
    /**
     * 是否是数组
     */
    private boolean isArrayType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return clazz.isArray() || Collection.class.isAssignableFrom(clazz);
    }
    
    /**
     * 是否是string
     */
    private boolean isStringType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return CharSequence.class.isAssignableFrom(clazz) || Character.class.isAssignableFrom(clazz);
    }
    
    
    /**
     * 是否是数字
     */
    private boolean isNumberType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return Number.class.isAssignableFrom(clazz);
    }
    
    /**
     * 是否是boolean
     */
    private boolean isBooleanType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return clazz.equals(Boolean.class);
    }
    
    /**
     * 是否是Map
     */
    private boolean isMapType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return clazz.equals(Map.class);
    }
    
    /**
     * 是否是Object
     */
    private boolean isObjectType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        boolean property = BeanUtils.isSimpleProperty(clazz);
        return !property;
    }
    
    /**
     * String序列化转换，将NULL替换成空字符串''
     */
    @NotNull
    private static JsonSerializer<Object> stringNullSerializer() {
        return new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString("");
            }
        };
    }
    
    /**
     * Map序列化转换，将NULL替换成空字符串''
     */
    @NotNull
    private static JsonSerializer<Object> objectNullSerializer() {
        return new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                if (value == null) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    ObjectNode objectNode = objectMapper.createObjectNode();
                    gen.writeObject(objectNode);
                }
            }
        };
    }
    
    /**
     * Array序列化转换，将NULL替换成空字符串''
     */
    @NotNull
    private static JsonSerializer<Object> arrayNullSerializer() {
        return new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                if (value == null) {
                    gen.writeStartArray();
                    gen.writeEndArray();
                }
            }
        };
    }
    
    
    /**
     * Boolean序列化转换，将NULL替换成空字符串''
     */
    @NotNull
    private static JsonSerializer<Object> booleanNullSerializer() {
        return new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeBoolean(false);
            }
        };
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
