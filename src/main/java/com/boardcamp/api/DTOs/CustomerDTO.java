package com.boardcamp.api.DTOs;


import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class CustomerDTO {
  @NotBlank(message = "name  cannot be blank or null")
  private String name;

  @Size(min = 11, max = 11, message= "Provide a CPF in the '00000000000' format.")
  @Digits(integer = 11,  fraction = 0, message="The CPF must be numeric only.")
  private String cpf;
}
