package com.battleshipics4u.game;

import com.battleshipics4u.game.*;
import com.battleshipics4u.game.ships.*;
import java.util.ArrayList;

/**
 * Daniel Bajenaru
 * Class which stores the logic for the player turn
 */
public class PlayerTurn{
    private ArrayList<Shot> playerShots = new ArrayList<>();
    private GameBoard gameBoard;

    public PlayerTurn(GameBoard currentBoard) {
        gameBoard = currentBoard;
    }

    /**
     * Fires a shot
     * @param shotX x coordinate for the shot
     * @param shotY y coordinate for the shot
     * @return the current shot
     */
    public Shot makeShot(int shotX, int shotY) {
        Shot currentShot = new Shot(shotX, shotY);
        if (currentShot.isShotValid(shotX, shotY, playerShots)) {
            playerShots.add(currentShot);
            if (currentShot.didShotHit(shotX, shotY, gameBoard)) {
                System.out.println("You hit a ship");
            } else {
                System.out.println("You missed ");
            }
        }
        return currentShot;
    }
}
