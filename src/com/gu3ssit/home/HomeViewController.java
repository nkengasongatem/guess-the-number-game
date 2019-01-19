/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gu3ssit.home;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author nkengasong
 */
public class HomeViewController implements Initializable {

    // Set default level to level 1
    public static String gameLevel = "1";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private StackPane stackpane;

    @FXML
    private AnchorPane homeAnchorPane;

    @FXML
    private JFXButton homePlay;

    @FXML
    private JFXButton howToPlay;

    @FXML
    private VBox selectLevel;

    @FXML
    private JFXButton level1;

    @FXML
    private JFXButton level2;

    @FXML
    private JFXButton level3;

    public static String getGameLevel() {
        return gameLevel;
    }

    public static void setGameLevel(String gameLevel) {
        HomeViewController.gameLevel = gameLevel;
    }

    @FXML
    void playLevel1(ActionEvent event) {
        gameLevel = "1";
        selectedPlay();
    }

    @FXML
    void playLevel2(ActionEvent event) {
        gameLevel = "2";
        selectedPlay();
    }

    @FXML
    void playLevel3(ActionEvent event) {
        gameLevel = "3";
        selectedPlay();
    }

    @FXML
    private JFXButton setting;

    void selectedPlay() {
        try {
            // get HowToPlay scene
            Parent root = FXMLLoader.load(getClass().getResource("/com/gu3ssit/play/PlayHome.fxml"));

            // get current scene
            Scene scene = homePlay.getScene();
            root.translateXProperty().set(scene.getWidth());
            stackpane.getChildren().add(root);

            // add animation to slide scenes
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(finishEvent -> {
                stackpane.getChildren().remove(homeAnchorPane);
            });
            timeline.play();

        } catch (IOException ex) {
            System.out.println("Exception in selectedPlay() at HomeViewController\n");
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void homePlay(ActionEvent event) {
        // ask user to select game level - Fade in select level vbox
        selectLevel.setVisible(true);
        Scene scene = selectLevel.getScene();
    }

    @FXML
    void howToPlay(ActionEvent event) {
        try {
            // set game level to 1
            setGameLevel("1");
            
            // get HowToPlay scene
            Parent root = FXMLLoader.load(getClass().getResource("/com/gu3ssit/howToPlay/HowToPlay.fxml"));

            // get current scene
            Scene scene = howToPlay.getScene();
            root.translateXProperty().set(scene.getWidth());
            stackpane.getChildren().add(root);

            // add animation to slide scenes
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(finishEvent -> {
                stackpane.getChildren().remove(homeAnchorPane);
            });
            timeline.play();

        } catch (IOException ex) {
            System.out.println("Exception in howToPlay() at HomeViewController\n");
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void setting(ActionEvent event) {
        try {
            // get setting scene
            Parent root = FXMLLoader.load(getClass().getResource("/com/gu3ssit/setting/Setting.fxml"));

            // get current scene
            Scene scene = setting.getScene();
            root.translateXProperty().set(scene.getWidth());
            stackpane.getChildren().add(root);

            // add animation to slide scenes
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(finishEvent -> {
                stackpane.getChildren().remove(homeAnchorPane);
            });
            timeline.play();

        } catch (IOException ex) {
            System.out.println("Exception in setting() at HomeViewController\n");
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
