package src;

public class Game extends Deck {
  Game(){
    startGame();
  }

  public void startGame(){
    buildDeck();
    embaralhaDeck();
    
  }
}