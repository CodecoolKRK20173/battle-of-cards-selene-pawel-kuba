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
    System.out.println(stock.getCards());
    setCardsOnTable();
    cardsNames(stock);
    dealCards();
  }

  public void dealCards() {
    Iterator<Card> deckIterator = stock.getCards().iterator();
    deckIterator.forEachRemaining(
        card -> {
          System.out.println("beniz");
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
    int width = 80;
    int height = 120;
    int r = 0;
    int margin = 10;
    for (int i = 0; i < 32; i++) {
      Card card = cards.get(i);
      card.setLayoutX(width + (r * width));
      card.setLayoutY(height + (j * height));
      r++;
      if (r == 7) r = 0;
      if (i % 8 == 0 && i != 0) j++;
      card.setMouseTransparent(false);
      getChildren().add(card);
      System.out.println("Placed " + card.getName() + " to up.");
    }
  }

  public void addCard() {}

  private EventHandler<MouseEvent> onMouseClickedHandler =
      e -> {
        System.out.println("Clicked!");
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
