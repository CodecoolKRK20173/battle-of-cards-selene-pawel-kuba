package com.codecool.memory;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card extends ImageView {
  private int cardName;
  private boolean isFaceUp = false;
  private Image frontImage;
  private Image backImage;
  private Pile containingPile;

  public Card(int name) {
    this.cardName = cardName;
  }

  public boolean getIsFaceUp() {
    return isFaceUp;
  }

  public int getName() {
    return cardName;
  }

  public void setIsFaceUp() {
    isFaceUp = !isFaceUp;
  }

  public Pile getContainingPile() {
    return containingPile;
  }
}
