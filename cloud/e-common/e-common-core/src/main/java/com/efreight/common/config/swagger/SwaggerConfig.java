package com.efreight.common.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

/**
 * Swagger配置
 *
 * @author 阿沐  babamu@126.com
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public GlobalOpenApiCustomizer orderGlobalOpenApiCustomizer() {
        return openApi -> {
            // 全局添加鉴权参数
            if (openApi.getPaths() != null) {
                openApi.getPaths().forEach((s, pathItem) -> {
                    pathItem.readOperations().forEach(operation -> {
                        operation.addSecurityItem(new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION));
                    });
                });
            }
            
        };
    }
    
    @Bean
    public OpenAPI openApi() {
        Contact contact = new Contact();
        contact.setName("张永伟<zhangyongwei@efreight.cn>");
        
        return new OpenAPI().info(new Info()
                                          .title("API接口文档")
                                          .description("部分简单返回的接口，返回字段没写备注，可以调试看一下请求结果，或者问后端开发人员."
                                                               + "<br/> 需要登录验证的接口，统一在header中传入Authorization,Authorization值在登录注册时候又返回"
                                                               + "<br/> 国际化支持，暂支持中文和英文，处理方式在header中携带lang参数或者将lang参数拼接在请求后面"
                                                               + "<br/> 系统支持根据时区自定义时间转换，处理方式在Header中携带timeZone参数，具体值获取方式如下："
                                                               + "<br/> JavaScript方式：Intl.DateTimeFormat().resolvedOptions().timeZone;")
                                          .contact(contact)
                                          .version("2.0-RELEASE")
                
                ).addSecurityItem(new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION))
                .components(new Components().addSecuritySchemes(HttpHeaders.AUTHORIZATION, new SecurityScheme()
                        .name(HttpHeaders.AUTHORIZATION).type(SecurityScheme.Type.HTTP)));
    }
    
}