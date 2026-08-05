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
public class RetroCompatibilityError implements CustomError<String> {

  @Schema(description = "The error status")
  String status;

  @Schema(description = "The error code")
  String code;

  @Schema(description = "The error title")
  String title;

  @JsonIgnore
  @Override
  public URI getType() {
    return null;
  }

  @JsonIgnore
  @Override
  public Integer getStatusNumber() {
    return Integer.valueOf(this.status);
  }

  @JsonIgnore
  @Override
  public String getMessage() {
    return null;
  }

  @JsonIgnore
  @Override
  public List<ErrorDetail> getDetails() {
    return null;
  }
}
