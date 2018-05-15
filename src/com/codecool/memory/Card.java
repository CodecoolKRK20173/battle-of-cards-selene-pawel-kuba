package com.codecool.memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
  public static final int WIDTH = 150;
  public static final int HEIGHT = 210;

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

  public List<Card> createStartPile(int numberOfPairs) {
    List<Card> result = new ArrayList<>();
    for (int i = numberOfPairs; i > 0; i--) {
      result.add(new Card(i));
      result.add(new Card(i));
    }
    Collections.shuffle(result);
    return result;
  }
}
