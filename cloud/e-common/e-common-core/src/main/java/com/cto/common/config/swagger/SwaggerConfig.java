package com.cto.common.config.swagger;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger配置信息
 *
 * @author 张永伟
 * @since 2023/4/27
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createUser() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).select()
                //.apis(RequestHandlerSelectors.basePackage("com.cto.sc.controller"))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(
                        PathSelectors.any()).build().globalRequestParameters(getGlobalRequestParameters()).groupName("接口列表");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("接口文档API").description(
                "部分简单返回的接口，返回字段没写备注，可以调试看一下请求结果，或者问后端开发人员." + "<br/> 需要登录验证的接口，统一在header中传入Authorization,Authorization值在登录注册时候又返回" + "<br/> 国际化支持，暂支持中文和英文，处理方式在header中携带lang参数或者将lang参数拼接在请求后面" + "<br/> 系统支持根据时区自定义时间转换，处理方式在Header中携带timeZone参数，具体值获取方式如下：" + "<br/> JavaScript方式：Intl.DateTimeFormat().resolvedOptions().timeZone;").contact(
                new Contact("张永伟", "", "zhangyongwei@cto.cn")).version("e-Cargo 2.0").build();
    }

    //生成全局通用参数
    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder().name("Authorization").description("登录校验token").required(true).in(
                ParameterType.HEADER).query(q -> q.model(m -> m.scalarModel(ScalarType.STRING))).build());
        return parameters;
    }
}
