package com.boardcamp.api.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class GameDTO {
  @NotBlank(message = "field name is required")
  private String name;

  private String image;
  
  @Positive(message = "stockTotal should be greater than zero.")
  private Long stockTotal;

  @Positive(message = "pricePerDay should be greater than zero.")
  private Long pricePerDay;
}
