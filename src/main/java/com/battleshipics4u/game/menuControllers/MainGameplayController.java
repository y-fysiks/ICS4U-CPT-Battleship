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
import java.io.InputStream;

/**
 * @author Yubo Wang
 * Controller for the main gameplay menu
 */
public class MainGameplayController {
    public GridPane playerBoard; // a GridPane that shows the player's board
    public GridPane enemyBoard; // a GridPane that shows the enemy's board
    public ImageView crossHairs;
    public AnchorPane backgroundPane; // the background
    public Label InfoLabelPlayer; // displays info about the player's ships
    public Label InfoLabelEnemy; // displays info about the enemy's ships
    private Pane[][] enemyPanes; // array of panes, so we can display images onto each pane of the enemy board
    private Pane[][] playerPanes; // array of panes, so we can display images onto each pane of the player board
    private Image fireImg;
    private Image splashImg;
    private ImageView[] enemyShipImgViews; // array to store the enemy ship ImageViews so that we can set them to visible later

    int fireX, fireY;

    /**
     * Initializes all the variables and sets up the scene. Called when the fxml file is loaded.
     */
    @FXML
    public void initialize() {
        //Load the images required
        InputStream crossHairInp = getClass().getResourceAsStream("/com/battleshipics4u/game/target.png");
        if (crossHairInp == null) {
            System.out.println("Error: image not found");
            crossHairs = new ImageView();
        }
        else crossHairs = new ImageView(new Image(crossHairInp)); // directly create imageview because we only need one cross-hair

        InputStream fireInp = getClass().getResourceAsStream("/com/battleshipics4u/game/fire.png");
        if (fireInp == null) {
            System.out.println("Error: image not found");
        }
        else fireImg = new Image(fireInp); // read the image into an Image class, because we need to create multiple ImageViews

        InputStream splashInp = getClass().getResourceAsStream("/com/battleshipics4u/game/splash.png");
        if (splashInp == null) {
            System.out.println("Error: image not found");
        }
        else splashImg = new Image(splashInp); // read the image into an Image class, because we need to create multiple ImageViews


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

        enemyShipImgViews = new ImageView[MainApplication.mainGame.enemy.shipList.size()]; // initialize the array
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

            enemyShipImgViews[s.shipIdx] = imgView; // add the imageview to the array
        }

    }

    /**
     * Highlights the coordinate on the enemy grid that the player clicked
     * @param x
     * @param y
     */
    private void gridClicked(int x, int y) {
        if (!MainApplication.mainGame.getTurn()) return; // if not the player turn then they can't click the grid
        if (MainApplication.mainGame.checkValidPlayerShot(x, y)) {
            fireX = x;
            fireY = y;
            for (int i = 0; i < enemyBoard.getColumnCount(); i++) {
                for (int j = 0; j < enemyBoard.getRowCount(); j++) {
                    enemyPanes[i][j].setStyle("");
                }
            }
            enemyPanes[x][y].setStyle("-fx-background-color: #FF4B00"); // sets the square clicked orange
        }
    }

    /**
     * Makes a crosshair follow the cursor when it's the player's turn
     * @param mouseEvent
     */
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

    /**
     * Fires a shot when the fire button is clicked
     * @param ignoredActionEvent the fire button being clicked
     */
    public void onFireButtonClicked(ActionEvent ignoredActionEvent) {
        if (fireX < 0 && fireY < 0) return;
        Shot shot = new Shot(fireX, fireY); //creates a new shot
        if (MainApplication.mainGame.takePlayerShot(shot)) { // if the player's shot has hit an enemy ship
            ImageView fire = new ImageView(fireImg); //displays the fire image where the player shot
            fire.setFitWidth(47.5);
            fire.setFitHeight(47.5);
            fire.setTranslateX(10);
            fire.setTranslateY(10);
            fire.setVisible(true);

            enemyPanes[fireX][fireY].getChildren().add(fire);
            enemyPanes[fireX][fireY].setStyle("");

        } else { // if the player's shot has missed an enemy ship
            ImageView splash = new ImageView(splashImg); //displays the splash image where the player shot
            splash.setFitWidth(47.5);
            splash.setFitHeight(47.5);
            splash.setTranslateX(10);
            splash.setTranslateY(10);
            splash.setVisible(true);

            enemyPanes[fireX][fireY].getChildren().add(splash);
            enemyPanes[fireX][fireY].setStyle("");
        }
        // reset the player's firing coordinates to invalid
        fireX = -1;
        fireY = -1;
        //check if any of the enemy's ships have been sunk and if the player has won
        int lastSunkIdx = MainApplication.mainGame.enemy.getLastSunkIdx(); // gets the index of the last sunk ship
        if (lastSunkIdx != -1) { //ship was just sunk by last move
            InfoLabelPlayer.setText("You sunk the enemy's " + Ship.getShipName.get(lastSunkIdx));
            //set the sunk ship as visible
            enemyShipImgViews[lastSunkIdx].setVisible(true);
        } else { // nothing was sunk, so clear the info label
            InfoLabelPlayer.setText("");
        }
        if (MainApplication.mainGame.checkPlayerWon()) {
            PauseTransition delayAfterWin = new PauseTransition(Duration.seconds(1));//delay for 1 second after the player wins
            delayAfterWin.setOnFinished(e -> MainApplication.endMenu.showMenu(true));
            delayAfterWin.play();
            return;
        }

        //make a delay for a few seconds
        PauseTransition delayAfterPlayerTurn = new PauseTransition(Duration.seconds(0.5));//delay for 0.5 seconds after the player goes, and then let the computer go
        delayAfterPlayerTurn.setOnFinished(e -> {
            try {
                computerTurn();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        delayAfterPlayerTurn.play();
    }

    /**
     * Lets the computer fire a shot
     */
    public void computerTurn() {
        //let the computer take a turn
        Shot enemyShot = MainApplication.mainGame.enemyTurn.generateNextTurn(MainApplication.mainGame.player);
        System.out.println(enemyShot.getX() + " " + enemyShot.getY());
        if (MainApplication.mainGame.takeEnemyShot(enemyShot)) { //if computer hit
            ImageView fire = new ImageView(fireImg); //displays the fire image where the computer shot
            fire.setFitWidth(47.5);
            fire.setFitHeight(47.5);
            fire.setVisible(true);
            fire.setTranslateX(10);
            fire.setTranslateY(10);

            playerPanes[enemyShot.getX()][enemyShot.getY()].getChildren().add(fire);

            int lastSunkIdx = MainApplication.mainGame.player.getLastSunkIdx();
            if (lastSunkIdx != -1) {
                //ship was just sunk by last computer move
                InfoLabelEnemy.setText("The Enemy sunk your " + Ship.getShipName.get(lastSunkIdx));
            } else {
                InfoLabelEnemy.setText("");
            }
        } else { //missed
            ImageView splash = new ImageView(splashImg); //displays the splash image where the computer shot
            splash.setFitWidth(47.5);
            splash.setFitHeight(47.5);
            splash.setVisible(true);
            splash.setTranslateX(10);
            splash.setTranslateY(10);

            playerPanes[enemyShot.getX()][enemyShot.getY()].getChildren().add(splash);
        }

        if (MainApplication.mainGame.checkEnemyWon()) {
            //THIS MEANS THE ENEMY HAS SUNK ALL THE PLAYER SHIPS AND HAS WON
            PauseTransition delayAfterWin = new PauseTransition(Duration.seconds(1));//delay for 1 seconds after the enemy wins
            delayAfterWin.setOnFinished(e -> MainApplication.endMenu.showMenu(false));
            delayAfterWin.play();
        }
    }

    /**
     * Generates a random shot for the player
     * @param ignoredActionEvent the random shot button being clicked
     */
    public void generateRandom(ActionEvent ignoredActionEvent) {
        Shot randomShot = MainApplication.mainGame.generateRandomPlayerShot();
        gridClicked(randomShot.getX(), randomShot.getY());
    }
}
