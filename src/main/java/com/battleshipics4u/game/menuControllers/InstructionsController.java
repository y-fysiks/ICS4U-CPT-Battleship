package com.battleshipics4u.game.menuControllers;

import javafx.event.ActionEvent;
import com.battleshipics4u.game.MainApplication;

/**
 * @author Sami
 * Instruction controller class
 * Holds all methods related to that screen
 */
public class InstructionsController {

    /**
     * Takes player back to the start screen
     * @param actionEvent when the back button is pressed
     */
    public void back(ActionEvent actionEvent) {
        MainApplication.startMenu.showMenu();
    }
}
