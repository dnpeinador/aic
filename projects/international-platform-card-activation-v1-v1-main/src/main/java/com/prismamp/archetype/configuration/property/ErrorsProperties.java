package com.prismamp.archetype.configuration.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "errors")
public class ErrorsProperties {
  private boolean allowRetroCompatibility;

  public boolean isAllowRetroCompatibility() {
    return allowRetroCompatibility;
  }

  public void setAllowRetroCompatibility(boolean allowRetroCompatibility) {
    this.allowRetroCompatibility = allowRetroCompatibility;
  }
}
