package com.battleshipics4u.ics4u_cpt_battleship.ships;

public class Ship {
    private String shipName;
    private int shipLength;
    private boolean shipOrientation = false; // 0 = horizontal 1 = vertical
    private int row, column; //left most and top most square of ship
    private boolean isSunk = false;
    private int countHits = 0;

    public Ship(String name, int length) {
        shipName = name;
        shipLength = length;
    }

    /**
     * Return Ship Position
     * @return array of x and y position
     */
    public int[] getPosition() {
        int[] position = {row, column};

        return position;
    }



    public boolean checkHit(int row, int column) {
        for(int x = 0; x < shipLength; x ++) {

        }

        return true;
    }

    public boolean checkSunk() {
        return countHits == shipLength;
    }

}
