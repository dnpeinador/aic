package com.prismamp.archetype.configuration;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("header-propagator")
public class HeaderPropagatorConfig {

  private String name;

  private List<String> headers = new ArrayList<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getHeaders() {
    return headers;
  }

  public void setHeaders(List<String> headers) {
    this.headers = headers;
  }
}
