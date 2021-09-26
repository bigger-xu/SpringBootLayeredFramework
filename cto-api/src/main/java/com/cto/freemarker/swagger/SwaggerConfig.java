package com.cto.freemarker.swagger;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bigger-Xu
 * @version v1.0.1
 * @date 2021/3/15 22:01
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig {
	@Bean
	public Docket createUser() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("net.dpt.toctou.app.controller.app"))
				//.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				.paths(PathSelectors.any())
				.build().groupName("APP接口API");
	}

	@Bean
	public Docket createAdmin() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("net.dpt.toctou.app.controller.manage"))
				//.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				.paths(PathSelectors.any())
				.build().groupName("后台管理接口API");
	}

	@Bean
	public Docket createRole() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("net.dpt.toctou.app.controller.web"))
				//.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				.paths(PathSelectors.any())
				.build().groupName("官网相关接口API");
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("TocTou 接口文档API")
				.description("部分简单返回的接口，返回字段没写备注，可以点击调试看一下请求结果，或者问后端开发人员."
						+ "<br/>国际化请求方式：每次请求需要带参数?lang=zh_CN或?lang=en_US（例如：http://test.test.com/user/login?lang=zh_CN）"
						+ "<br/> 需要登录验证的接口，统一在header中传入token,token值在登录注册时候又返回")
				.contact(new Contact("张永伟", "", ""))
				.version("v1.0")
				.build();
	}
}