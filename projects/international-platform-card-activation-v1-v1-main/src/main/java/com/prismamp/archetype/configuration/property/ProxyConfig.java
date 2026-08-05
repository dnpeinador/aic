package com.prismamp.archetype.configuration.property;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties("proxy")
@Getter
@Setter
@Validated
public class ProxyConfig {

  @NotBlank private String name;

  @NotBlank private String version;
}
