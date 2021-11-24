package com.battleshipics4u.game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

//import custom classes
import com.battleshipics4u.game.menuClasses.*;

public class MainApplication extends Application {
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        MainApplication.stage = primaryStage;
        stage.setTitle("Battleship");

        //create all the menuClasses
        StartMenu start = new StartMenu();


        //show the start menu
        start.showMenu();
        stage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        }.start();
        //show the stage
    }

    public static void setScene(Scene scene) {
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}