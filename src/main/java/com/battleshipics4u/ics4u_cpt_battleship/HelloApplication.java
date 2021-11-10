package com.battleshipics4u.ics4u_cpt_battleship;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Testing");
        stage.setScene(scene);
        stage.show();
        // Testing this

        new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        }.start();
        //show the stage
    }

    public static void main(String[] args) {
        launch();
    }
}