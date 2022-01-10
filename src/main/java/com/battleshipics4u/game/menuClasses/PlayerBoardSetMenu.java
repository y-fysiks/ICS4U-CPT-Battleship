package com.battleshipics4u.game.menuClasses;

import java.io.IOException;

import com.battleshipics4u.game.MainApplication;
import com.battleshipics4u.game.menuControllers.PlayerBoardSetController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import com.battleshipics4u.game.menuControllers.PlayerBoardSetController;

public class PlayerBoardSetMenu extends Menu{

    /**
     * The constructor for the menu abstract class. This will be called by the subclasses to set up the menu.
     *
     * @throws IOException if the fxml file cannot be found.
     */
    public PlayerBoardSetMenu() throws IOException {
        super("playerBoardSet-menu.fxml");
    }

}
