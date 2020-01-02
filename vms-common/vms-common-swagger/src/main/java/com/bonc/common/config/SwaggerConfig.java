package com.bonc.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Title: vms
 * @Package: com.bonc.common.config
 * @Description: swagger文档配置类
 * @Author: dreamcc
 * @Date: 2020/1/2 10:30
 * @Version: V1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.pathMapping("/")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.bonc"))
				.paths(PathSelectors.any())
				.build().apiInfo(new ApiInfoBuilder()
						.title("vms视频云平台")
						.description("vms视频云平台接口文档")
						.version("1.0")
						.contact(new Contact("vms","www.bonc.com.cn","xxxxxx@gmail.com"))
						.license("The Apache License")
						.build());
	}
}
