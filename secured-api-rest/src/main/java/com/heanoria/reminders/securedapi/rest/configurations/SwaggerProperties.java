package com.heanoria.reminders.securedapi.rest.configurations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    private String title;
    private String description;
    private String termsOfServiceUrl;
    private String contact;
    private String license;
    private String licenseUrl;
    private String version;

}
