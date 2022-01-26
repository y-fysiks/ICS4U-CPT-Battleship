package com.battleshipics4u.game.menuControllers;

import com.battleshipics4u.game.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * End screen controller class
 * All methods used on that screen are here
 */
public class EndController {
    public Label winLossText; //holds the text that will display whether the player won or lost

    /**
     * Initializes whatever needs to be initialized
     */
    @FXML
    public void initialize() {
        if (MainApplication.endMenu.getPlayerWin()) winLossText.setText("You Won!");
        else winLossText.setText("You Lost!");
    }

    /**
     * Quits the game
     * @param actionEvent when quit game button is pressed
     */
    public void quitGame(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * Takes player back to start screen
     * @param actionEvent when play again button is pressed
     */
    public void playAgain(ActionEvent actionEvent) {
        MainApplication.initEverything();
        MainApplication.startMenu.showMenu();
    }
}
