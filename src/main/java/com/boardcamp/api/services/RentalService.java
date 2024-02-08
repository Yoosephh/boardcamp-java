package com.boardcamp.api.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boardcamp.api.DTOs.RentalDTO;
import com.boardcamp.api.errors.ClosedRentalError;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.models.RentalsModel;
import com.boardcamp.api.repositories.CustomerRepository;
import com.boardcamp.api.repositories.GamesRepository;
import com.boardcamp.api.repositories.RentalsRepository;

@Service
public class RentalService{
  
  final RentalsRepository rentalsRepository;
  final GamesRepository gamesRepository;
  final CustomerRepository customerRepository;

  public RentalService(RentalsRepository rentalsRepository, GamesRepository gamesRepository, CustomerRepository customerRepository) {
    this.rentalsRepository = rentalsRepository;
    this.gamesRepository = gamesRepository;
    this.customerRepository = customerRepository;
  }

  public RentalsModel save(RentalDTO body) {
    CustomerModel customer = customerRepository.findById(body.getCustomerId()).get();
    GameModel game = gamesRepository.findById(body.getGameId()).get();
    RentalsModel rental = new RentalsModel(body, customer, game);

    Long daysRented = rental.getDaysRented();
    Long pricePerDay = game.getPricePerDay();
    Long originalPrice = pricePerDay * daysRented;
    rental.setOriginalPrice(originalPrice);

    return  rentalsRepository.save(rental);
  }

  public List<RentalsModel> findAllRentals() {
      return rentalsRepository.findAll();
  }

  public boolean existsById(Long id) {
    return rentalsRepository.existsById(id);
  }

  public RentalsModel close( Long id) {
    RentalsModel rental = rentalsRepository.findById(id).get();

    if(rental.getReturnDate() != null) throw new ClosedRentalError("The rental is already closed!");

    rental.setReturnDate(LocalDate.now());

    LocalDate rentDate = rental.getRentDate();
    LocalDate returnDate = rental.getReturnDate();

    Long realDaysRented = ChronoUnit.DAYS.between(rentDate, returnDate);
    Long daysRented = rental.getDaysRented();
    if( realDaysRented > daysRented) {
      Long daysDifference = realDaysRented - daysRented;

      Long pricePerDay = rental.getOriginalPrice() / daysRented;

      rental.setDelayFee(daysDifference * pricePerDay);
    }

    return rentalsRepository.save(rental);

  }
}
