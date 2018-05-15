package com.codecool.memory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.effect.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Pile extends Pane {

  private String name;
  private double cardGap;
  private ObservableList<Card> cards = FXCollections.observableArrayList();

  public Pile(String name, double cardGap) {

    this.cardGap = cardGap;
  }

  public String getName() {
    return name;
  }

  public double getCardGap() {
    return cardGap;
  }

  public ObservableList<Card> getCards() {
    return cards;
  }

  public int numOfCards() {
    return cards.size();
  }

  public boolean isEmpty() {
    return cards.isEmpty();
  }

  public void shufflePile() {
    FXCollections.shuffle(cards);
  }

  public void addCard(Card card) {
    cards.add(card);
  }

  public void flushPile() {
    cards.clear();
  }

  public void setBlurredBackground() {
    setPrefSize(Card.WIDTH, Card.HEIGHT);
    BackgroundFill backgroundFill = new BackgroundFill(Color.gray(0.0, 0.2), null, null);
    Background background = new Background(backgroundFill);
    GaussianBlur gaussianBlur = new GaussianBlur(10);
    setBackground(background);
    setEffect(gaussianBlur);
  }
}
