package com.prismamp.archetype.dto.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import java.net.URI;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Error", description = "An error representation")
public class Error implements CustomError<Integer> {

  @Schema(description = "The error type")
  URI type;

  @Schema(description = "The error code")
  String code;

  @Schema(description = "The error status")
  Integer status;

  @Schema(description = "The error title")
  String title;

  @Schema(description = "The error custom message")
  String message;

  @Schema(description = "The error instance and method")
  String instance;

  @Schema(description = "The error details")
  List<ErrorDetail> details;

  @JsonIgnore
  @Override
  public Integer getStatusNumber() {
    return this.status;
  }
}
