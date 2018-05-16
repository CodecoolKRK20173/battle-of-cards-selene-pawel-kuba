package com.codecool.memory;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.layout.Pane;

public class Game extends Pane {
  public Pile stock;
  private Pile playerPile;

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
}
