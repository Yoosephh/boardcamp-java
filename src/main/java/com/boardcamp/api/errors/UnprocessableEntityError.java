package com.boardcamp.api.errors;

public class UnprocessableEntityError extends RuntimeException {
  public UnprocessableEntityError(String message) {
    super(message);
  }
}
