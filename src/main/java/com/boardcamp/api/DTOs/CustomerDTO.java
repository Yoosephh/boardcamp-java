package com.boardcamp.api.DTOs;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;


public class CustomerDTO {
  
  private String name;

  @Size(min = 11, max = 11, message= "Provide a CPF in the '00000000000' format.")
  @Digits(integer = 11,  fraction = 0, message="The CPF must be numeric only.")
  private String cpf;
}
