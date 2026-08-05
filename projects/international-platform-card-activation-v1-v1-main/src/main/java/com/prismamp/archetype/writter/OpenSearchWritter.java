package com.prismamp.archetype.writter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prismamp.archetype.configuration.opensearch.ResponseOpensearchRepository;
import com.prismamp.archetype.configuration.property.ApplicationProperties;
import com.prismamp.archetype.model.HeaderSchema;
import com.prismamp.archetype.model.HttpRequestSchema;
import com.prismamp.archetype.model.HttpResponseSchema;
import com.prismamp.archetype.model.OpensearchSchema;
import com.prismamp.archetype.utils.StringUtils;
import java.io.IOException;
import org.zalando.logbook.Correlation;
import org.zalando.logbook.HttpLogWriter;
import org.zalando.logbook.Precorrelation;

public class OpenSearchWritter implements HttpLogWriter {

  private final ResponseOpensearchRepository repository;

  private ApplicationProperties applicationProperties;

  private OpensearchSchema.OpensearchSchemaBuilder opensearchSchemaBuilder;

  private HeaderSchema headerSchema;

  public OpenSearchWritter(
      ResponseOpensearchRepository repository, ApplicationProperties applicationProperties) {
    this.repository = repository;
    this.applicationProperties = applicationProperties;
    headerSchema = new HeaderSchema();
  }

  @Override
  public void write(Precorrelation precorrelation, String request) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    HttpRequestSchema httpRequestSchema = objectMapper.readValue(request, HttpRequestSchema.class);

    OpensearchSchema opensearchSchema =
        OpensearchSchema.builder()
            .timestamp(precorrelation.getStart())
            .application(applicationProperties.getName())
            .applicationVersion(applicationProperties.getVersion())
            .path(httpRequestSchema.getPath())
            .url(httpRequestSchema.getUri())
            .type(httpRequestSchema.getScheme())
            .method(httpRequestSchema.getMethod())
            .query(StringUtils.getQuery(httpRequestSchema.getUri()))
            .body(String.valueOf(httpRequestSchema.getBody()))
            .cookies(null)
            .realIp(StringUtils.getIp(httpRequestSchema.getRemote()))
            .build();

    opensearchSchemaBuilder = opensearchSchema.toBuilder();
    headerSchema.setRequest(httpRequestSchema.getHeaders().toString());
  }

  @Override
  public void write(Correlation correlation, String response) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    JsonNode jsonNode = objectMapper.readTree(response);

    HttpResponseSchema responseJson = objectMapper.readValue(response, HttpResponseSchema.class);

    headerSchema.setResponse(responseJson.getHeaders().toString());
    opensearchSchemaBuilder
        .headers(headerSchema)
        .requestDuration(responseJson.getDuration())
        .statusCode(responseJson.getStatus())
        .response(String.valueOf(responseJson.getBody()));

    String id = String.format("%s-%s", jsonNode.get("type").asText(), correlation.getId());

    repository.bulkTransactions(opensearchSchemaBuilder.build(), id);
  }
}
