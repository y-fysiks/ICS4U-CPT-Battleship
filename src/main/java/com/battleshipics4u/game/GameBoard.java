package com.battleshipics4u.game;

import com.battleshipics4u.game.ships.*;

import java.util.*;

/**
 * Daniel Bajenaru
 */
public class GameBoard {

    public static final int DEFAULT_ROWS = 8;
    public static final int DEFAULT_COLS = 8;
    public List<Shot> injuredShip = new ArrayList<>();

    public int[][] gridStates = new int[DEFAULT_COLS][DEFAULT_ROWS];
    //0 means not fired upon, 1 means hit, 2 means miss
    public ArrayList<Ship> shipList = new ArrayList<>();

    private int lastSunkIdx = -1;

    public GameBoard() {
        Ship.getShipIdx.put("Carrier", 0);
        Ship.getShipIdx.put("Battleship", 1);
        Ship.getShipIdx.put("Cruiser", 2);
        Ship.getShipIdx.put("Submarine", 3);
        Ship.getShipIdx.put("Destroyer", 4);


        Ship.getShipName.put(0, "Carrier");
        Ship.getShipName.put(1, "Battleship");
        Ship.getShipName.put(2, "Cruiser");
        Ship.getShipName.put(3, "Submarine");
        Ship.getShipName.put(4, "Destroyer");


        Ship carrier = new Ship("Carrier", 4, 280, 75, "carrier.png");
        Ship battleship = new Ship("Battleship", 4, 280, 50, "battleship.png");
        Ship cruiser = new Ship("Cruiser", 3, 215, 38, "cruiser.png");
        Ship submarine = new Ship("Submarine", 3, 215, 53, "submarine.png");
        Ship destroyer = new Ship("Destroyer", 2, 140, 30, "destroyer.png");


        shipList.add(carrier);
        shipList.add(battleship);
        shipList.add(cruiser);
        shipList.add(submarine);
        shipList.add(destroyer);

    }

    /**
     * Checks if a shot hit a ship on the given gameBoard
     * @param shot the shot object
     * @return true if shot did hit, false if missed
     */
    public boolean didShotHit(Shot shot) {
        for (Ship currentShip: shipList) {
            boolean prevSunk = currentShip.checkSunk();
            if (currentShip.checkHit(shot.getY(), shot.getX())) {
                injuredShip.add(shot);
                if (currentShip.checkSunk() && !prevSunk) {
                    lastSunkIdx = currentShip.shipIdx;
                    injuredShip.clear();
                } else lastSunkIdx = -1;
                return true;
            }
        }
        lastSunkIdx = -1;
        return false;
    }

    public int getLastSunkIdx() {
        return lastSunkIdx;
    }

    public boolean allSunk() {
        for (Ship s : shipList) {
            if (!s.checkSunk()) return false;
        }
        return true;
    }
}
