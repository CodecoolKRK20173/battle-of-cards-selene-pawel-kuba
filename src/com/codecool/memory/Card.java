public class Card extends ImageView {
  private int cardName;
  private boolean isFaceUp = false;
  private Image frontImage;
  private Image backImage;

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
}
