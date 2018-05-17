package com.codecool.memory;

import java.io.File;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {

    private final String musicFile = "resources/Music/dreams_become_real.mp3";
    private Media sound = new Media(new File(musicFile).toURI().toString());
    private MediaPlayer mediaPlayer = new MediaPlayer(sound);

  public Music() {
    playCardSound("flip.wav");
    playMusic();
  }

  public void playMusic() {
    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    mediaPlayer.play();
    mediaPlayer.setVolume(0.5);
  }

  public void playCardSound(String soundName) {
    String resource = "resources/Music/" + soundName;
    AudioClip sound = new AudioClip(new File(resource).toURI().toString());
    sound.play();
  }
}
