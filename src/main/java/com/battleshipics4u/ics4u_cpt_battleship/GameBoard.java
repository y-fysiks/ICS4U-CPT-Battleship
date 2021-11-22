package com.battleshipics4u.ics4u_cpt_battleship;

import java.util.Random;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class GameBoard {
    Random random = new Random();
    Scene sc = new Scene(new GridPane(), 1280, 720);

    public static final int DEFAULT_ROWS = 10;
    public static final int DEFAULT_COLS = 10;
}
