package com.cto.common.config.swagger;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport; /**
 * @author ZhangYongWei
 * @since 2024/5/17
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //处理静态资源无法访问
        //registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        //处理Swagger无法访问
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        //处理Swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}