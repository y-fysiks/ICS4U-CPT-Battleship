package com.battleshipics4u.game;
import com.battleshipics4u.game.ships.Orientation;
import com.battleshipics4u.game.ships.Ship;

import java.util.*;

/**
 * Yubo Wang
 * Stores the current internal state of the game, generates positions for the battleships
 */
public class GameStates {
    public GameBoard player, enemy;
    boolean playerTurn = true;
    Random rand = new Random();

    public GameStates() {
        player = new GameBoard();
        enemy = new GameBoard();
    }

    public boolean getTurn() {
        return playerTurn;
    }

    /**
     * generates the enemy ship placements randomly and ensures there are no overlaps between them
     */
    public void generateEnemyShipPlacements() {
        for (Ship ship : enemy.shipList) {
            boolean direction = rand.nextBoolean();
            if (direction) {
                //vertical case
                int row, col;
                do {
                    row = rand.nextInt(GameBoard.DEFAULT_ROWS - ship.getShipLength());
                    col = rand.nextInt(GameBoard.DEFAULT_ROWS);
                } while (checkShipOverlaps(enemy, row, col, ship.getShipLength(), Orientation.Vertical));
                ship.setPosition(row, col, Orientation.Vertical);
            } else {
                //horizontal case
                int row, col;
                do {
                    row = rand.nextInt(GameBoard.DEFAULT_ROWS);
                    col = rand.nextInt(GameBoard.DEFAULT_ROWS - ship.getShipLength());
                } while (checkShipOverlaps(enemy, row, col, ship.getShipLength(), Orientation.Horizontal));
                ship.setPosition(row, col, Orientation.Horizontal);
            }
        }
    }

    /**
     * places a ship based on the specified coordinates for the PLAYER ONLY
     * @param shipIdx index in list of ship to place
     * @param row row in grid to place ship
     * @param col column in grid to place ship
     * @param dir orientation to place ship in
     * @return if the placement was successful or not
     */
    public boolean placeShip(int shipIdx, int row, int col, Orientation dir) {
        Ship ship = player.shipList.get(shipIdx);

        if (row < 0 || col < 0) return false;
        if (dir == Orientation.Horizontal) {
            if (row >= GameBoard.DEFAULT_ROWS || col > GameBoard.DEFAULT_COLS - ship.getShipLength()) return false;
        } else {
            if (row > GameBoard.DEFAULT_ROWS - ship.getShipLength() || col >= GameBoard.DEFAULT_COLS) return false;
        }

        if (checkShipOverlaps(player, row, col, ship.getShipLength(), dir)) return false;
        ship.setPosition(row, col, dir);
        ship.activate();
        return true;
    }

    /**
     * checks if the ships are overlapping
     * @param gb the gameBoard to check
     * @param row the row where the ship to be checked is
     * @param col the column where the ship
     * @param shipLength the length of the ship to check
     * @param orientation the orientation of the ship to check
     * @return whether or not the input ship size and location overlaps with any other ships
     */
    private boolean checkShipOverlaps(GameBoard gb, int row, int col, int shipLength, Orientation orientation) {
        int endRow = row, endCol = col;
        if (orientation == Orientation.Horizontal) {
            endCol += shipLength - 1;
        } else {
            endRow += shipLength - 1;
        }
        for (Ship s : gb.shipList) {
            if (!s.getActivation()) continue;
            int sRow = s.getPosition(0), sCol = s.getPosition(1);
            int sEndRow = sRow, sEndCol = sCol;
            if (s.getOrientation() == Orientation.Horizontal) {
                sEndCol += s.getShipLength() - 1;
            } else {
                sEndRow += s.getShipLength() - 1;
            }
            if (row <= sEndRow && endRow >= sRow && col <= sEndCol && endCol >= sCol) {
                return true;
            }
        }
        return false;
    }
    //------------------------------DEBUGGING METHODS--------------------------------
    /**
     * debugging method, prints the current board
     */
    public void printEnemyShipLocations() {
        boolean[][] board = new boolean[8][8];
        for (Ship s : enemy.shipList) {
            int row = s.getPosition(0), col = s.getPosition(1);
            for (int i = 0; i < s.getShipLength(); i++) {
                board[row][col] = true;
                if (s.getOrientation() == Orientation.Vertical) row++;
                else col++;
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j]) {
                    System.out.print(". ");
                }
                else System.out.print("  ");
            }
            System.out.println();
        }
    }


}
