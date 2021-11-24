package com.battleshipics4u.ics4u_cpt_battleship;

import java.util.Random;
import com.battleshipics4u.ics4u_cpt_battleship.ships.*;
import java.util.*;


public class GameBoard {
    Random random = new Random();

    public static final int DEFAULT_ROWS = 8;
    public static final int DEFAULT_COLS = 8;

    public int[][] gameBoard = new int[DEFAULT_ROWS][DEFAULT_COLS];
    private ArrayList<Ship> shipList = new ArrayList<>();


    public void getBoard() {

    }
}
