package com.battleshipics4u.game;

import com.battleshipics4u.game.ships.*;
import java.util.*;

/**
 * Daniel Bajenaru
 */
public class GameBoard {
    Random random = new Random();

    public static final int DEFAULT_ROWS = 8;
    public static final int DEFAULT_COLS = 8;
    public List<Shot> injuredShip = new ArrayList<>();

    public int[][] gridStates = new int[DEFAULT_ROWS][DEFAULT_COLS];
    //0 means not fired upon, 1 means hit, 2 means miss
    public ArrayList<Ship> shipList = new ArrayList<>();

    private Ship carrier, battleship, destroyer, cruiser, submarine;

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


        carrier = new Ship("Carrier", 5, 280, 75, "carrier.png");
        battleship = new Ship("Battleship", 4, 280, 50, "battleship.png");
        cruiser = new Ship("Cruiser", 2, 215, 38, "cruiser.png");
        submarine = new Ship("Submarine", 3, 215, 53, "submarine.png");
        destroyer = new Ship("Destroyer", 3, 140, 30, "destroyer.png");


        shipList.add(carrier);
        shipList.add(battleship);
        shipList.add(destroyer);
        shipList.add(submarine);
        shipList.add(cruiser);
    }

}
