package com.prismamp.archetype.configuration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prismamp.archetype.commons.constant.ProblemType;
import com.prismamp.archetype.configuration.property.ErrorsProperties;
import com.prismamp.archetype.exception.GenericException;
import com.prismamp.archetype.filter.AccessValidationFilterFactory;
import com.prismamp.archetype.filter.HeaderMappingFilterFactory;
import java.util.Arrays;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class RouteLocatorConfig {
  @Value("${server.servlet.context-path}")
  private String basePath;

  @Value("${api-core.host}")
  private String apiCoreHost;

  @Value("${api-core.path}")
  private String apiCorePath;

  @Autowired private ErrorsProperties errorsConfig;

  @Bean
  public RouteLocator customRouteLocator(
      RouteLocatorBuilder builder,
      HeaderMappingFilterFactory headerMappingFilterFactory,
      AccessValidationFilterFactory accessValidationFilterFactory) {
    return builder
        .routes()
        .route(
            "default-router",
            predicateSpec ->
                predicateSpec
                    .path(basePath + "/**")
                    .filters(
                        spec ->
                            spec.rewritePath(
                                    basePath + "/(?<segment>/?.*)", apiCorePath + "/$\\{segment}")
                                .filter(
                                    headerMappingFilterFactory.apply(
                                        new HeaderMappingFilterFactory.Config()))
                                .filter(
                                    accessValidationFilterFactory.apply(
                                        new AccessValidationFilterFactory.Config()))
                                .modifyResponseBody(
                                    String.class, String.class, getStringStringRewriteFunction())
                                .removeRequestHeader("Accept-Encoding"))
                    .uri(apiCoreHost))
        .build();
  }

  private RewriteFunction<String, String> getStringStringRewriteFunction() {
    return (exchange, body) -> {
      ServerHttpResponse response = exchange.getResponse();
      HttpStatusCode httpStatus = response.getStatusCode();

      if (errorsConfig.isAllowRetroCompatibility() && httpStatus.isError()) {
        try {
          ObjectMapper objectMapper = new ObjectMapper();
          JsonNode rootNode = objectMapper.readTree(body);
          if (rootNode.has("errors") && rootNode.get("errors").isArray()) {
            JsonNode errorsNode = rootNode.get("errors").get(0);
            if (errorsNode.has("message")) {
              String errorMessage = errorsNode.get("message").asText();
              Optional<ProblemType> problemTypeOp =
                  Arrays.stream(ProblemType.values())
                      .filter(item -> item.getStatus() == httpStatus)
                      .findFirst();
              Mono mono =
                  problemTypeOp
                      .map(
                          problemType ->
                              Mono.error(new GenericException(problemType, errorMessage)))
                      .orElse(Mono.just(body));
              return mono;
            }
          } else {
            log.info("Invalid error response structure: 'errors' field not found or not an array.");
          }
        } catch (Exception e) {
          log.error("Error to parse body message");
        }
      }

      return Mono.just(body);
    };
  }
}
