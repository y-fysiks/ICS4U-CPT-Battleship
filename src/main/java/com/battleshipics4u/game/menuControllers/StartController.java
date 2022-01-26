package com.battleshipics4u.game.menuControllers;

import com.battleshipics4u.game.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Font;

/**
 * Controller for the start menu
 * @author Sami
 */
public class StartController {

    /**
     * Goes to the player board set menu
     * @param actionEvent start game button is pressed
     */
    @FXML
    public void onStartButtonClick(ActionEvent actionEvent) {
        MainApplication.playerBoardSetMenu.showMenu();
    }

    /**
     *
     * @param actionEvent
     */
    @FXML
    public void onHelpButtonClick(ActionEvent actionEvent) {
        MainApplication.instructionMenu.showMenu();
    }

    @FXML
    public void onExitButtonClick(ActionEvent actionEvent) {
        System.exit(0);
    }
}
