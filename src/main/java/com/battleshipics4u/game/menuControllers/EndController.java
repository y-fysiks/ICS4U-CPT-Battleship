package com.battleshipics4u.game.menuControllers;

import com.battleshipics4u.game.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EndController {

    public Label winLossText;

    @FXML
    public void initialize() {
        if (MainApplication.endMenu.getPlayerWin()) winLossText.setText("You Won!");
        else winLossText.setText("You Lost!");
    }

    public void quitGame(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void playAgain(ActionEvent actionEvent) {
        MainApplication.initEverything();
        MainApplication.startMenu.showMenu();
    }
}
