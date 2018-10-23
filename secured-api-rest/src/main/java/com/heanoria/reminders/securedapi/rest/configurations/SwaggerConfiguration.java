package com.heanoria.reminders.securedapi.rest.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

import java.util.function.Predicate;

@Configuration
@EnableSwagger2WebFlux
public class SwaggerConfiguration {

    private final SwaggerProperties swagger;

    public SwaggerConfiguration(SwaggerProperties swagger) {
        this.swagger = swagger;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(not(PathSelectors.regex("/actuator.*")))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swagger.getTitle())
                .description(swagger.getDescription())
                .termsOfServiceUrl(swagger.getTermsOfServiceUrl())
                .contact(new Contact(swagger.getContact(), "", ""))
                .license(swagger.getLicense())
                .licenseUrl(swagger.getLicenseUrl())
                .version(swagger.getVersion())
                .build();
    }

    private static <T> Predicate<T> not(Predicate<T> input) {
        return it -> !input.test(it);
    }

}
