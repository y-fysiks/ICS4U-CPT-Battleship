package com.battleshipics4u.game.menuClasses;

import java.io.IOException;

import com.battleshipics4u.game.MainApplication;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import com.battleshipics4u.game.menuControllers.PlayerBoardSetController;

public class PlayerBoardSet extends Menu{
    Image carrier = new Image(String.valueOf(MainApplication.class.getResource("carrier.png")));
//    Image battleship = new Image("battleship.png");
//    Image destroyer = new Image("destroyer.png");
//    Image submarine = new Image("submarine.png");
//    Image cruiser = new Image("cruiser.png");



    /**
     * The constructor for the menu abstract class. This will be called by the subclasses to set up the menu.
     *
     * @throws IOException if the fxml file cannot be found.
     */
    public PlayerBoardSet() throws IOException {
        super("playerBoardSet-menu.fxml");
        //PlayerBoardSetController.carrierImgView = new ImageView(carrier);
    }

//    private void addSquares() {
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                Rectangle square = new Rectangle (squareLength, squareLength);
//
//            }
//        }
//    }

}
