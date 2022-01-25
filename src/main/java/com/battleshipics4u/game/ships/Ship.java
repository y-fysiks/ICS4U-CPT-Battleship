package com.battleshipics4u.game.ships;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @author Daniel Bajenaru
 * Class for ships
 * Methods for checking and settings ship states
 */
public class Ship {
    public final String shipName;
    public final int shipIdx;
    private final int shipLength;
    private final int pixelsLength, pixelsWidth;
    public Image img;
    private Orientation shipOrientation = Orientation.Horizontal;
    private int shipRow = -10, shipColumn = -10; //left most and top most square of ship
    private boolean activated = false;
    private int countHits = 0;

    public static HashMap<String, Integer> getShipIdx = new HashMap<>();
    public static HashMap<Integer, String> getShipName = new HashMap<>();

    /**
     * Constructor sets ship name and length
     * @param name the name of the ship
     * @param length the length of the ship
     * @param pixelsLength length in pixels of ship
     * @param pixelsWidth width in pixels of ship
     * @param imageName filename of ship image
     */
    public Ship(String name, int length, int pixelsLength, int pixelsWidth, String imageName) {
        this.shipName = name;
        this.shipLength = length;
        this.pixelsLength = pixelsLength;
        this.pixelsWidth = pixelsWidth;
        shipIdx = Ship.getShipIdx.get(name);

        InputStream imgInp = getClass().getResourceAsStream("/com/battleshipics4u/game/" + imageName);
        if (imgInp == null) {
            System.out.println("Error: image not found");
        }
        else img = new Image(imgInp);

    }

    /**
     * Return Ship leftmost and topmost position
     * @return array of x and y position
     */
    public int[] getPosition() {
        return new int[]{shipRow, shipColumn};
    }

    /**
     * Gets a certain component of the position of the ship
     * @param component which component of the
     * @return a row or column coordinate of the ships position
     */
    public int getPosition(int component) { //component can be 0 for row, 1 for column
        int[] position = {shipRow, shipColumn};

        return position[component];
    }

    public Orientation getOrientation() {
        return shipOrientation;
    }

    /**
     * Sets the position of the ship
     * @param rowNum the row number of the position
     * @param columnNum the column number of the position
     */
    public void setPosition(int rowNum, int columnNum, Orientation or) {
        shipRow = rowNum;
        shipColumn = columnNum;
        shipOrientation = or;
    }

    /**
     * Checks if the ship is hit by a shot
     * @param shotRow the row number of the shot
     * @param shotColumn the column number of the shot
     * @return true if the ship is hit
     */
    public boolean checkHit(int shotRow, int shotColumn) {
        if (shipOrientation == Orientation.Vertical && shotColumn == shipColumn && shotRow >= shipRow && shotRow <= (shipRow + shipLength - 1)) { //checks if the shot is within the length of the ship for vertical orientation
            countHits++;
            return true;
        } else if (shipOrientation == Orientation.Horizontal && shotRow == shipRow && shotColumn >= shipColumn && shotColumn <= (shipColumn + shipLength - 1)) { //checks if the shot is within the length of the ship for horizontal orientation
            countHits++;
            return true;
        }
        return false;
    }

    /**
     * Checks if the ship is sunk
     * @return true if the ship is sunk
     */
    public boolean checkSunk() {
        return countHits >= shipLength;
    }
    /**
     * gets the ship's length
     * @return the ship's length
     */
    public int getShipLength() {
        return shipLength;
    }

    /**
     * gets the length in pixels of the ship
     * @return length of ship in pixels
     */
    public int getPixelsLength() {
        return pixelsLength;
    }

    /**
     * gets the width in pixels of the ship
     * @return width of ship in pixels
     */
    public int getPixelsWidth() {
        return pixelsWidth;
    }

    /**
     * sets the activated variable to false which deactivates the ship
     */
    public void deactivate() {
        activated = false;
    }

    /**
     * sets the activated variable to true which activates the ship
     */
    public void activate() {
        activated = true;
    }

    /**
     * gets the current activation status of the ship
     * @return boolean of activation status
     */
    public boolean getActivation() {
        return activated;
    }
}
