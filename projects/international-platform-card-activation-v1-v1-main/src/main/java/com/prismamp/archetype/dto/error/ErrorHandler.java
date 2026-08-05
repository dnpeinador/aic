package com.prismamp.archetype.dto.error;

import com.prismamp.archetype.commons.constant.ProblemType;
import com.prismamp.archetype.configuration.openapi.OpenApiConfigurationProperties;
import com.prismamp.archetype.configuration.property.ErrorsProperties;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class ErrorHandler {
  @Autowired private ErrorsProperties errorsConfig;

  @Autowired private OpenApiConfigurationProperties openApiConfigurationProperties;

  public CustomError createError(
      ServerWebExchange exchange, Throwable throwable, ProblemType problemType) {
    boolean allowRetroCompatibility = errorsConfig.isAllowRetroCompatibility();

    CustomError customError = null;

    if (allowRetroCompatibility) {
      customError =
          new RetroCompatibilityError(
              String.valueOf(problemType.getStatus().value()),
              openApiConfigurationProperties.getPrefix() + problemType.getStatus().value(),
              throwable.getMessage());
    } else {
      customError =
          new Error(
              problemType.getType(),
              openApiConfigurationProperties.getPrefix() + problemType.getCode(),
              problemType.getStatus().value(),
              problemType.getTitle(),
              throwable.getMessage(),
              exchange.getRequest().getMethod().name()
                  + " "
                  + exchange.getRequest().getPath().value(),
              List.of());
    }

    return customError;
  }
}
