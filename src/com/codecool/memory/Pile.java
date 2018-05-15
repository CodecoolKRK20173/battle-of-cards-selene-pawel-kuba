package com.codecool.memory;

import javafx.collections.ObservableList;

public class Pile extends Pane {

  private PileType pileType;
  private String name;
  private double cardGap;
  private ObservableList<Card> cards = FXCollections.observableArrayList();

  public Pile(PileType pileType, String name, double cardGap) {
    this.pileType = pileType;
    this.cardGap = cardGap;
  }

  public PileType getPileType() {
    return pileType;
  }

  public getName() {
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

  @Override
  public boolean isEmpty() {
    return cards.isEmpty();
  }

  public enum PileType {
    STOCK,
    PLAYERS
  }

  public void shufflePile() {
    FXCollections.shuffle(cards);
  }
}
