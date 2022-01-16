package com.battleshipics4u.game.menuControllers;

import com.battleshipics4u.game.MainApplication;
import com.battleshipics4u.game.Shot;
import com.battleshipics4u.game.ships.Ship;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.*;

public class MainGameplayController {
    public GridPane playerBoard;
    public GridPane enemyBoard;
    public ImageView crossHairs;
    public AnchorPane backgroundPane;
    public Label InfoLabel;
    private Pane[][] panes;

    private InputStream fireInp;
    private InputStream splashInp;

    int fireX, fireY;

    @FXML
    public void initialize() {
        InputStream crossHairInp = getClass().getResourceAsStream("/com/battleshipics4u/game/target.png");
        if (crossHairInp == null) {
            System.out.println("Error: image not found");
            crossHairs = new ImageView();
        }
        else crossHairs = new ImageView(new Image(crossHairInp));

        fireInp = getClass().getResourceAsStream("/com/battleshipics4u/game/fire.png");
        if (fireInp == null) {
            System.out.println("Error: image not found");
        }
        splashInp = getClass().getResourceAsStream("/com/battleshipics4u/game/fire.png");
        if (fireInp == null) {
            System.out.println("Error: image not found");
        }

        crossHairs.setFitWidth(67.5);
        crossHairs.setFitHeight(67.5);
        crossHairs.setVisible(false);
        crossHairs.setMouseTransparent(true);

        backgroundPane.getChildren().add(crossHairs);

        panes = new Pane[enemyBoard.getColumnCount()][enemyBoard.getRowCount()];
        for (int i = 0; i < enemyBoard.getColumnCount(); i++) {
            for (int j = 0; j < enemyBoard.getRowCount(); j++) {
                Pane cell = new Pane();
                //cell.setStyle("-fx-background-color: #0099ff");
                enemyBoard.add(cell, i, j);
                int finalI = i;
                int finalJ = j;
                cell.setOnMousePressed(e -> gridClicked(finalI, finalJ));
                panes[i][j] = cell;
            }
        }
    }

    private void gridClicked(int x, int y) {
        if (!MainApplication.mainGame.getTurn()) return;
        if (MainApplication.mainGame.checkValidPlayerShot(x, y)) {
            fireX = x;
            fireY = y;
            for (int i = 0; i < enemyBoard.getColumnCount(); i++) {
                for (int j = 0; j < enemyBoard.getRowCount(); j++) {
                    panes[i][j].setStyle("");
                }
            }
            panes[x][y].setStyle("-fx-background-color: #FF4B00");
        }
    }

    public void move(MouseEvent mouseEvent) {
        if (!MainApplication.mainGame.getTurn()) return;
        double x = mouseEvent.getSceneX();
        double y = mouseEvent.getSceneY();

        if (x >= enemyBoard.getLayoutX() && x <= enemyBoard.getLayoutX() + enemyBoard.getPrefWidth() &&
                y >= enemyBoard.getLayoutY() && y <= enemyBoard.getLayoutY() + enemyBoard.getPrefHeight()) {
            crossHairs.setVisible(true);
            crossHairs.setLayoutX(x - 67.5 / 2);
            crossHairs.setLayoutY(y - 67.5 / 2);
        } else {
            crossHairs.setVisible(false);
        }
    }


    public void onFireButtonClicked(ActionEvent actionEvent) {
        if (fireX < 0 && fireY < 0) return;
        Shot shot = new Shot(fireX, fireY);
        if (MainApplication.mainGame.takePlayerShot(shot)) {
            ImageView fire = new ImageView(new Image(fireInp));
            fire.setFitWidth(47.5);
            fire.setFitHeight(47.5);
            fire.setVisible(true);
            fire.setTranslateX(10);
            fire.setTranslateY(10);
            enemyBoard.add(fire, fireX, fireY);

            panes[fireX][fireY].setStyle("");

        } else {
            ImageView splash = new ImageView(new Image(splashInp));
            splash.setFitWidth(47.5);
            splash.setFitHeight(47.5);
            splash.setVisible(true);
            splash.setTranslateX(10);
            splash.setTranslateY(10);
            enemyBoard.add(splash, fireX, fireY);

            panes[fireX][fireY].setStyle("");
        }
        fireX = -1;
        fireY = -1;
        //check if any of the enemy's ships have been sunk and if the player has won
        int lastSunkIdx = MainApplication.mainGame.player.getLastSunkIdx();
        if (lastSunkIdx != -1) {
            //ship was just sunk by last move
            InfoLabel.setText("You sunk the " + Ship.getShipName.get(lastSunkIdx));
        }

        //let the computer take a turn
        Shot enemyShot = MainApplication.mainGame.enemyTurn.generateNextTurn(MainApplication.mainGame.player);
        if (MainApplication.mainGame.takeEnemyShot(enemyShot)) {
            System.out.println("hit");
        }



    }
}
