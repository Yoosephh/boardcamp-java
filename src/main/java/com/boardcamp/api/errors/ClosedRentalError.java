package com.boardcamp.api.errors;

public class ClosedRentalError extends RuntimeException {
  public ClosedRentalError(String message) {
    super(message);
  }
}
