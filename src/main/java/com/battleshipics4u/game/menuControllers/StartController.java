package com.battleshipics4u.game.menuControllers;

import com.battleshipics4u.game.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class StartController {
    
    @FXML
    public void onStartButtonClick(ActionEvent actionEvent) {
        //start the game

    }

    @FXML
    public void onInsButtonClick(ActionEvent actionEvent) {
        MainApplication.help.showMenu();
    }

    public void onExitButtonClick(ActionEvent actionEvent) {
        System.exit(0);
    }
}
