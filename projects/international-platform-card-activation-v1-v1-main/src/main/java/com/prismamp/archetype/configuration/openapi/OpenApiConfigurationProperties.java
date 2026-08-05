package com.prismamp.archetype.configuration.openapi;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "documentation.openapi.info")
public class OpenApiConfigurationProperties {
  private String prefix;
  private String version;
  private String title;
  private String description;
  private ContactProperties contact = new ContactProperties();

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ContactProperties getContact() {
    return contact;
  }

  public void setContact(ContactProperties contact) {
    this.contact = contact;
  }

  public class ContactProperties {
    private String email;
    private String name;

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}
