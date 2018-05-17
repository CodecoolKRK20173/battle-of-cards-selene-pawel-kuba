package com.codecool.memory;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
    Game game = new Game();
    game.setTableBackground(new Image("table/green.png"));
    primaryStage.getIcons().add(new Image("Images/skull.png"));
    primaryStage.setTitle("Memory Game");
    primaryStage.setScene(new Scene(game, WINDOW_WIDTH, WINDOW_HEIGHT));
    primaryStage.show();
  }
}
