package com.battleshipics4u.game.menuControllers;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PlayerBoardSetController {

    public enum ShipNames {
        Carrier, Battleship, Destroyer, Cruiser, Submarine
    }

    public ImageView carrierImgView;
    public ImageView battleshipImgView;
    public ImageView destroyerImgView;
    public ImageView cruiserImgView;
    public ImageView submarineImgView;

    public void selectShip(MouseEvent mouseEvent) {
        ShipNames selectedShip = ShipNames.Carrier;
    boolean moveBattleship = false;
    boolean moveDestroyer = false;
    boolean moveCruiser = false;
    boolean moveSub = false;


        switch (selectedShip) {
            case Carrier:
                break;
            case Battleship:
                break;
            case Destroyer:
                break;
            case Cruiser:
                break;
            case Submarine:
                break;

        }

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
