package com.battleshipics4u.game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//import custom classes
import com.battleshipics4u.game.menuClasses.*;

/**
 * @author Yubo Wang
 * The Main class. loads all the different menu classes and displays them.
 */
public class MainApplication extends Application {
    private static Stage stage;
    public static SplashScreen splashScreen;
    public static StartMenu startMenu;
    public static InstructionMenu instructionMenu;
    public static PlayerBoardSetMenu playerBoardSetMenu;
    public static MainGameplayMenu mainGameplayMenu;
    public static EndMenu endMenu;
    public static GameStates mainGame;

    /**
     * Overrides JavaFX Application default start() so we can initialize the game
     * @param primaryStage the stage which to display everything on, is handled automatically by JavaFX. no action needed.
     */
    @Override
    public void start(Stage primaryStage) {
        //load fonts
        // loads ALL the fonts of family agency-fb-bold, not just size 36
        Font.loadFont(getClass().getResourceAsStream("/com/battleshipics4u/game/fonts/agency-fb-bold.ttf" ), 36.0);

        //set the stage to the static variable so we can access it outside the Main class
        MainApplication.stage = primaryStage;
        stage.setTitle("Battleship");
        stage.setResizable(false);

        initEverything(); // initialize everything

        //show the splash screen and start menu
        stage.show();
        splashScreen.showMenu();
        startMenu.showMenu();
    }

    /**
     * Sets the scene specified to the stage
     * Used by the Menu classes to set the corresponding scene
     * @param scene a scene to be set on the stage
     */
    public static void setScene(Scene scene) {
        stage.setScene(scene);
    }

    /**
     * Initializes the entire game, including all the menus and the GameState
     * Can be used to reset the game to its starting state for replay
     */
    public static void initEverything() {
        //create GameState
        mainGame = new GameStates();

        //create all the menuClasses
        splashScreen = new SplashScreen();
        startMenu = new StartMenu();
        instructionMenu = new InstructionMenu();
        playerBoardSetMenu = new PlayerBoardSetMenu();
        mainGameplayMenu = new MainGameplayMenu();
        endMenu = new EndMenu();

    }

    public static void main(String[] args) {
        launch();
    }

}