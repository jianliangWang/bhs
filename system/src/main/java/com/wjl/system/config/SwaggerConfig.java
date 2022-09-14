package com.wjl.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createResApi(){
        return new Docket(DocumentationType.OAS_30).select().apis(RequestHandlerSelectors.basePackage("com.wjl.system"))
            .paths(PathSelectors.any()).build();
    }
}
