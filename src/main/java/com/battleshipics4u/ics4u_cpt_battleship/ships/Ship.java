package com.battleshipics4u.ics4u_cpt_battleship.ships;

import com.battleshipics4u.ics4u_cpt_battleship.GameBoard;

/**
 * Daniel Bajenaru
 * Class for ships
 * Methods for checking and settings ship states
 */
public class Ship {
    private String shipName;
    private int shipLength;
    private boolean shipOrientation = false; // 0 = horizontal 1 = vertical
    private int shipRow, shipColumn; //left most and top most square of ship
    private boolean isSunk = false;
    private int countHits = 0;

    /**
     * Constructor sets ship name and length
     * @param name the name of the ship
     * @param length the length of the ship
     */
    public Ship(String name, int length) {
        shipName = name;
        shipLength = length;
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

    /**
     * Set's the position of the ship
     * @param rowNum the row number of the position
     * @param columnNum the column number of the position
     */
    public void setPosition(int rowNum, int columnNum) {
        shipRow = rowNum;
        shipColumn = columnNum;
    }

    /**
     * Checks if the ship is hit by a shot
     * @param shotRow the row number of the shot
     * @param shotColumn the column number of the shot
     * @return true if the ship is hit
     */
    public boolean checkHit(int shotRow, int shotColumn) {
                if (shipOrientation) {
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
        return countHits == shipLength;
    }
}
