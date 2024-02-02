package com.boardcamp.api.services;

import org.springframework.stereotype.Service;

import com.boardcamp.api.DTOs.CustomerDTO;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.repositories.CustomerRepository;

@Service
public class CustomerService {
  
  final CustomerRepository customerRepository;

  CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public CustomerModel save(CustomerDTO body) {
    CustomerModel user = new CustomerModel(body);

    return customerRepository.save(user);
  }

  public boolean existsByCpf(String cpf) {
    return customerRepository.existsByCpf(cpf);
  }
}