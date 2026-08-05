package com.prismamp.archetype.configuration.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("spring.application")
@Getter
@Setter
public class ApplicationProperties {

  private String name;

  private String version;
}
