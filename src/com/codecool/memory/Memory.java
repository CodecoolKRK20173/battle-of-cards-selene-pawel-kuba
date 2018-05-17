package com.codecool.memory;

import javafx.application.Application;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.control.*;
import java.util.Optional;
import java.util.HashMap;


public class Memory extends Application {
  private final double WINDOW_WIDTH = 1400;
  private final double WINDOW_HEIGHT = 900;

  private Music music;
  private boolean winCondition;
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    music = new Music();
    GameMode difficulty = selectGameDifficulty();
    Game game = new Game(difficulty);
    game.setTableBackground(new Image("table/green.png"));
    primaryStage.getIcons().add(new Image("Images/skull.png"));
    primaryStage.setTitle("Memory Game");
    primaryStage.setScene(new Scene(game, WINDOW_WIDTH, WINDOW_HEIGHT));
    
    primaryStage.show();
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

  public final HashMap<String, GameMode> gameModeMap() {
    HashMap<String, GameMode> gameModes = new HashMap<>();
    gameModes.put("Easy", new Easy());
    gameModes.put("Medium", new Medium());
    gameModes.put("Insane", new Hard());

    return gameModes;
  }
}
