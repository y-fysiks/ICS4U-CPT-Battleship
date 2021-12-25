package com.battleshipics4u.game.menuControllers;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PlayerBoardSetController {

    public ImageView carrierImgView;
    public ImageView battleshipImgView;
    public ImageView destroyerImgView;
    public ImageView cruiserImgView;
    public ImageView submarineImgView;
    boolean moveCarrier = false;

    public void mouseClickedCarrier(MouseEvent mouseEvent) {
        moveCarrier = !moveCarrier;
    }

    public void move(MouseEvent mouseEvent) {
        if (moveCarrier) {
            carrierImgView.setX(mouseEvent.getX() - 36);
            carrierImgView.setY(mouseEvent.getY() - 30);
        }

    }
}
