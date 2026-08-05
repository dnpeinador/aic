package com.prismamp.archetype.model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@JsonIncludeProperties({
  "origin",
  "type",
  "correlation",
  "protocol",
  "method",
  "uri",
  "host",
  "path",
  "scheme",
  "port",
  "body",
  "headers",
  "remote"
})
@Getter
@Setter
public class HttpRequestSchema {

  private String origin;

  private String type;

  private String correlation;

  private String protocol;

  private String method;

  private String uri;

  private String host;

  private String path;

  private String scheme;

  private Integer port;

  private JsonNode body;

  private String remote;

  private Map<String, List<String>> headers;
}
