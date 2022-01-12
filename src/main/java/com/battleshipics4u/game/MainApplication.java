package com.battleshipics4u.game;

import com.battleshipics4u.game.ships.Orientation;
import com.battleshipics4u.game.ships.Ship;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;


//import custom classes
import com.battleshipics4u.game.menuClasses.*;

public class MainApplication extends Application {
    private static Stage stage;
    public static StartMenu startMenu;
    public static InstructionMenu instructionMenu;
    public static PlayerBoardSetMenu playerBoardSetMenu;
    public static MainGameplayMenu mainGameplayMenu;
    public static GameStates mainGame;

    @Override
    public void start(Stage primaryStage) throws IOException {
        //load fonts
        Font.loadFont(getClass().getResourceAsStream("/com/battleshipics4u/game/fonts/agency-fb-bold.ttf" ), 36.0); // loads ALL the fonts of family agency-fb-bold, not just size 36

        MainApplication.stage = primaryStage;
        stage.setTitle("Battleship");
        stage.setResizable(false);

        //create GameState
        mainGame = new GameStates();

        //create all the menuClasses
        startMenu = new StartMenu(primaryStage);
        instructionMenu = new InstructionMenu();
        playerBoardSetMenu = new PlayerBoardSetMenu();
        mainGameplayMenu = new MainGameplayMenu();

        //show the start menu
        startMenu.showMenu();
        stage.show();


    }

    public static void setScene(Scene scene) {
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        //debugging purposes, comment this out when you want to see the GUI running
        //debug();

        launch();
    }

    static void debug() {
        Scanner in = new Scanner(System.in);
        GameStates mainGame = new GameStates();
        System.out.println("testing---");
        mainGame.generateEnemyShipPlacements();
        mainGame.printEnemyShipLocations();
        for (int i = 0; i < 5; i++) {
            System.out.println("Please enter the location of the " + Ship.getShipName.get(i));
            System.out.println("Enter row and then column and then the direction (H or V). ");
            int row = in.nextInt(), col = in.nextInt();
            String inpOrientation = in.next();
            Orientation orientation = inpOrientation.equals("H") ? Orientation.Horizontal : Orientation.Vertical;
            while (!mainGame.placeShip(i, row, col, orientation)) {
                System.out.println("Invalid. ");
                System.out.println("Please enter the location of the " + Ship.getShipName.get(i));
                System.out.println("Enter row and then column and then the direction (H or V). ");
                row = in.nextInt();
                col = in.nextInt();
                inpOrientation = in.next();
                orientation = inpOrientation.equals("H") ? Orientation.Horizontal : Orientation.Vertical;
            }
        }


        in.close();
    }
}