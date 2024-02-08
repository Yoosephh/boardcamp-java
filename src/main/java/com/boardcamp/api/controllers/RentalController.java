package com.boardcamp.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.DTOs.RentalDTO;
import com.boardcamp.api.models.RentalsModel;
import com.boardcamp.api.services.RentalService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rentals")
public class RentalController {
  final RentalService rentalService;

  RentalController(RentalService rentalService) {
    this.rentalService = rentalService;
  }

  @PostMapping
  public ResponseEntity<RentalsModel> createRental(@RequestBody @Valid RentalDTO body) {

    RentalsModel rental = rentalService.save(body);
    return ResponseEntity.status(HttpStatus.CREATED).body(rental);
  }

  @GetMapping
  public ResponseEntity<List<RentalsModel>> findAllRentals() {
    List<RentalsModel> rentals = rentalService.findAllRentals();

    return ResponseEntity.status(HttpStatus.OK).body(rentals);
  }

  @PutMapping("/{id}/return")
  public ResponseEntity<RentalsModel> closeRental(@PathVariable Long id){
    if(!rentalService.existsById(id)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    RentalsModel rental = rentalService.close(id);

    return ResponseEntity.status(HttpStatus.OK).body(rental);
  }
}
