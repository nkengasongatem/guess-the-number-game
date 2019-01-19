/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gu3ssit.setting;

import com.gu3ssit.home.HomeViewController;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author nkengasong
 */
public class SettingController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private AnchorPane settingAnchorPane;

    @FXML
    private JFXButton homeButton;

    @FXML
    void goHome(ActionEvent event) {
         try {
            // get HowToPlay scene
            Parent root = FXMLLoader.load(getClass().getResource("/com/gu3ssit/home/HomeView.fxml"));
            
            // get current scene
            Scene scene = homeButton.getScene();
            root.translateXProperty().set(scene.getWidth());
            StackPane stackpane = (StackPane) scene.getRoot();
            stackpane.getChildren().add(root);
            
            // add animation to slide scenes
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(finishEvent -> {
                stackpane.getChildren().remove(settingAnchorPane);
            });
            timeline.play();
            
        } catch (IOException ex) {
            System.out.println("Exception in home() at HowToPlayController\n");
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
