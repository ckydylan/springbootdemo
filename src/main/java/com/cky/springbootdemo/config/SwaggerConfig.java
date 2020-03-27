package com.cky.springbootdemo.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: cky
 * @Date: 2020/3/26 10:09
 * @Description:
 */
@Data
@Configuration
@EnableSwagger2 // 启用 Swagger
//@EnableWebMvc
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.cky.springbootdemo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 构建 api 文档的详细信息函数
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title("SSM Application [cky] Swagger2 RESTFul API")
                // 创建人
                .contact(new Contact("x-sir", "http://www.cky666.top", "1205428733@qq.com"))
                // 版本号
                .version("1.0")
                // 描述
                .description("This is [cky] API doc")
                .build();
    }
}