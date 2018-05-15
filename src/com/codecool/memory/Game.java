package com.codecool.memory;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class Game extends Pane {

  public Game() {
    initPiles();
  }

  public void initPiles() {

    Pile stock = new Pile("stock", 10);
    getChildren().add(stock);
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
}
