package com.prismamp.archetype.configuration.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

  @Autowired OpenApiConfigurationProperties openApiConfigurationProperties;

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title(openApiConfigurationProperties.getTitle())
                .description(openApiConfigurationProperties.getDescription())
                .version(openApiConfigurationProperties.getVersion())
                .termsOfService("http://swagger.io/terms/")
                .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                .contact(infoContact()));
  }

  private Contact infoContact() {
    return new Contact()
        .email(openApiConfigurationProperties.getContact().getEmail())
        .name(openApiConfigurationProperties.getContact().getName());
  }
}
