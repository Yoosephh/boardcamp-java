package com.boardcamp.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.DTOs.CustomerDTO;
import com.boardcamp.api.errors.ConflictError;
import com.boardcamp.api.errors.NotFoundError;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.services.CustomerService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customers")
public class CustomerController {
  
  final CustomerService customerService;

  CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @PostMapping
  public ResponseEntity<CustomerModel> createCustomer(@RequestBody @Valid CustomerDTO body) {
    if(customerService.existsByCpf(body.getCpf())) {
      throw new ConflictError("The provided CPF is already registered.");
    }

    CustomerModel customer = customerService.save(body);
    return ResponseEntity.status(HttpStatus.CREATED).body(customer);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomerModel> getCustomer(@PathVariable Long id) {
    if(!customerService.existsById(id)) {
      throw new NotFoundError("No customer found for such id.");
    }

    CustomerModel customer = customerService.findById(id);
    return ResponseEntity.status(HttpStatus.OK).body(customer);
  }
  
}
