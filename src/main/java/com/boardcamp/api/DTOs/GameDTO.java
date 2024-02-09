package com.boardcamp.api.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameDTO {
  @NotBlank(message = "field name is required")
  private String name;

  private String image;
  
  @NotNull(message = "field stockTotal is required")
  @Positive(message = "stockTotal should be greater than zero.")
  private Long stockTotal;

  @NotNull(message = "field pricePerDay is required")
  @Positive(message = "pricePerDay should be greater than zero.")
  private Long pricePerDay;
}
