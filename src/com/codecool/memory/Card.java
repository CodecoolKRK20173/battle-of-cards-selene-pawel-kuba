package com.codecool.memory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Card extends ImageView implements Comparable {
  private int cardName;
  private boolean isFaceUp = false;
  private Image frontImage;
  private Image backImage;
  private DropShadow dropShadow;
  public static final int WIDTH = 66;
  public static final int HEIGHT = 100;

  public Card(int name) {
    this.cardName = name;
    this.dropShadow = new DropShadow(2, Color.gray(0, 0.80));
    frontImage = new Image("Images/" + Integer.toString(name + 1) + ".png");
    backImage = new Image("Images/skull.png");
    setImage(isFaceUp ? frontImage : backImage);
    setEffect(dropShadow);
  }

  @Override
  public int compareTo(Object object) {
    Card card = (Card) object;
    return Integer.compare(this.cardName, card.getName());
  }

  public boolean getIsFaceUp() {
    return isFaceUp;
  }

  public int getName() {
    return cardName;
  }

  public String nameAsString(int name) {
    StringBuilder str = new StringBuilder();
    str.append(name);
    return str.toString();
  }

  public static void createStartPile(Pile pile, int numberOfPairs) {
    for (int i = 0; i < numberOfPairs; i++) {
      pile.addCard(new Card(i));
      pile.addCard(new Card(i));
    }
  }

  public void moveCard(Pile stock, Pile destPile) {
    stock.getCards().remove(this);
    destPile.addCard(this);
  }

  public void flip() {
    isFaceUp = !isFaceUp;
    setImage(isFaceUp ? frontImage : backImage);
  }
}
