package com.battleshipics4u.game.menuControllers;

import javafx.event.ActionEvent;
import com.battleshipics4u.game.MainApplication;

public class InstructionsController {

    public void back(ActionEvent actionEvent) {
        MainApplication.start.showMenu();
    }
}
