package com.battleshipics4u.game.menuControllers;

import com.battleshipics4u.game.MainApplication;
import com.battleshipics4u.game.ships.Orientation;
import com.battleshipics4u.game.ships.Ship;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * @author Yubo Wang
 * Controller for the player board setting process.
 */
public class PlayerBoardSetController {

    public GridPane Board;
    public GridPane shipList;
    public AnchorPane backgroundPane;
    public Label placeShipsMessage; // message that tells the user they need to finish placing the ships
    private final ImageView[] images = new ImageView[5];
    private Pane[][] panes;

    //defines which ship the player should be moving
    private int moveShipId = -1; //-1 means none, other values correspond to each of five ships

    /**
     * Initializes all the variables and sets up the scene. Called when the fxml file is loaded.
     */
    @FXML
    public void initialize() {
        placeShipsMessage.setVisible(false); // hide the message until it is needed

        //creates and adds a pane to each location on the board so that we can set the ActionEvents
        panes = new Pane[Board.getColumnCount()][Board.getRowCount()];
        for (int i = 0; i < Board.getColumnCount(); i++) {
            for (int j = 0; j < Board.getRowCount(); j++) {
                Pane cell = new Pane();
                Board.add(cell, i, j);
                int finalI = i;
                int finalJ = j;
                // set the Event using a lambda
                cell.setOnMousePressed(e -> {
                    if (e.isPrimaryButtonDown()) { // if the primary mouse button is clicked, place the ship.
                        gridClicked(finalI, finalJ);
                    } else { // otherwise, the user has right-clicked, so rotate the ship
                        rightClick(e);
                    }
                });
                panes[i][j] = cell;
            }
        }
        // displays the ships
        for (Ship s : MainApplication.mainGame.player.shipList) {
            int i = s.shipIdx;
            images[i] = new ImageView(s.img);
            images[i].setFitHeight(s.getPixelsLength());
            images[i].setFitWidth(s.getPixelsWidth());
            images[i].setTranslateX(shipList.getPrefWidth() / 2);
            images[i].setRotate(-90);
            shipList.add(images[i], 0, i);
            images[i].setOnMouseClicked(e -> { // set the Event when a user clicks a ship using a lambda
                if (moveShipId == -1) { // if no other ships are being moved, then make it follow the mouse.
                    s.activate();
                    moveShipId = i;
                    ImageView targetImg = images[moveShipId];
                    targetImg.setMouseTransparent(true);
                    shipList.getChildren().remove(targetImg);
                    targetImg.setTranslateX(0);
                    targetImg.setTranslateY(0);
                    backgroundPane.getChildren().add(targetImg);
                    move(e);
                }
            });
        }
    }

    /**
     * Called when the user clicks the "back" button.
     * @param ignoredActionEvent not used but is necessary to not throw an error
     */
    public void back(ActionEvent ignoredActionEvent) {
        if (moveShipId == -1) MainApplication.startMenu.showMenu(); // prevents issues with holding the ship and clicking
    }

    /**
     * Sets a Ship's x and y coordinates, shows it in the right position, and activates it after being called.
     * @param x the x coordinate to place the ship at
     * @param y the y coordinate the place the ship at
     */
    public void gridClicked(int x, int y) {
        if (moveShipId == -1) return;
        ImageView targetImg = images[moveShipId];

        //change the actual saved ship location in the GameBoard
        boolean success;
        if (targetImg.getRotate() == 0) success = MainApplication.mainGame.placeShip(moveShipId, y, x, Orientation.Vertical);
        else success = MainApplication.mainGame.placeShip(moveShipId, y, x, Orientation.Horizontal);
        if (!success) {
            return;
        }

        //display on new location on screen
        backgroundPane.getChildren().remove(targetImg);
        panes[x][y].getChildren().add(targetImg);

        targetImg.setLayoutX(0);
        targetImg.setLayoutY(0);

        Ship curShip = MainApplication.mainGame.player.shipList.get(moveShipId);
        // center the image
        int translateX = (75 - curShip.getPixelsWidth()) / 2;
        int translateY = (curShip.getShipLength() * 75 - curShip.getPixelsLength()) / 2;
        // handle case where ship is horizontal
        if (targetImg.getRotate() == -90) {
            int temp = translateX;
            //noinspection SuspiciousNameCombination suppress the warning
            translateX = translateY;
            translateY = temp;

            translateX += (targetImg.getFitHeight() / 2.0) - (targetImg.getFitWidth() / 2.0);
            translateY += (targetImg.getFitWidth() / 2.0) - (targetImg.getFitHeight() / 2.0);
        }

        //set the ImageView's position so that it shows up on the screen
        targetImg.setTranslateX(translateX);
        targetImg.setTranslateY(translateY);
        //reset the moveShipId to be invalid because we are no longer holding a ship
        moveShipId = -1;
    }

    /**
     * Called automatically whenever the mouse is moved
     * @param mouseEvent The MouseEvent of the movement
     */
    public void move(MouseEvent mouseEvent) {
        if (moveShipId == -1) return;
        ImageView targetImg = images[moveShipId];
        double x, y;
        //set the correct x and y coordinates and handle the rotated case
        if (targetImg.getRotate() == 0) {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        } else {
            x = mouseEvent.getSceneX() + (targetImg.getFitHeight() / 2.0) - (targetImg.getFitWidth() / 2.0);
            y = mouseEvent.getSceneY() + (targetImg.getFitWidth() / 2.0) - (targetImg.getFitHeight() / 2.0);
        }
        x -= (targetImg.getFitWidth() / 2);
        y -= (targetImg.getFitWidth() / 2);
        targetImg.setLayoutX(x);
        targetImg.setLayoutY(y);

    }

    /**
     * called when the mouse is right-clicked to rotate the ship.
     * @param mouseEvent the MouseEvent corresponding to the right click
     */
    public void rightClick(MouseEvent mouseEvent) {
        if (moveShipId != -1) {
            ImageView targetImg = images[moveShipId];
            if (targetImg.getRotate() == -90) {
                targetImg.setRotate(0);
            } else {
                targetImg.setRotate(-90);
            }
        }
        move(mouseEvent);
    }

    /**
     * Called when the user clicks the "reset button".
     * @param ignoredActionEvent not used but is necessary to not throw an error
     */
    public void reset(ActionEvent ignoredActionEvent) {
        if (moveShipId != -1) return; // do not do anything if the user is already holding a ship.
        int i = 0;
        for (Ship s : MainApplication.mainGame.player.shipList) {
            if (s.getActivation()) {
                s.deactivate();
                if (s.getPosition(1) > 0 && s.getPosition(0) > 0) { // if item was already placed on board
                    // remove the image from the board so we can move it back to original GridPane
                    panes[s.getPosition(1)][s.getPosition(0)].getChildren().remove(images[i]);
                }

                s.setPosition(-10, -10, Orientation.Vertical);
                images[i].setTranslateX(shipList.getPrefWidth() / 2);
                images[i].setTranslateY(0);
                images[i].setLayoutX(0);
                images[i].setLayoutY(0);
                images[i].setRotate(-90);
                //add the ship back into the ship list GridPane
                shipList.add(images[i], 0, i);

                // allow the image to be clickable again
                images[i].setMouseTransparent(false);
            }
            i++;
        }
    }

    /**
     * called when the user clicks the "Done!" button.
     * @param ignoredActionEvent not used but is necessary to not throw an error
     */
    public void donePlacements(ActionEvent ignoredActionEvent) {
        boolean allPlaced = true;
        for (Ship s : MainApplication.mainGame.player.shipList) {
            if (!s.getActivation()) {
                allPlaced = false;
                break;
            }
        }
        if (allPlaced) {
            //go to the next scene
            MainApplication.mainGame.generateEnemyShipPlacements();
            MainApplication.mainGameplayMenu.showMenu();
        } else {
            placeShipsMessage.setVisible(true);
        }
    }
}
