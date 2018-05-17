package com.codecool.memory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.layout.Pane;

public class Game extends Pane {
  public Pile stock;
  private Pile playerPile;
  private List<Card> deck = new ArrayList<>();
  private ArrayList<Card> cardsFacedUp = new ArrayList<>();
  private Music sound = new Music();
  private int pairs;

  public Game() {
    initPiles();
    stock.shufflePile();
    setCardsOnTable();
    cardsNames(stock);
    mouseClick();
  }

  public void mouseClick() {
    Iterator<Card> stockIterator = stock.getCards().iterator();
    stockIterator.forEachRemaining(
        card -> {
          card.setOnMouseClicked(onMouseClickedHandler);
        });
  }

  public void cardsNames(Pile pile) {
    ObservableList<Card> cards = pile.getCards();
    for (Card c : cards) {}
  }

  public void initPiles() {
    stock = new Pile("stock", 10);
    Card.createStartPile(stock, 30);

    playerPile = new Pile("playerPile", 0);
    playerPile.setBlurredBackground();
    playerPile.setLayoutX(95);
    playerPile.setLayoutY(20);
  }

  public boolean isWon() {
    if (playerPile.getCards().size() == 60) return true;
    return false;
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
    int baseWidth = 175;
    int width = 81;
    int baseHeight = 75;
    int height = 115;
    int r = 0;
    double margin = 0;
    for (int i = 0; i < 60; i++) {
      Card card = cards.get(i);

      card.setLayoutX(baseWidth + (r * width));
      card.setLayoutY(baseHeight + (j * height));
      r++;
      if (r == 12) {
        r = 0;
        j++;
      }
      getChildren().add(card);
    }
  }

  private EventHandler<MouseEvent> onMouseClickedHandler =
      e -> {
        Card card = (Card) e.getSource();
        sound.playCardSound("flip.wav");

        if (cardsFacedUp.size() == 2) {
          cardsFacedUp.get(0).flip();
          cardsFacedUp.get(1).flip();
          cardsFacedUp.clear();
        }
        card.flip();
        cardsFacedUp.add(card);
        if (cardsFacedUp.size() == 2) {
          if (cardsFacedUp.get(0).getName() == cardsFacedUp.get(1).getName()
              && !cardsFacedUp.get(0).equals(cardsFacedUp.get(1))) {
            sound.playCardSound("pair.wav");
            cardsFacedUp.clear();
          }
          for (int i = 0; i < cardsFacedUp.size(); i++) {
            System.out.print(i);
            System.out.print(" karta ");
            System.out.println(cardsFacedUp.get(i).getName());
          }
        }
      };
}
