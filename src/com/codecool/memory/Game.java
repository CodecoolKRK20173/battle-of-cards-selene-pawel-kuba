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

  public Game() {
    initPiles();
    setCardsOnTable();
    cardsNames(stock);
    dealCards();
  }

  public void dealCards() {
    Iterator<Card> deckIterator = stock.getCards().iterator();
    deckIterator.forEachRemaining(
        card -> {
          card.setOnMouseClicked(onMouseClickedHandler);
        });
  }

  public void cardsNames(Pile pile) {
    ObservableList<Card> cards = pile.getCards();
    for (Card c : cards) {
      System.out.println(c.getName());
    }
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
    int baseWidth = 200;
    int width = 73;
    int baseHeight = 75;
    int height = 110;
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
      System.out.println("Placed " + card.getName() + " to up.");
    }
  }

  public void addCard() {}

  private EventHandler<MouseEvent> onMouseClickedHandler =
      e -> {
        Card card = (Card) e.getSource();
        if (cardsFacedUp.size() < 2) {
          card.flip();
          cardsFacedUp.add(card);
        }
        if (cardsFacedUp.size() == 2) {

          handleGuessAttempt();
          cardsFacedUp.clear();
        }

        card.setMouseTransparent(false);
        System.out.println("Flipped " + card.getName());
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
