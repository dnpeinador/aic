package com.prismamp.archetype.configuration.property;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("access-validation")
public class AccessValidationProperties {

  private String hostUrl;

  private String validationPath;

  private String livenessPath;

  private Exclude exclude;

  public String getHostUrl() {
    return hostUrl;
  }

  public void setHostUrl(String hostUrl) {
    this.hostUrl = hostUrl;
  }

  public String getValidationPath() {
    return validationPath;
  }

  public void setValidationPath(String validationPath) {
    this.validationPath = validationPath;
  }

  public String getLivenessPath() {
    return livenessPath;
  }

  public void setLivenessPath(String livenessPath) {
    this.livenessPath = livenessPath;
  }

  public Exclude getExclude() {
    return exclude;
  }

  public void setExclude(Exclude exclude) {
    this.exclude = exclude;
  }

  public static class Exclude {
    private List<String> paths;

    public List<String> getPaths() {
      return paths;
    }

    public void setPaths(List<String> paths) {
      this.paths = paths;
    }
  }
}
