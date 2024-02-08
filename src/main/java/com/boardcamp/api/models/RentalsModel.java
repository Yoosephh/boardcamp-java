package com.boardcamp.api.models;

import java.time.LocalDate;

import com.boardcamp.api.DTOs.RentalDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")
public class RentalsModel {

  public RentalsModel(RentalDTO body, CustomerModel customer, GameModel game) {
    this.customer = customer;
    this.game = game;
    this.daysRented = body.getDaysRented();
    this.rentDate = LocalDate.now();
    this.returnDate = null;
    this.delayFee = 0L;
    }

  public void closeRentalsModel() {
    this.returnDate = LocalDate.now();
    }


  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "customerId")
  private CustomerModel customer;

  @ManyToOne
  @JoinColumn(name = "gameId")
  private GameModel game;

  @Column(nullable = false)
  private LocalDate rentDate;

  @Column(nullable = false)
  private Long daysRented;

  @Column
  private LocalDate returnDate;

  @Column
  private Long delayFee;

  @Column
  private Long originalPrice;
}
