package com.battleshipics4u.game;

import com.battleshipics4u.game.ships.Orientation;
import com.battleshipics4u.game.ships.Ship;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Scanner;

//import custom classes
import com.battleshipics4u.game.menuClasses.*;

public class MainApplication extends Application {
    private static Stage stage;
    public static StartMenu start;
    public static InstructionMenu help;

    @Override
    public void start(Stage primaryStage) throws IOException {
        MainApplication.stage = primaryStage;
        stage.setTitle("Battleship");

        //create all the menuClasses
        start = new StartMenu(primaryStage);
        //help = new InstructionMenu();

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
        // Scanner in = new Scanner(System.in);
        // GameStates mainGame = new GameStates();
        // System.out.println("testing---");
        // mainGame.generateEnemyShipPlacements();
        // mainGame.printEnemyShipLocations();
        // for (int i = 0; i < 5; i++) {
        //     System.out.println("Please enter the location of the " + Ship.getShipName.get(i));
        //     System.out.println("Enter row and then column and then the direction (H or V). ");
        //     int row = in.nextInt(), col = in.nextInt();
        //     String dir = in.next();
        //     Orientation orientation = dir.equals("H") ? Orientation.Horizontal : Orientation.Vertical;
        //     while (!mainGame.placeShip(i, row, col, orientation)) {
        //         System.out.println("Invalid. ");
        //         System.out.println(mainGame.placeShip(i, row, col, orientation));
        //         System.out.println("Please enter the location of the " + Ship.getShipName.get(i));
        //         System.out.println("Enter row and then column and then the direction (H or V). ");
        //         row = in.nextInt();
        //         col = in.nextInt();
        //         dir = in.next();
        //         orientation = dir.equals("H") ? Orientation.Horizontal : Orientation.Vertical;
        //     }
        // }

        launch();
    }
}