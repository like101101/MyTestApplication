package com.example.mytestapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket SwaggerConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.paths(PathSelectors.ant("/student/*/*"))
                .apis(RequestHandlerSelectors.basePackage("com.example.mytestapp"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "MyTestApplication API",
                "Simple API tryout",
                "1.0",
                "Free use",
                new springfox.documentation.service.Contact(
                        "Ke Li",
                        "https://github.com/like101101",
                        "likelike101101@gmail.com"),
                "No License",
                "Just for fun",
                Collections.emptyList()
        );
    }
}
