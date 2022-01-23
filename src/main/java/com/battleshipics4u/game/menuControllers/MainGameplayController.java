package com.battleshipics4u.game.menuControllers;

import com.battleshipics4u.game.MainApplication;
import com.battleshipics4u.game.Shot;
import com.battleshipics4u.game.ships.Orientation;
import com.battleshipics4u.game.ships.Ship;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.io.*;
import java.util.Objects;

public class MainGameplayController {
    public GridPane playerBoard;
    public GridPane enemyBoard;
    public ImageView crossHairs;
    public AnchorPane backgroundPane;
    public Label InfoLabelPlayer;
    public Label InfoLabelEnemy;
    private Pane[][] enemyPanes;
    private Pane[][] playerPanes;

    private Image fireImg;
    private Image splashImg;

    int fireX, fireY;

    @FXML
    public void initialize() {
        InputStream crossHairInp = getClass().getResourceAsStream("/com/battleshipics4u/game/target.png");
        if (crossHairInp == null) {
            System.out.println("Error: image not found");
            crossHairs = new ImageView();
        }
        else crossHairs = new ImageView(new Image(crossHairInp));

        try {
            fireImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/battleshipics4u/game/fire.png")));
        } catch (NullPointerException e) {
            System.out.println("Error: image not found");
        }

        try {
            splashImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/battleshipics4u/game/splash.png")));
        } catch (NullPointerException e) {
            System.out.println("Error: image not found");
        }


        crossHairs.setFitWidth(67.5);
        crossHairs.setFitHeight(67.5);
        crossHairs.setVisible(false);
        crossHairs.setMouseTransparent(true);

        backgroundPane.getChildren().add(crossHairs);

        enemyPanes = new Pane[enemyBoard.getColumnCount()][enemyBoard.getRowCount()];
        for (int i = 0; i < enemyBoard.getColumnCount(); i++) {
            for (int j = 0; j < enemyBoard.getRowCount(); j++) {
                Pane cell = new Pane();
                enemyBoard.add(cell, i, j);
                int finalI = i;
                int finalJ = j;
                cell.setOnMousePressed(e -> gridClicked(finalI, finalJ));
                enemyPanes[i][j] = cell;
            }
        }
        playerPanes = new Pane[playerBoard.getColumnCount()][playerBoard.getRowCount()];
        for (int i = 0; i < playerBoard.getColumnCount(); i++) {
            for (int j = 0; j < playerBoard.getRowCount(); j++) {
                Pane cell = new Pane();
                playerBoard.add(cell, i, j);
                playerPanes[i][j] = cell;
            }
        }
        for (Ship s : MainApplication.mainGame.player.shipList) {
            int x = s.getPosition(1);
            int y = s.getPosition(0);
            ImageView imgView = new ImageView(s.img);
            imgView.setFitHeight(s.getPixelsLength() * 0.9);
            imgView.setFitWidth(s.getPixelsWidth() * 0.9);

            playerPanes[x][y].getChildren().add(imgView);

            imgView.setLayoutX(0);
            imgView.setLayoutY(0);

            double translateX = (67.5 - s.getPixelsWidth() * 0.9) / 2;
            double translateY = (s.getShipLength() * 67.5 - s.getPixelsLength() * 0.9) / 2;
            if (s.getOrientation() == Orientation.Horizontal) {
                imgView.setRotate(-90);
                double temp = translateX;
                //noinspection SuspiciousNameCombination suppress the warning
                translateX = translateY;
                translateY = temp;

                translateX += (imgView.getFitHeight() / 2.0) - (imgView.getFitWidth() / 2.0);
                translateY += (imgView.getFitWidth() / 2.0) - (imgView.getFitHeight() / 2.0);
            }

            imgView.setTranslateX(translateX);
            imgView.setTranslateY(translateY);
        }

        for (Ship s : MainApplication.mainGame.enemy.shipList) {
            int x = s.getPosition(1);
            int y = s.getPosition(0);
            ImageView imgView = new ImageView(s.img);
            imgView.setFitHeight(s.getPixelsLength() * 0.9);
            imgView.setFitWidth(s.getPixelsWidth() * 0.9);

            enemyPanes[x][y].getChildren().add(imgView);

            imgView.setLayoutX(0);
            imgView.setLayoutY(0);

            double translateX = (67.5 - s.getPixelsWidth() * 0.9) / 2;
            double translateY = (s.getShipLength() * 67.5 - s.getPixelsLength() * 0.9) / 2;
            if (s.getOrientation() == Orientation.Horizontal) {
                imgView.setRotate(-90);
                double temp = translateX;
                //noinspection SuspiciousNameCombination suppress the warning
                translateX = translateY;
                translateY = temp;

                translateX += (imgView.getFitHeight() / 2.0) - (imgView.getFitWidth() / 2.0);
                translateY += (imgView.getFitWidth() / 2.0) - (imgView.getFitHeight() / 2.0);
            }

            imgView.setTranslateX(translateX);
            imgView.setTranslateY(translateY);

            imgView.setVisible(false);
        }

    }

    private void gridClicked(int x, int y) {
        if (!MainApplication.mainGame.getTurn()) return;
        if (MainApplication.mainGame.checkValidPlayerShot(x, y)) {
            fireX = x;
            fireY = y;
            for (int i = 0; i < enemyBoard.getColumnCount(); i++) {
                for (int j = 0; j < enemyBoard.getRowCount(); j++) {
                    enemyPanes[i][j].setStyle("");
                }
            }
            enemyPanes[x][y].setStyle("-fx-background-color: #FF4B00");
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


    public void onFireButtonClicked(ActionEvent actionEvent) throws Exception{
        if (fireX < 0 && fireY < 0) return;
        Shot shot = new Shot(fireX, fireY);
        if (MainApplication.mainGame.takePlayerShot(shot)) {
            System.out.println("You hit a ship");
            ImageView fire = new ImageView(fireImg);
            fire.setFitWidth(47.5);
            fire.setFitHeight(47.5);
            fire.setVisible(true);
            fire.setTranslateX(10);
            fire.setTranslateY(10);

            enemyPanes[fireX][fireY].getChildren().add(fire);
            enemyPanes[fireX][fireY].setStyle("");

        } else {
            System.out.println("You missed ");
            ImageView splash = new ImageView(splashImg);
            splash.setFitWidth(47.5);
            splash.setFitHeight(47.5);
            splash.setVisible(true);
            splash.setTranslateX(10);
            splash.setTranslateY(10);

            enemyPanes[fireX][fireY].getChildren().add(splash);
            enemyPanes[fireX][fireY].setStyle("");
        }
        fireX = -1;
        fireY = -1;
        //check if any of the enemy's ships have been sunk and if the player has won
        int lastSunkIdx = MainApplication.mainGame.enemy.getLastSunkIdx();
        if (lastSunkIdx != -1) {
            //ship was just sunk by last move
            InfoLabelPlayer.setText("You sunk the enemy's " + Ship.getShipName.get(lastSunkIdx));
        }
        if (MainApplication.mainGame.checkPlayerWon()) {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("Didn't work");
            }
            MainApplication.endMenu.showMenu(true);
            return;
        }

        //make a delay for a few seconds
        PauseTransition delayAfterPlayerTurn = new PauseTransition(Duration.seconds(0.6));//delay for 0.6 seconds after the player goes, and then let the computer go
        delayAfterPlayerTurn.setOnFinished(e -> {
            try {
                computerTurn();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        delayAfterPlayerTurn.play();


    }

    public void computerTurn() throws Exception{
        //let the computer take a turn
        Shot enemyShot = MainApplication.mainGame.enemyTurn.generateNextTurn(MainApplication.mainGame.player);
        if (MainApplication.mainGame.takeEnemyShot(enemyShot)) {
            ImageView fire = new ImageView(fireImg);
            fire.setFitWidth(47.5);
            fire.setFitHeight(47.5);
            fire.setVisible(true);
            fire.setTranslateX(10);
            fire.setTranslateY(10);

            playerPanes[enemyShot.getX()][enemyShot.getY()].getChildren().add(fire);

            int lastSunkIdx = MainApplication.mainGame.player.getLastSunkIdx();
            if (lastSunkIdx != -1) {
                //ship was just sunk by last computer move ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ FOR MAKING TEXT APPEAR ONLY FOR TWO TURNS
                InfoLabelEnemy.setText("The Enemy sunk your " + Ship.getShipName.get(lastSunkIdx));
            }
        } else {
            ImageView splash = new ImageView(splashImg);
            splash.setFitWidth(47.5);
            splash.setFitHeight(47.5);
            splash.setVisible(true);
            splash.setTranslateX(10);
            splash.setTranslateY(10);

            playerPanes[enemyShot.getX()][enemyShot.getY()].getChildren().add(splash);
        }

        if (MainApplication.mainGame.checkEnemyWon()) {
            //THIS MEANS THE ENEMY HAS SUNK ALL THE PLAYER SHIPS AND HAS WON
            try {
                Thread.sleep(4000);
            } catch (Exception e) {
                System.out.println("Didn't work");
            }
            MainApplication.endMenu.showMenu(false);
        }
    }

    public void generateRandom(ActionEvent actionEvent) {
        Shot randomShot = MainApplication.mainGame.generateRandomPlayerShot();
        gridClicked(randomShot.getX(), randomShot.getY());
    }
}
