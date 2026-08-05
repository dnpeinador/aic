package com.prismamp.archetype.exception;

public class UnAuthorizedException extends RuntimeException {
  public UnAuthorizedException(String message) {
    super(message);
  }
}
