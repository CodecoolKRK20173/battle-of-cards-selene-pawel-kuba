package com.codecool.memory;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class Game extends Pane {

  private ArrayList<Card> cardsFacedUp = new ArrayList();

  public Game() {
    initPiles();
  }

  public void initPiles() {

    Pile stock = new Pile("stock", 10);
    getChildren().add(stock);
  }

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
        if (cardsFacedUp.size() == 2) {
          handleGuessAttempt();
        }

        card.setMouseTransparent(false);
        System.out.println("Placed " + card + " to up.");
      };

  private void handleGuessAttempt() {
    Card card1 = cardsFacedUp.get(0);
    Card card2 = cardsFacedUp.get(1);
    if (card1.getName() == card2.getName()) {
      handleRightGuess();
    } else {
      handleWrongGuess();
    }
  }

  private void handleWrongGuess() {
    for (int i = 0; i > cardsFacedUp.size(); i++) {
      Card cardFacedUp = cardsFacedUp.get(i);
      cardsFacedUp.remove(cardFacedUp);
      cardFacedUp.flip();
    }
  }

  private void handleRightGuess() {
    // remove cards from Pile
  }
}
