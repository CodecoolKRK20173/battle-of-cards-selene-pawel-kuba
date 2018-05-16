package com.codecool.memory;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.layout.Pane;

public class Game extends Pane {
  public Pile stock;
  private Pile playerPile;

  private ArrayList<Card> cardsFacedUp = new ArrayList();

  public Game() {
    initPiles();
    System.out.println(stock.getCards());
    setCardsOnTable();
  }

  public void initPiles() {
    stock = new Pile("stock", 10);
    Card.createStartPile(stock, 32);

    playerPile = new Pile("playerPile", 0);
    playerPile.setBlurredBackground();
    playerPile.setLayoutX(95);
    playerPile.setLayoutY(20);
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

  public void setCardsOnTable() {
    ObservableList<Card> cards = stock.getCards();
    int j = 0;
    for (int i = 0; i < 32; i++) {
      Card card = cards.get(i);
      card.setLayoutX(20 * (i + 1));
      card.setLayoutY(20 * (j + 1));
      if (i % 8 == 0) j++;
      getChildren().add(card);
    }
  }

  public void addCard() {}

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
