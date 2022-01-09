package com.battleshipics4u.game.menuControllers;

import com.battleshipics4u.game.MainApplication;
import com.battleshipics4u.game.ships.Orientation;
import com.battleshipics4u.game.ships.Ship;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * @author Yubo Wang
 */
public class PlayerBoardSetController {

    public GridPane Board;
    public GridPane shipList;
    public AnchorPane backgroundPane;
    private final ImageView[] images = new ImageView[5];
    private Pane[][] panes;

    int moveShipId = -1; //-1 means none, other values correspond to each of five ships

    @FXML
    public void initialize() {
        panes = new Pane[Board.getColumnCount()][Board.getRowCount()];
        for (int i = 0; i < Board.getColumnCount(); i++) {
            for (int j = 0; j < Board.getRowCount(); j++) {
                Pane cell = new Pane();
                Board.add(cell, i, j);
                int finalI = i;
                int finalJ = j;
                cell.setOnMousePressed(e -> {
                    if (e.isPrimaryButtonDown()) {
                        gridClicked(finalI, finalJ);
                    } else {
                        rightClick(e);
                    }
                });
                panes[i][j] = cell;
            }
        }
        int i = 0;
        for (Ship s : MainApplication.mainGame.player.shipList) {
            images[i] = new ImageView(s.img);
            images[i].setFitHeight(s.getPixelsLength());
            images[i].setFitWidth(s.getPixelsWidth());
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
        if (moveShipId == -1) return;
        ImageView targetImg = images[moveShipId];

        boolean success;
        if (targetImg.getRotate() == 0) success = MainApplication.mainGame.placeShip(moveShipId, y, x, Orientation.Vertical);
        else success = MainApplication.mainGame.placeShip(moveShipId, y, x, Orientation.Horizontal);
        if (!success) {
            System.out.println("ship already at location. try again. ");
            return;
        }
        System.out.println("Ship placed at " + x + " " + y);

        backgroundPane.getChildren().remove(targetImg);
        panes[x][y].getChildren().add(targetImg);
        targetImg.setX(0);
        targetImg.setY(0);
        targetImg.setLayoutX(0);
        targetImg.setLayoutY(0);

        Ship curShip = MainApplication.mainGame.player.shipList.get(moveShipId);
        int translateX = (75 - curShip.getPixelsWidth()) / 2;
        int translateY = (curShip.getShipLength() * 75 - curShip.getPixelsLength()) / 2;
        if (targetImg.getRotate() == -90) {
            int temp = translateX;
            //noinspection SuspiciousNameCombination suppress the warning
            translateX = translateY;
            translateY = temp;

            translateX += (targetImg.getFitHeight() / 2.0) - (targetImg.getFitWidth() / 2.0);
            translateY += (targetImg.getFitWidth() / 2.0) - (targetImg.getFitHeight() / 2.0);
        }

        targetImg.setTranslateX(translateX);
        targetImg.setTranslateY(translateY);

        //change the actual saved ship location in the gameboard

        moveShipId = -1;
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

    public void rightClick(MouseEvent mouseEvent) {
        if (moveShipId != -1) {
            ImageView targetImg = images[moveShipId];
            if (targetImg.getRotate() == -90) {
                targetImg.setRotate(0);
            } else {
                targetImg.setRotate(-90);
            }
        }
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
}
