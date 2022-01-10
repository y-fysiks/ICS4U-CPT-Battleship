package com.battleshipics4u.game.ships;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Daniel Bajenaru
 * Class for ships
 * Methods for checking and settings ship states
 */
public class Ship {
    public final String shipName;
    private final int shipLength;
    private final int pixelsLength, pixelsWidth;
    public Image img;
    private Orientation shipOrientation = Orientation.Horizontal;
    private int shipRow = -10, shipColumn = -10; //left most and top most square of ship
    private boolean activated = false;
    private boolean isSunk = false;
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
        try {
            img = new Image(new FileInputStream("src/main/resources/com/battleshipics4u/game/" + imageName));
        } catch (FileNotFoundException e) {
            System.out.println("Error: image not found");
        }

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
        if (shipOrientation == Orientation.Vertical) {
            if (shotColumn == shipColumn && shotRow >= shipRow && shotRow <= (shipRow + shipLength - 1)) { //checks if the shot is within the length of the ship for vertical orientation
                countHits++;
                return true;
            }
        } else {
            if (shotRow == shipRow && shotColumn >= shipColumn && shotColumn <= (shipColumn + shipLength - 1)) { //checks if the shot is within the length of the ship for horizontal orientation
                countHits++;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the ship is sunk
     * @return true if the ship is sunk
     */
    public boolean checkSunk() {
        if (countHits == shipLength) {
            isSunk = true;
            return true;
        }
        return false;
    }

    /**
     * gets the ship's name
     * @return the ship's name
     */
    public String getShipName() {
        return shipName;
    }

    /**
     * gets the ship's length
     * @return the ship's length
     */
    public int getShipLength() {
        return shipLength;
    }

    public int getPixelsLength() {
        return pixelsLength;
    }

    public int getPixelsWidth() {
        return pixelsWidth;
    }

    public void deactivate() {
        activated = false;
    }

    public void activate() {
        activated = true;
    }

    public boolean getActivation() {
        return activated;
    }
}
