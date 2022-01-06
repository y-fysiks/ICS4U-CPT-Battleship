package com.battleshipics4u.game.menuControllers;

import com.battleshipics4u.game.MainApplication;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PlayerBoardSetController {

    public void back(ActionEvent actionEvent) {
        MainApplication.start.showMenu();
    }

    public enum ShipNames {
        Carrier, Battleship, Destroyer, Cruiser, Submarine
    }

    public ImageView carrierImgView;
    public ImageView battleshipImgView;
    public ImageView destroyerImgView;
    public ImageView cruiserImgView;
    public ImageView submarineImgView;

    boolean moveCarrier = false;
    boolean moveBattleship = false;
    boolean moveDestroyer = false;
    boolean moveCruiser = false;
    boolean moveSub = false;

    public void mouseClickedCarrier(MouseEvent mouseEvent) {
        moveCarrier = !moveCarrier;
    }

    public void mouseClickedBattleship(MouseEvent mouseEvent) {

    }

    public void mouseClickedDestroyer(MouseEvent mouseEvent) {
    }

    public void mouseClickedCruiser(MouseEvent mouseEvent) {
    }

    public void mouseClickedSub(MouseEvent mouseEvent) {

    }

    public void move(MouseEvent mouseEvent) {
        if (moveCarrier) {
            carrierImgView.setX(mouseEvent.getX() - 36);
            carrierImgView.setY(mouseEvent.getY() - 30);
        }

    }
}
