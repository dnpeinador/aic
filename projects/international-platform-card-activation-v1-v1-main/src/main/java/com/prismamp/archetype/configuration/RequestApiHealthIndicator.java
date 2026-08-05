package com.prismamp.archetype.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class RequestApiHealthIndicator implements ReactiveHealthIndicator {

  @Value("${api-core.path}")
  private String apiCorePath;

  @Value("${api-core.liveness-path}")
  private String apiCoreLivenessPath;

  private final WebClient webClient;

  @Autowired
  public RequestApiHealthIndicator(
      WebClient.Builder webClientBuilder, @Value("${api-core.host}") String apiCoreHost) {
    this.webClient = webClientBuilder.baseUrl(apiCoreHost).build();
  }

  @Override
  public Mono<Health> health() {
    return webClient
        .get()
        .uri(apiCoreLivenessPath)
        .exchangeToMono(
            res -> {
              if (res.statusCode().is2xxSuccessful()) {
                return Mono.just(new Health.Builder().up().build());
              } else {
                return Mono.just(new Health.Builder().down().build());
              }
            });
  }
}
