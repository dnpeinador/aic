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
  "duration",
  "status",
  "body",
  "protocol",
  "headers"
})
@Getter
@Setter
public class HttpResponseSchema {

  private String origin;

  private String type;

  private String correlation;

  private Integer duration;

  private String protocol;

  private Integer status;

  private Map<String, List<String>> headers;

  private JsonNode body;
}
