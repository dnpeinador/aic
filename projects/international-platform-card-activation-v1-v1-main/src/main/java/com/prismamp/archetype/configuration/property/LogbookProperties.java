package com.prismamp.archetype.configuration.property;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("logbook.obfuscate")
@Getter
@Setter
public class LogbookProperties {

  private List<String> headers;

  private List<String> parameters;

  private List<String> jsonBodyFields;
}
