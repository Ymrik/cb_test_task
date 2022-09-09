package com.umarbariev.projects.cb_demo_task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket configSwagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/**"))
                .apis(RequestHandlerSelectors.basePackage("com.umarbariev.projects.cb_demo_task"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Insurance companies API",
                "API for CRUD operation with insurance companies from database",
                "1.0",
                "Free to use",
                new Contact("Umar Bariev", "https://github.com/Ymrik", "ubariev@mail.ru"),
                "API License",
                "https://github.com/Ymrik",
                Collections.emptyList());
    }
}
