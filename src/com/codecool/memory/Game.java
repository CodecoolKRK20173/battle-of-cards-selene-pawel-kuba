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
  private final int PAIR = 2;
  private final int INDEX0 = 0;
  private final int INDEX1 = 1;

  public Game() {
    initPiles();
    //stock.shufflePile();
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
    if (playerPile.getCards().size() == 60) {
      System.out.println("You won!");
      return true;
    }
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
        flipWrongCards();
        card.flip();
        cardsFacedUp.add(card);
        handleProperCards();

      };

  private void flipWrongCards() {
    if (cardsFacedUp.size() == PAIR) {
      cardsFacedUp.get(INDEX0).flip();
      cardsFacedUp.get(INDEX1).flip();
      cardsFacedUp.clear();
    }
  }

  private void handleProperCards() {
    if (cardsFacedUp.size() == PAIR) {
      if (isPair() && isPairUnique()) {
        sound.playCardSound("pair.wav");
        scoreCard(INDEX0);
        scoreCard(INDEX1);
        cardsFacedUp.clear();
      }
      isWon();
    }
  }

  private void scoreCard(int index) {
    cardsFacedUp.get(index).moveCard(stock, playerPile);
    getChildren().remove(cardsFacedUp.get(index));
    
  }

  private boolean isPair(){
    if (cardsFacedUp.get(INDEX0).compareTo(cardsFacedUp.get(INDEX1)) == 0) {
      return true;
    }
    return false;
  } 

  private boolean isPairUnique() {
    if (cardsFacedUp.get(INDEX0).hashCode() != cardsFacedUp.get(INDEX1).hashCode()) {
       return true;
    }
      return false;
  }
}
