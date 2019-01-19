/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gu3ssit.home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author nkengasong
 */
public class HomeMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {            
    
            Parent root = FXMLLoader.load(getClass().getResource("/com/gu3ssit/home/HomeView.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/com/gu3ssit/home/homeview.css").toExternalForm());
            primaryStage.resizableProperty().setValue(Boolean.FALSE);
            primaryStage.setTitle("GU3SS IT");
            primaryStage.sizeToScene();
    
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
