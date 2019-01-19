/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gu3ssit.play;

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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author nkengasong
 */
public class PlayHomeController implements Initializable {

    int trials;
    boolean validGuess;
    int randomNumber;
    int maxNumber;

    void init() {
        // GET GAME LEVEL AND PREPARE UI
        trials = 5;
        validGuess = false;
        trialsLeft.setText(trials + "");
        switch (HomeViewController.getGameLevel()) {
            case "1":
                levelLabel.setText("1                                               Range [0 - 0050]");
                randomNumber = (int) ((Math.random()) * 51);
                maxNumber = 51;
                break;
            case "2":
                levelLabel.setText("2                                               Range [0 - 0100]");
                randomNumber = (int) ((Math.random()) * 101);
                maxNumber = 101;
                break;
            case "3":
                levelLabel.setText("3                                               Range [0 - 1000]");
                randomNumber = (int) ((Math.random()) * 1001);
                maxNumber = 1001;
                break;
        }
        randomNumber = 12;
        winImage.setVisible(false);
        wrongGuess.setVisible(false);

        if (guessNumber.getText().equalsIgnoreCase("")) {
            guess.setDisable(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
    }

    @FXML
    private Label congratulation;

    @FXML
    private Label levelLabel;

    @FXML
    private HBox wrongGuess;

    @FXML
    private Label trialsLeft;

    @FXML
    private AnchorPane playAnchorPane;

    @FXML
    private JFXButton guess;

    @FXML
    private VBox userWins;

    @FXML
    private Circle trial1;

    @FXML
    private Circle trial2;

    @FXML
    private Circle trial3;

    @FXML
    private Circle trial4;

    @FXML
    private Circle trial5;

    @FXML
    private Label greater1;

    @FXML
    private VBox lostVBOX;

    @FXML
    private JFXButton tryagain;

    @FXML
    private Label lostmessage;

    @FXML
    private Label lesser1;

    @FXML
    private JFXButton home;

    @FXML
    private ImageView winImage;    
    
    @FXML
    private ImageView failedImage;

    @FXML
    private TextField guessNumber;
    @FXML
    private JFXButton replay;

    @FXML
    private JFXButton levelup;

    @FXML
    void goHome(ActionEvent event) {
        try {
            // get HowToPlay scene
            Parent root = FXMLLoader.load(getClass().getResource("/com/gu3ssit/home/HomeView.fxml"));

            // get current scene
            Scene scene = guess.getScene();
            root.translateYProperty().set(scene.getWidth());
            StackPane stackpane = (StackPane) scene.getRoot();
            stackpane.getChildren().add(root);

            // add animation to slide scenes
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(finishEvent -> {
                stackpane.getChildren().remove(playAnchorPane);
            });
            timeline.play();

        } catch (IOException ex) {
            System.out.println("Exception in goHome() at playHomeController\n");
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void guess(ActionEvent event) {
        // check if button is disabled
        if (!guess.isDisable()) {
            int num = Integer.parseInt(guessNumber.getText());
            findRandomNumber(num);
        } else {
            invalidEntryNotification();
        }
    }

    void findRandomNumber(int numberGuessedByUser) {
        // change color of trial circles
        trials -= 1;
        trialsLeft.setText(trials + "");

        if (numberGuessedByUser > randomNumber || numberGuessedByUser < randomNumber) {
            wrongGuess.setVisible(true);
            switch (trials + "") {
                case "0":
                    /**
                     * ************* USER LOSTS ************
                     */
                    trial5.setFill(Color.RED);
                    trial5.setStroke(Color.RED);
                    guess.setVisible(false);
                    guessNumber.setDisable(true);
                    lostmessage.setText("YOU LOST! THE NUMBER WAS " + randomNumber);
                    // tryagain.setVisible(true);
                    lostVBOX.setVisible(true);
                    // show play again button && display lost message & gif
                    break;
                case "1":
                    trial4.setFill(Color.RED);
                    trial4.setStroke(Color.RED);
                    break;
                case "2":
                    trial3.setFill(Color.RED);
                    trial3.setStroke(Color.RED);
                    break;
                case "3":
                    trial2.setFill(Color.RED);
                    trial2.setStroke(Color.RED);
                    break;
                case "4":
                    trial1.setFill(Color.RED);
                    trial1.setStroke(Color.RED);
                    break;
            }
            if (numberGuessedByUser < randomNumber) {
                greater1.setText("> " + numberGuessedByUser);
                greater1.setOpacity(1);
            } else if (numberGuessedByUser > randomNumber) {
                lesser1.setText("< " + numberGuessedByUser);
                lesser1.setOpacity(1);
            }
        } else {
            /**
             * ************* USER WINS ************
             */
            guessNumber.setDisable(true);
            wrongGuess.setVisible(false); //remove wrong guess guides
            // get trial number and set winning trial circle to white
            switch (trials + "") {
                case "0":
                    trial5.setFill(Color.WHITE);
                    trial5.setStroke(Color.WHITE);
                    break;
                case "1":
                    trial4.setFill(Color.WHITE);
                    trial4.setStroke(Color.WHITE);
                    break;
                case "2":
                    trial3.setFill(Color.WHITE);
                    trial3.setStroke(Color.WHITE);
                    break;
                case "3":
                    trial2.setFill(Color.WHITE);
                    trial2.setStroke(Color.WHITE);
                    break;
                case "4":
                    trial1.setFill(Color.WHITE);
                    trial1.setStroke(Color.WHITE);
                    break;
            }
            
            guess.setVisible(false);
            userWins.setVisible(true);
            // check if user is at level 3
            if ((HomeViewController.getGameLevel() + "").equals("3")) {
                levelup.setVisible(false);
            }
            // display congratulations message
            congratulation.setText("GOOD JOB! THE NUMBER WAS " + randomNumber);
            
            // show congratulations gif
            winImage.setVisible(true);
        }

    }

    @FXML
    void testGuessValue(KeyEvent event) {
        try {
            Integer userGuess = Integer.parseInt(guessNumber.getText());
            if (userGuess > (maxNumber - 1) || userGuess < 0) {
                guess.setDisable(true);
            } else {
                guess.setDisable(false);
            }
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Error occurred in testGuessValue: Found in PlayHomeController");
            guess.setDisable(true);
        }
    }

    // Play Again after the user loss
    @FXML
    void tryagain(ActionEvent event) {
        setTrialCirclesGreen();
        lostVBOX.setVisible(false);

    }

    @FXML
    void levelup(ActionEvent event) {
        // cannot level up at the last level(3)
        // increment the level when the user has won and replay
        if (!(HomeViewController.getGameLevel() + "").equals("3")) {
            HomeViewController.setGameLevel((Integer.parseInt(HomeViewController.getGameLevel()) + 1) + "");
            setTrialCirclesGreen();
            userWins.setVisible(false);
            levelUpNotification();
        }
    }

    // Play again after the user wins
    @FXML
    void replay(ActionEvent event) {
        setTrialCirclesGreen();
        userWins.setVisible(false);
    }

    void setTrialCirclesGreen() {
        // empty textfield so it disables the guess button
        guessNumber.setText("");
        init();
        trial5.setFill(Paint.valueOf("#29b563"));
        trial5.setStroke(Paint.valueOf("#29b563"));
        trial4.setFill(Paint.valueOf("#29b563"));
        trial4.setStroke(Paint.valueOf("#29b563"));
        trial3.setFill(Paint.valueOf("#29b563"));
        trial3.setStroke(Paint.valueOf("#29b563"));
        trial2.setFill(Paint.valueOf("#29b563"));
        trial2.setStroke(Paint.valueOf("#29b563"));
        trial1.setFill(Paint.valueOf("#29b563"));
        trial1.setStroke(Paint.valueOf("#29b563"));
        guessNumber.setDisable(false);
        guess.setVisible(true);
        lesser1.setText("");
        greater1.setText("");
    }
    
    public void levelUpNotification() {
        Image img = new Image("/com/gu3ssit/images/one.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Congratulations")
                .text("High Five, You just moved to the next level!")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.showConfirm();
    }
    
    public void invalidEntryNotification() {
        Image img = new Image("/com/gu3ssit/images/close-button.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Invalid Entry")
                .text("Your entry must be a number within the level's range!")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(3))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.showConfirm();
    }
}
