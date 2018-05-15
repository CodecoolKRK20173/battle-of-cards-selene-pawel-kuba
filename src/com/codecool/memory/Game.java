package com.codecool.memory;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class Game extends Pane {

  public Game() {}

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
