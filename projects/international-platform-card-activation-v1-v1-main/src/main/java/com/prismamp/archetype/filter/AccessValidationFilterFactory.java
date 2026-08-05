package com.prismamp.archetype.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prismamp.archetype.configuration.property.AccessValidationProperties;
import com.prismamp.archetype.configuration.property.ProxyConfig;
import com.prismamp.archetype.exception.UnAuthorizedException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Slf4j
@Component
public final class AccessValidationFilterFactory
    extends AbstractGatewayFilterFactory<AccessValidationFilterFactory.Config> {

  public static final String IDENTIFIER = "bank_code";

  public static final String CUIT_OWNER = "cuit_owner";
  public static final String X_APIP_CUIT = "x-apip-cuit";

  @Autowired private ProxyConfig proxyConfig;

  @Autowired private AccessValidationProperties accessValidationConfig;

  private final WebClient webClient;

  public AccessValidationFilterFactory(WebClient.Builder webClientBuilder) {
    this.webClient =
        webClientBuilder
            .build()
            .mutate()
            .clientConnector(
                new ReactorClientHttpConnector(HttpClient.newConnection().followRedirect(true)))
            .build();
  }

  @Override
  public GatewayFilter apply(AccessValidationFilterFactory.Config config) {
    return (exchange, chain) -> {
      String path = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_PREDICATE_PATH_CONTAINER_ATTR).toString();
      if (isExcludedPath(path)) {
        log.info(
                "Skiping Access Validation for path: {}", exchange.getRequest().getURI().getPath());
        return chain.filter(exchange);
      }
      log.info("Start proxy filter owner:");
      log.info("Start proxy filter - Path:" + path.toString() + "  Cuit:" + exchange.getRequest().getHeaders().getFirst(CUIT_OWNER));
      String cuitApplicant = getValueFromHeaderFirst(exchange, X_APIP_CUIT);
      String apiName = proxyConfig.getName();
      String apiVersion = proxyConfig.getVersion();
      String method = exchange.getRequest().getMethod().name();

      Mono<String> identifierMono =
          Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(IDENTIFIER))
              .switchIfEmpty(
                  Mono.defer(
                      () ->
                          Mono.justOrEmpty(
                              exchange.getRequest().getQueryParams().getFirst(IDENTIFIER))))
              .switchIfEmpty(Mono.defer(() -> getPropertyFromBody(exchange, IDENTIFIER)))
              .switchIfEmpty(Mono.just(""));

      Mono<String> cuitOwnerMono =
          Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(CUIT_OWNER))
              .switchIfEmpty(
                  Mono.defer(
                      () ->
                          Mono.justOrEmpty(
                              exchange.getRequest().getQueryParams().getFirst(CUIT_OWNER))))
              .switchIfEmpty(Mono.defer(() -> getPropertyFromBody(exchange, CUIT_OWNER)))
              .switchIfEmpty(Mono.just(""));

      return identifierMono.flatMap(
          identifier ->
              cuitOwnerMono.flatMap(
                  cuitOwner -> {
                    String uri =
                        UriComponentsBuilder.fromHttpUrl(
                                accessValidationConfig.getHostUrl()
                                    + accessValidationConfig.getValidationPath())
                            .queryParam(
                                "cuit_applicant", cuitApplicant)
                            .queryParam(
                                "api_name", apiName)
                            .queryParam(
                                "api_version", apiVersion)
                            .queryParam(
                                "http_verb", method)
                            .queryParam(
                                "api_resource", path)
                            .queryParam(
                                "identifier", identifier)
                            .queryParam(
                                "cuit_owner", cuitOwner)
                            .build(true)
                            .toUriString();

                    return webClient
                        .get()
                        .uri(uri)
                        .retrieve()
                        .toEntity(String.class)
                        .flatMap(
                            response -> {
                              return chain.filter(exchange);
                            })
                        .onErrorResume(
                            throwable -> {
                              return Mono.error(
                                  new UnAuthorizedException("Access Validation Error"));
                            });
                  }));
    };
  }

  private String getValueFromHeaderFirst(ServerWebExchange exchange, String header) {
    String value = exchange.getRequest().getHeaders().getFirst(header);
    return value != null ? value : "";
  }

  private boolean isExcludedPath(String path) {
    return accessValidationConfig.getExclude().getPaths().stream().anyMatch(path::contains);
  }

    private Mono<String> getPropertyFromBody(ServerWebExchange exchange, String propertyName) {
        CacheRequestBodyFilter cacheRequestBodyFilter = new CacheRequestBodyFilter();
        ServerWebExchange cachedExchange = cacheRequestBodyFilter.apply(exchange);

        return DataBufferUtils.join(cachedExchange.getRequest().getBody())
                .map(dataBuffer -> {
                    String bodyAsString = dataBuffer.toString(StandardCharsets.UTF_8);
                    DataBufferUtils.release(dataBuffer);
                    return bodyAsString;
                })
                .flatMap(body -> {
                    if (body != null) {
                        try {
                            ObjectMapper objectMapper = new ObjectMapper();
                            JsonNode rootNode = objectMapper.readTree(body);

                            if (rootNode.has(propertyName)) {
                                return Mono.just(rootNode.get(propertyName).asText());
                            }
                        } catch (Exception e) {
                            log.error("Error converting to JSON", e);
                            return Mono.empty();
                        }
                    }
                    return Mono.empty();
                });
    }

  public static class Config {}
}
