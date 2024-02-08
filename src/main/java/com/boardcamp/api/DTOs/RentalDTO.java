package com.boardcamp.api.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
@Data
public class RentalDTO {
  @NotNull(message = "field customerId is required")
  private Long customerId;

  @NotNull(message = "field gameId is required")
  private Long gameId;

  @NotNull(message =  "field daysRented is required")
  @Positive(message = "field daysRented should be greater than 0")
  private Long daysRented;
}
