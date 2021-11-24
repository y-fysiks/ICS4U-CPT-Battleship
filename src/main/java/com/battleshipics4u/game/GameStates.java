package com.battleshipics4u.game;
import java.io.*;
import java.util.*;

/**
 * Yubo Wang
 * Stores the current internal state of the game, generates positions for the
 */
public class GameStates {
    GameBoard player, enemy;
    boolean playerTurn = true;

    public GameStates() {
        player = new GameBoard();
        enemy = new GameBoard();
    }

    public void getTurn() {


    }
}
