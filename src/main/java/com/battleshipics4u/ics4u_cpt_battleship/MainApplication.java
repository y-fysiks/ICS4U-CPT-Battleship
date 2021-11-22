package com.battleshipics4u.ics4u_cpt_battleship;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

//custom package imports
import com.battleshipics4u.ics4u_cpt_battleship.menus.*;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Battleship");
        
        stage.setScene(scene);
        stage.show();

        Menu start = new StartMenu();
        Menu instructions = new InstructionsMenu();
        Menu startMenu = new StartMenu();
        Menu endMenu = new EndMenu();
        Menu pauseMenu = new PauseMenu();


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