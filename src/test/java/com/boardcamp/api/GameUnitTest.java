package com.boardcamp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.boardcamp.api.DTOs.GameDTO;
import com.boardcamp.api.errors.ConflictError;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.repositories.GamesRepository;
import com.boardcamp.api.services.GamesService;

@SpringBootTest
class GameUnitTest {
  @InjectMocks
  private GamesService gamesService;

  @Mock
  private GamesRepository gamesRepository;

  @Test
  void givenRepeatedGame_whenCreatingGame_thenThrowsError() {
    GameDTO game = new GameDTO( "jose", "randomImg", 2000L, 2L);
    doReturn(true).when(gamesRepository).existsByName(any());

    ConflictError exception = assertThrows(ConflictError.class,
    () -> gamesService.save(game));

    verify(gamesRepository, times(1)).existsByName(any());
    verify(gamesRepository, times(0)).save(any());
    assertNotNull(exception);
    assertEquals("The provided name for game is unavaliable, pick a new one.", exception.getMessage());
  }

  @Test
  void givenValidGame_whenCreatingGame_thenCreatesGame() {
    GameDTO game = new GameDTO("jose", "randomImg", 2000L, 2L);
    GameModel newGame = new GameModel(game);
    
    doReturn(false).when(gamesRepository).existsByName(any());
    doReturn(newGame).when(gamesRepository).save(any());
    GameModel result = gamesService.save(game);

    verify(gamesRepository, times(1)).existsByName(any());
    verify(gamesRepository, times(1)).save(any());
    assertEquals(newGame, result);
  }
}
