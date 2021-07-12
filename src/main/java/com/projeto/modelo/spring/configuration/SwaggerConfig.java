package com.projeto.modelo.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.projeto.modelo.spring.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessageForGET())
                .apiInfo(apiInfo());
    }

    private List<ResponseMessage> responseMessageForGET()
    {
        var listResponse = new ArrayList<ResponseMessage>();
        listResponse.add(new ResponseMessageBuilder()
                .code(500)
                .message("500 message")
                .build());
        listResponse.add(new ResponseMessageBuilder()
                .code(403)
                .message("Forbidden!")
                .build());
        return listResponse;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API Rest + Spring + RabbitMQ + MongoDB")
                .description("Um exemplo de aplicação Spring Boot REST API usando o banco de dados Mongo e fila RabbitMQ")
                .version("1.0.0")
                        .contact(new Contact("Leonardo Mendes dos Santos", "", "l.d.dos.santos@avanade.com"))
                        .build();
    }
}
