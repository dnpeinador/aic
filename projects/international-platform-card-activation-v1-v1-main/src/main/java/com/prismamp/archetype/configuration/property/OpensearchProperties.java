package com.prismamp.archetype.configuration.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("opensearch")
@Getter
@Setter
public class OpensearchProperties {

  private Boolean enabled;
  private String index;
  private String failureIndex;
  private String user;
  private String password;
  private String host;
  private String schema;
  private int port;
  private int connectTimeout;
  private int socketTimeout;
}
