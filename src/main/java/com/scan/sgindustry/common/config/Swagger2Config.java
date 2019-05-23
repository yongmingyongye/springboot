package com.scan.sgindustry.common.config;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger2的简单配置类
 * @author fx
 * @date 2019/05/17
 *
 */
public class Swagger2Config {
	
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
					.apiInfo(apiInfo())
					.pathMapping("/")
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.scan.sgindustry.controller"))
					.paths(PathSelectors.regex("/.*")) // 对根下所有路径进行监控
					.build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
						.title("利用swagger2构建api文档")
						.description("简单优雅的restfun风格，https://blog.csdn.net/yongmingyongye")
						.termsOfServiceUrl("https://blog.csdn.net/yongmingyongye")
						.version("1.0")
						.contact(new Contact("F.X", "https://blog.csdn.net/yongmingyongye", "1028353148@qq.com"))
						.build();
	}

}
