package com.battleshipics4u.game.menuControllers;

import com.battleshipics4u.game.GameStates;
import com.battleshipics4u.game.MainApplication;
import com.battleshipics4u.game.ships.Ship;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * @author Yubo Wang
 */
public class PlayerBoardSetController {
    public ImageView carrierImgView;
    public ImageView battleshipImgView;
    public ImageView destroyerImgView;
    public ImageView cruiserImgView;
    public ImageView submarineImgView;
    public GridPane Board;
    public GridPane shipList;
    public AnchorPane backgroundPane;
    private ImageView[] images = new ImageView[5];

    int moveShipId = -1; //-1 means none, other values correspond to each of five ships

    @FXML
    public void initialize() {
        for (int i = 0; i < Board.getColumnCount(); i++) {
            for (int j = 0; j < Board.getRowCount(); j++) {
                Pane cell = new Pane();
                Board.add(cell, i, j);
                int finalI = i;
                int finalJ = j;
                cell.setOnMouseClicked(e -> gridClicked(finalI, finalJ));
            }
        }
        int i = 0;
        for (Ship s : MainApplication.mainGame.player.shipList) {
            images[i] = new ImageView(s.img);
            images[i].setFitHeight(s.pixelsLength);
            images[i].setFitWidth(s.pixelsWidth);
            images[i].setTranslateX(shipList.getPrefWidth() / 2);
            images[i].setRotate(-90);
            shipList.add(images[i], 0, i);
            int finalI = i;//weird Java quirk, must set variable as final to use inside lambda
            images[i].setOnMouseClicked(e -> {
                System.out.println(s.shipName + " was clicked"); //for debugging
                moveShipId = finalI;
                ImageView targetImg = images[moveShipId];
                targetImg.setMouseTransparent(true);
                shipList.getChildren().remove(targetImg);
                targetImg.setLayoutX(0);
                targetImg.setLayoutY(0);
                targetImg.setTranslateX(0);
                targetImg.setTranslateY(0);
                backgroundPane.getChildren().add(targetImg);
                move(e);
            });
            i++;
        }

    }

    public void back(ActionEvent actionEvent) {
        MainApplication.start.showMenu();
    }

    public void gridClicked(int x, int y) {

    }


    public void move(MouseEvent mouseEvent) {
        if (moveShipId == -1) return;
        ImageView targetImg = images[moveShipId];
        double x, y;
        if (targetImg.getRotate() == 0) {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        } else {
            x = mouseEvent.getSceneX() + (targetImg.getFitHeight() / 2.0) - (targetImg.getFitWidth() / 2.0);
            y = mouseEvent.getSceneY() + (targetImg.getFitWidth() / 2.0) - (targetImg.getFitHeight() / 2.0);
        }
        x -= (targetImg.getFitWidth() / 2);
        y -= (targetImg.getFitWidth() / 2);
        targetImg.setX(x);
        targetImg.setY(y);

    }

    //TODO get image rotation working, it's not calling the key press action for some reason
    public void keyPress(KeyEvent keyEvent) {
        System.out.println("t");
        if (keyEvent.getCode().isWhitespaceKey()) {
            if (moveShipId != -1) {
                ImageView targetImg = images[moveShipId];
                if (targetImg.getRotate() == -90) {
                    targetImg.setRotate(0);
                } else {
                    targetImg.setRotate(-90);
                }
            }
        }
    }
}
