/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gu3ssit.game;

import javafx.application.Application;
import javafx.stage.Stage;

import com.gu3ssit.home.HomeMain;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author nkengasong
 */
public class Gu3ssItGame extends Application {
    
    Stage stage;
    BooleanProperty ready = new SimpleBooleanProperty(false);

    @Override
    public void start(final Stage primaryStage) throws Exception {

        // Display the home page 
        HomeMain game = new HomeMain();
        game.start(primaryStage);
        
    }
}
