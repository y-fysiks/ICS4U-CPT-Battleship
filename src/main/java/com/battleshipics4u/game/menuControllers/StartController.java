package com.battleshipics4u.game.menuControllers;

import com.battleshipics4u.game.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class StartController {
    
    @FXML
    public void onStartButtonClick(ActionEvent actionEvent) {
        MainApplication.playerBoardSetMenu.showMenu();
    }

    @FXML
    public void onHelpButtonClick(ActionEvent actionEvent) {
        MainApplication.instructionMenu.showMenu();
    }

    public void onExitButtonClick(ActionEvent actionEvent) {
        System.exit(0);
    }
}
