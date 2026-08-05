package com.prismamp.archetype;

import com.prismamp.archetype.configuration.RequestApiHealthIndicator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

class RequestApiHealthIndicatorTests {

  private WebClient.Builder createWebClientBuilder(HttpStatusCode status) {
    return WebClient.builder()
        .exchangeFunction(
            clientRequest ->
                Mono.just(
                    ClientResponse.create(status)
                        .header("content-type", "application/json")
                        .body("{ \"key\" : \"value\"}")
                        .build()));
  }

  @Test
  void testHealthIsUp() {
    WebClient.Builder webClientBuilder = createWebClientBuilder(HttpStatus.OK);
    RequestApiHealthIndicator healthIndicator =
        new RequestApiHealthIndicator(webClientBuilder, "xxx");
    Health health = healthIndicator.health().block();
    Assertions.assertEquals(Status.UP, health.getStatus());
  }

  @Test
  void testHealthIsDown() {
    WebClient.Builder webClientBuilder = createWebClientBuilder(HttpStatus.INTERNAL_SERVER_ERROR);
    RequestApiHealthIndicator healthIndicator =
        new RequestApiHealthIndicator(webClientBuilder, "xxx");
    Health health = healthIndicator.health().block();
    Assertions.assertEquals(Status.DOWN, health.getStatus());
  }
}
