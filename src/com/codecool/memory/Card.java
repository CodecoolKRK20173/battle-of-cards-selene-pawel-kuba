package com.codecool.memory;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Card extends ImageView {
  private int cardName;
  private boolean isFaceUp = false;
  private Image frontImage;
  private Image backImage;
  private DropShadow dropShadow;
  public static final int WIDTH = 65;
  public static final int HEIGHT = 100;

  public Card(int name) {
    this.cardName = cardName;
    this.dropShadow = new DropShadow(2, Color.gray(0, 0.80));
    setEffect(dropShadow);
    setImage(isFaceUp ? frontImage : backImage);
  }

  public boolean getIsFaceUp() {
    return isFaceUp;
  }

  public int getName() {
    return cardName;
  }

  public void setIsFaceUp() {
    isFaceUp = !isFaceUp;
    setImage(isFaceUp ? frontImage : backImage);
  }

  public static void createStartPile(Pile pile, int numberOfPairs) {
    for (int i = numberOfPairs; i > 0; i--) {
      pile.addCard(new Card(i));
      pile.addCard(new Card(i));
    }
    pile.shufflePile();
  }

  public void moveCard(Pile stock, Pile destPile) {
    //  this.stock.remove(thi  destPile.addCard(this);
  }
}
