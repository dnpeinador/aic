package com.prismamp.archetype.dto.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Slf4j
@Schema(name = "ErrorResponse", description = "An error response representation")
public record ErrorResponse<T>(@Schema(description = "List of error type") CustomError<T> error) {

  public ResponseEntity<ErrorResponse<T>> toResponseEntity(Exception ex) {
    log.error("Exception captured", ex);
    return ResponseEntity.status(error.getStatusNumber())
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(this);
  }
}
