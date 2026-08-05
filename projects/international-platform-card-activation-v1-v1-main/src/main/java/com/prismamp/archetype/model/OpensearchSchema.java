package com.prismamp.archetype.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(toBuilder = true)
@Getter
@Setter
public class OpensearchSchema {

  @JsonProperty("@timestamp")
  private Instant timestamp;

  private String application;

  private String applicationVersion;

  private String path;

  private String url;

  private String type;

  private String method;

  private String query;

  private String body;

  private String realIp;

  private HeaderSchema headers;

  private List<String> cookies;

  private Integer requestDuration;

  private Integer statusCode;

  private String response;
}
