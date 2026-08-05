package com.prismamp.archetype.dto.error;

import java.net.URI;
import java.util.List;

public interface CustomError<T> {
  URI getType();

  String getCode();

  T getStatus();

  Integer getStatusNumber();

  String getTitle();

  String getMessage();

  List<ErrorDetail> getDetails();
}
