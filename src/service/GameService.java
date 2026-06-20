package service;

import entity.Game;

public class GameService {

 private Game game;

 public void startNewGame(int userId, int gridSize, int mineCount) {
	    game = new Game(userId, gridSize, mineCount);
	}


 public void endGame() {
     if (game != null) {
         game.setStatus("Finished");
     }
 }

 public Game getGame() {
     return game;
 }
}
