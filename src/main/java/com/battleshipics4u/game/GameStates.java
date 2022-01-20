package com.battleshipics4u.game;

import com.battleshipics4u.game.ships.*;

import java.util.*;

/**
 * Yubo Wang
 * Stores the current internal state of the game, generates positions for the battleships
 * Main game class, storing core gameplay methods
 */
public class GameStates {
    public GameBoard player, enemy;
    private boolean playerTurn = true;
    private final Random rand = new Random();
    public final EnemyTurn enemyTurn;

    public GameStates() {
        player = new GameBoard();
        enemy = new GameBoard();
        enemyTurn = new EnemyTurn();
    }

    public boolean getTurn() {
        return playerTurn;
    }

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
            ship.activate();
        }
    }

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

    public boolean checkValidPlayerShot(int shotX, int shotY) {
        return enemy.gridStates[shotX][shotY] == 0; // returns false if the location of the enemy's board has already been shot
    }

    public Shot generateRandomPlayerShot() {
        Shot s;
        do {
            s = new Shot(rand.nextInt(8), rand.nextInt(8));
        } while (!checkValidPlayerShot(s.getX(), s.getY()));
        return s;
    }

    /**
     * Fires a shot on the Enemy by the Player
     * PLEASE CHECK IF SHOT IS VALID FIRST USING checkValidPlayerShot()
     * @param currentShot the Shot to make
     * @return true for a hit, false for a miss
     */
    public boolean takePlayerShot(Shot currentShot) {
        playerTurn = false;
        if (enemy.didShotHit(currentShot)) {
            enemy.gridStates[currentShot.getX()][currentShot.getY()] = 1;
            return true;
        } else {
            enemy.gridStates[currentShot.getX()][currentShot.getY()] = 2;
            return false;
        }
    }

    /**
     * Fires a shot on the Player by the Enemy
     * @param currentShot the Shot to make
     * @return true for a hit, false for a miss
     */
    public boolean takeEnemyShot(Shot currentShot) {
        playerTurn = true;
        return player.didShotHit(currentShot);
    }

    /**
     * Checks if the player has won the game
     * @return true if all enemy ships have been sunk and not all player ships have been sunk
     */
    public boolean checkPlayerWon() {
        return enemy.allSunk();
    }

    /**
     * Checks if the enemy has won the game
     * @return true if all player ships have been sunk and not all enemy ships have been sunk
     */
    public boolean checkEnemyWon() {
        return player.allSunk();
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
                else System.out.print("_ ");
            }
            System.out.println();
        }
    }

    /**
     * debugging method, prints the current board
     */
    public void printPlayerShipLocations() {
        boolean[][] board = new boolean[8][8];
        for (Ship s : player.shipList) {
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
                else System.out.print("_ ");
            }
            System.out.println();
        }
    }


}
