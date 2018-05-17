package com.codecool.memory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
  private long finalTime;
  private long startTime;

  public Game() {
    selectGameDifficulty();

    initPiles();
    // stock.shufflePile();
    setCardsOnTable();
    startTime = System.nanoTime();

    mouseClick();
  }

  public void mouseClick() {
    Iterator<Card> stockIterator = stock.getCards().iterator();
    stockIterator.forEachRemaining(
        card -> {
          card.setOnMouseClicked(onMouseClickedHandler);
        });
  }

  public void initPiles() {
    stock = new Pile("stock");
    Card.createStartPile(stock, 30);

    playerPile = new Pile("playerPile");
    playerPile.setBlurredBackground();
    playerPile.setLayoutX(95);
    playerPile.setLayoutY(20);
  }

  public boolean isWon() {
    if (stock.isEmpty()) {
      System.out.println("You won!");
      finalTime = System.nanoTime() - startTime;
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
        System.out.println("size: " + cardsFacedUp.size());
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
      if (isWon()) showWonMessage();
    }
  }

  private void showWonMessage() {
    createWonAlert();
  }

  private void createWonAlert() {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("You won!");
    alert.setHeaderText("You won a game! Your time is: " + (finalTime / 1000000000) + " seconds!");
    alert.setContentText("Do you want to play again?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
      initPiles();
      setCardsOnTable();
      mouseClick();
      startTime = System.nanoTime();
    } else {
      System.exit(0);
    }
  }

  private String selectGameDifficulty() {
    List<String> choices = new ArrayList<String>();
    choices.add("Easy");
    choices.add("Medium");
    choices.add("Insane");
    ChoiceDialog<String> dialog = new ChoiceDialog<>("Easy", choices);
    dialog.setTitle("Select game difficulty");
    dialog.setHeaderText("Please specify game difficulty from list below");
    dialog.setContentText("Difficult: ");
    Optional<String> result = dialog.showAndWait();
    if (result.isPresent()) {
      System.out.println(result.get());
    }
    return result.get();
  }

  private void scoreCard(int index) {
    cardsFacedUp.get(index).moveCard(stock, playerPile);
    getChildren().remove(cardsFacedUp.get(index));
  }

  private boolean isPair() {
    return (cardsFacedUp.get(INDEX0).compareTo(cardsFacedUp.get(INDEX1)) == 0);
  }

  private boolean isPairUnique() {
    return (cardsFacedUp.get(INDEX0).hashCode() != cardsFacedUp.get(INDEX1).hashCode());
  }
}
