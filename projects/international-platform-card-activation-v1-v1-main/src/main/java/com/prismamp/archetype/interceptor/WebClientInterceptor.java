package com.prismamp.archetype.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prismamp.archetype.commons.constant.ProblemType;
import com.prismamp.archetype.dto.error.CustomError;
import com.prismamp.archetype.dto.error.ErrorHandler;
import com.prismamp.archetype.dto.error.ErrorResponse;
import com.prismamp.archetype.exception.GenericException;
import com.prismamp.archetype.exception.UnAuthorizedException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class WebClientInterceptor implements WebFilter {

  @Autowired private ErrorHandler errorHandler;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    return chain
        .filter(exchange)
        .onErrorResume(
            throwable -> {
              if (throwable instanceof UnAuthorizedException) {
                ErrorResponse errorResponse =
                    getErrorResponse(exchange, throwable, ProblemType.HTTP_FORBIDDEN);
                ServerHttpResponse response =
                    createServerHttpResponse(exchange, ProblemType.HTTP_FORBIDDEN);

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonErrorResponse = null;
                try {
                  jsonErrorResponse = objectMapper.writeValueAsString(errorResponse);
                } catch (JsonProcessingException e) {
                  log.error("Error converting to JSON", e);
                  return Mono.error(e);
                }
                DataBuffer buffer = getDataBuffer(response, jsonErrorResponse);

                return response.writeWith(Mono.just(buffer));
              }

              if (throwable instanceof GenericException) {
                GenericException exception = (GenericException) throwable;
                ErrorResponse errorResponse =
                    getErrorResponse(exchange, throwable, exception.getProblemType());
                ServerHttpResponse response =
                    createServerHttpResponse(exchange, exception.getProblemType());

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonErrorResponse = null;
                try {
                  jsonErrorResponse = objectMapper.writeValueAsString(errorResponse);
                } catch (JsonProcessingException e) {
                  log.error("Error converting to JSON", e);
                  return Mono.error(e);
                }
                DataBuffer buffer = getDataBuffer(response, jsonErrorResponse);

                return response.writeWith(Mono.just(buffer));
              }

              return Mono.error(throwable);
            });
  }

  private static ServerHttpResponse createServerHttpResponse(
      ServerWebExchange exchange, ProblemType problemType) {
    ServerHttpResponse response = exchange.getResponse();
    response.setStatusCode(problemType.getStatus());
    response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
    return response;
  }

  private ErrorResponse getErrorResponse(
      ServerWebExchange exchange, Throwable throwable, ProblemType problemType) {
    CustomError customError = errorHandler.createError(exchange, throwable, problemType);
    ErrorResponse errorResponse = new ErrorResponse(customError);
    return errorResponse;
  }

  private static DataBuffer getDataBuffer(ServerHttpResponse response, String jsonErrorResponse) {
    byte[] responseBytes = jsonErrorResponse.getBytes(StandardCharsets.UTF_8);
    DataBuffer buffer = response.bufferFactory().wrap(responseBytes);
    return buffer;
  }
}
