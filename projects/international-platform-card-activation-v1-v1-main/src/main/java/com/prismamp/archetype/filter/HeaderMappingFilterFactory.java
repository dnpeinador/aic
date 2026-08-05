package com.prismamp.archetype.filter;

import com.prismamp.archetype.configuration.HeaderPropagatorConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public final class HeaderMappingFilterFactory
    extends AbstractGatewayFilterFactory<HeaderMappingFilterFactory.Config> {

  private static final String API_NAME_CUSTOM_HEADER = "x-apip-api-name";

  private final HeaderPropagatorConfig headerPropagatorConfig;

  @Autowired
  public HeaderMappingFilterFactory(HeaderPropagatorConfig headerPropagatorConfig) {
    this.headerPropagatorConfig = headerPropagatorConfig;
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) ->
        chain
            .filter(exchange)
            .then(
                Mono.fromRunnable(
                    () -> {
                      ServerHttpRequest request = exchange.getRequest();
                      ServerHttpResponse response = exchange.getResponse();
                      setHeaders(request, response);
                      exchange.mutate().response(response).build();
                    }));
  }

  private void setHeaders(ServerHttpRequest request, ServerHttpResponse response) {
    var headers = response.getHeaders();
    try {
      headers.add(API_NAME_CUSTOM_HEADER, this.headerPropagatorConfig.getName());

      this.headerPropagatorConfig
          .getHeaders()
          .forEach(
              header -> {
                headers.add(header, request.getHeaders().getFirst(header));
              });
    } catch (UnsupportedOperationException e) {
      log.info("Headers can't be propagated because HttpHeaders is read only");
    }
  }

  public static class Config {}
}
