package com.battleshipics4u.game;

import com.battleshipics4u.game.ships.*;

import java.util.*;

/**
 * @author Yubo Wang
 * Stores the current internal state of the game, generates positions for the battleships
 * Main game class, storing core gameplay methods
 */
public class GameStates {
    public GameBoard player, enemy;
    private boolean playerTurn = true; // determines whether it is the player's turn to go
    private final Random rand = new Random();
    public final EnemyTurn enemyTurn; // EnemyTurn class to generate enemy turns

    /**
     * Constructor for GameStates
     * creates both GameBoards and the EnemyTurn
     */
    public GameStates() {
        player = new GameBoard();
        enemy = new GameBoard();
        enemyTurn = new EnemyTurn();
    }

    /**
     * gets whose turn it is
     * @return playerTurn
     */
    public boolean getTurn() {
        return playerTurn;
    }

    /**
     * Generates random enemy ship placements that do not overlap
     */
    public void generateEnemyShipPlacements() {
        for (Ship ship : enemy.shipList) {
            boolean direction = rand.nextBoolean(); //random orientation
            if (direction) { //vertical case
                int row, col;
                do {
                    row = rand.nextInt(GameBoard.DEFAULT_ROWS - ship.getShipLength());
                    col = rand.nextInt(GameBoard.DEFAULT_ROWS);
                } while (checkShipOverlaps(enemy, row, col, ship.getShipLength(), Orientation.Vertical));
                ship.setPosition(row, col, Orientation.Vertical);
            } else { //horizontal case
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

    /**
     * Stores the ship location on the player board when player sets up their board
     * @param shipIdx index of the ship just placed
     * @param row the row of the ship
     * @param col the column of the ship
     * @param dir the orientation of the ship
     * @return if the ship can be placed where player wants return true. If illegal placement, return false
     */
    public boolean placeShip(int shipIdx, int row, int col, Orientation dir) {
        Ship ship = player.shipList.get(shipIdx);

        if (row < 0 || col < 0) return false; // if the location is invalid, return false
        if (dir == Orientation.Horizontal) { // if the ship is horizontal, check if it is out of bounds
            if (row >= GameBoard.DEFAULT_ROWS || col > GameBoard.DEFAULT_COLS - ship.getShipLength()) return false;
        } else { // if the ship is vertical, check if it is out of bounds
            if (row > GameBoard.DEFAULT_ROWS - ship.getShipLength() || col >= GameBoard.DEFAULT_COLS) return false;
        }
        // check if the ship overlaps with any previously placed ships and return false if so
        if (checkShipOverlaps(player, row, col, ship.getShipLength(), dir)) return false;
        ship.setPosition(row, col, dir);
        ship.activate();
        return true;
    }

    /**
     * Checks if the ship trying to be placed overlaps another ship
     * @param gb which gameBoard to check the ships, enemy or player
     * @param row the row of the ship being tested
     * @param col the column of the ship being tested
     * @param shipLength length of the ship being tested
     * @param orientation the orientation of the ship being tested
     * @return if the ship overlaps another ship return true. If not then return false
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

    /**
     * Checks whether a new player shot has already been taken
     * @param shotX the x coordinate of the shot
     * @param shotY the y coordinate of the shot
     * @return true if the player shot has not been repeated before
     */
    public boolean checkValidPlayerShot(int shotX, int shotY) {
        return enemy.gridStates[shotX][shotY] == 0; // returns false if the location of the enemy's board has already been shot
    }

    /**
     * Generates a random player shot that is valid
     * @return a valid randomly generated Shot
     */
    public Shot generateRandomPlayerShot() {
        Shot s;
        do { // loops until a valid shot is generated
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
        playerTurn = false; // once the player has gone, it is no longer their turn so set playerTurn to false
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
