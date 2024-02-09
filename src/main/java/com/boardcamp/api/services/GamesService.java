package com.boardcamp.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.DTOs.GameDTO;
import com.boardcamp.api.errors.ConflictError;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.repositories.GamesRepository;

@Service
public class GamesService {
  
  final GamesRepository gamesRepository;

  GamesService(GamesRepository gamesRepository) {
    this.gamesRepository = gamesRepository;
  }

  public GameModel save(GameDTO body) {
    if(gamesRepository.existsByName(body.getName())){ 
      throw new ConflictError("The provided name for game is unavaliable, pick a new one.");
    }

    GameModel game = new GameModel(body);
    return gamesRepository.save(game);
  }

  public List<GameModel>  getAll() {
    return gamesRepository.findAll();
  }

  public boolean existsByName(String name) {
    return gamesRepository.existsByName(name);
  }
}
