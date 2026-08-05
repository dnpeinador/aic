package com.prismamp.archetype.exception;

import com.prismamp.archetype.commons.constant.ProblemType;
import lombok.Getter;

@Getter
public class GenericException extends RuntimeException {

  private ProblemType problemType;

  public GenericException(ProblemType problemType, String message) {
    super(message);
    this.problemType = problemType;
  }
}
