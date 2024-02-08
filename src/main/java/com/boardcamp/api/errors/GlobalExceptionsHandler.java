package com.boardcamp.api.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsHandler {
  
  @ExceptionHandler({ClosedRentalError.class})
  public ResponseEntity<String> handleClosedRental(ClosedRentalError exception) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
  }
  @ExceptionHandler({ConflictError.class})
  public ResponseEntity<String> handleConflictError(ConflictError exception) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
  }

  @ExceptionHandler({ NotFoundError.class })
  public ResponseEntity<String> handleNotFoundError(NotFoundError exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  @ExceptionHandler({ UnprocessableEntityError.class })
  public ResponseEntity<String> handleUnprocessableEntity(UnprocessableEntityError exception) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
  }
}
