package com.battleshipics4u.game;

import com.battleshipics4u.game.ships.*;
import java.util.*;


public class GameBoard {
    Random random = new Random();

    public static final int DEFAULT_ROWS = 8;
    public static final int DEFAULT_COLS = 8;

    public int[][] gameBoard = new int[DEFAULT_ROWS][DEFAULT_COLS];
    private ArrayList<Ship> shipList = new ArrayList<>();

    private Ship carrier, battleship, destroyer, cruiser, submarine;

    public GameBoard() {
        carrier = new Ship("Carrier", 5);
        battleship = new Ship("Battleship", 4);
        destroyer = new Ship("Destroyer", 3);
        submarine = new Ship("Submarine", 3);
        cruiser = new Ship("Cruiser", 2);

        shipList.add(carrier);
        shipList.add(battleship);
        shipList.add(destroyer);
        shipList.add(submarine);
        shipList.add(cruiser);
    }
}
