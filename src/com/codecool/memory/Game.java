package com.codecool.memory;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.event.EventHandler;
import java.util.ArrayList;

import com.codecool.klondike.Card;


public class Game extends Pane {

  private ArrayList<Card> cardsFacedUp = new ArrayList();

  public Game() {}

  public void setTableBackground(Image tableBackground) {
    setBackground(
        new Background(
            new BackgroundImage(
                tableBackground,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
  }
  private EventHandler<MouseEvent> onMouseClickedHandler =
  e -> {
    Card card = (Card) e.getSource();
    if (cardsFacedUp.size() < 2) {
      card.setIsFaceUp(); 
      cardsFacedUp.add(card);
    } 
    if (cardsFacedUp == 2) {
      handleGuessAttempt();
    }
    
    card.setMouseTransparent(false);
    System.out.println("Placed " + card + " to up.");
  };

  private void handleGuessAttempt() {
    Card card1 = cardsFacedUp.get(0);
    Card card2 = cardsFacedUp.get(1);
    if (card1.getName().equals(card2.getName())){
      handleRightGuess();
    } else {
      handleWrongGuess();
    }
  }

  private void handleWrongGuess(){
    for (int i = 0; i > cardsFacedUp.size(); i++) {
      Card cardFacedUp = cardsFacedUp.get(i);
      cardsFacedUp.remove(cardFacedUp);
      cardFacedUp.setFaceDown();
    }
  }

  private void handleRightGuess(){
    // remove cards from Pile
  }

  

}
