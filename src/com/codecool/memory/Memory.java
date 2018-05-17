package com.codecool.memory;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class Memory extends Application {
  private final double WINDOW_WIDTH = 1400;
  private final double WINDOW_HEIGHT = 900;
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    Game game = new Game();
    new Music();    
    game.setTableBackground(new Image("table/green.png"));
    primaryStage.getIcons().add(new Image("Images/skull.png"));
    primaryStage.setTitle("Memory Game");
    primaryStage.setScene(new Scene(game, WINDOW_WIDTH, WINDOW_HEIGHT));
    primaryStage.show();
  }
}
