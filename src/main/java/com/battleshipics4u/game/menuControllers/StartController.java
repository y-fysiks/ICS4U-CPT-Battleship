package com.battleshipics4u.game.menuControllers;

import com.battleshipics4u.game.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Font;

/**
 * @author Sami
 */
public class StartController {
    
    @FXML
    public void onStartButtonClick(ActionEvent actionEvent) {
        MainApplication.playerBoardSetMenu.showMenu();
    }

    @FXML
    public void onHelpButtonClick(ActionEvent actionEvent) {
        MainApplication.instructionMenu.showMenu();
    }

    @FXML
    public void onExitButtonClick(ActionEvent actionEvent) {
        System.exit(0);
    }
}
