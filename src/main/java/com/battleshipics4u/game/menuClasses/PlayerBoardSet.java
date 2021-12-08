package com.battleshipics4u.game.menuClasses;

import java.io.IOException;
import javafx.scene.shape.Rectangle;

public class PlayerBoardSet extends Menu{
    public final Rectangle[][] board = new Rectangle[8][8];
    private final double squareLength = 30;

    /**
     * The constructor for the menu abstract class. This will be called by the subclasses to set up the menu.
     *
     * @throws IOException if the fxml file cannot be found.
     */
    public PlayerBoardSet() throws IOException {
        super("playerBoardSet-menu.fxml");
    }

    private void addSquares() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Rectangle square = new Rectangle (squareLength, squareLength);

            }
        }
    }

}
