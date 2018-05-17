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
import java.util.HashMap;

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
    GameMode mode = selectGameDifficulty();
    initPiles(mode.getNumOfCards());
    setCardsOnTable(mode.getAllStats());
    startTime = System.nanoTime();    
    mouseClick();
  }

  private GameMode selectGameDifficulty() {
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
    String mode = (String) result.get();
    return gameModeMap().get(mode);
  }

  private final HashMap<String, GameMode> gameModeMap() {
    HashMap<String, GameMode> gameModes = new HashMap<>();
    gameModes.put("Easy", new Easy());
    gameModes.put("Medium", new Medium());
    gameModes.put("Insane", new Hard());

    return gameModes;
  }

  public void mouseClick() {
    Iterator<Card> stockIterator = stock.getCards().iterator();
    stockIterator.forEachRemaining(
        card -> {
          card.setOnMouseClicked(onMouseClickedHandler);
        });
  }

  public void initPiles(int numOfCards) {
    stock = new Pile("stock");
    
    Card.createStartPile(stock, numOfCards);

    playerPile = new Pile("playerPile");
    playerPile.setBlurredBackground();
    playerPile.setLayoutX(95);
    playerPile.setLayoutY(20);
    stock.shufflePile();
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

  public void setCardsOnTable(ArrayList<Integer> allStats) {
    int baseWidth = allStats.get(0);
    int baseHeight = allStats.get(1);
    int numOfCards = allStats.get(2);
    int numOfRows = allStats.get(3);
    int width = allStats.get(4);
    

    ObservableList<Card> cards = stock.getCards();
    int j = 0;
    int height = 115;
    int columnNumber = 0;
    double margin = 0;
    int r = 0;
    for (int i = 0; i < 2*numOfCards; i++) {
      Card card = cards.get(i);

      card.setLayoutX(baseWidth + (r * width));
      card.setLayoutY(baseHeight + (j * height));
      r++;
      if (r == numOfRows) {
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
      GameMode mode = selectGameDifficulty();
      initPiles(mode.getNumOfCards());
      setCardsOnTable(mode.getAllStats());
      mouseClick();
      startTime = System.nanoTime();
    } else {
      System.exit(0);
    }
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
