package com.battleshipics4u.game;

import com.battleshipics4u.game.ships.Ship;

import java.util.ArrayList;

/**
 * @author Daniel Gordon
 * @author Daniel Bajenaru
 * Stores coordinates of a shot methods for checking that shot
 */
public class Shot {
	private int x; //x coordinate of the shot
	private int y; //y coordinate of the shot

	/**
	 * Constructor
	 * @param x x coordinate for the shot
	 * @param y y coordinate for the shot
	 */
	public Shot(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Getter for the x coordinate
	 * @return Shot.x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Getter for the y coordinate
	 * @return Shot.y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Checks if the current shot is valid; if it is not a repeat of a previous shot
	 * @param playerShots a list of all the previous shot
	 * @return true if the shot is valid, false if not
	 */
	public boolean isShotValid(ArrayList<Shot> playerShots) {
		for (Shot shot : playerShots) {
			if (shot.getX() == x && shot.getY() == y) { //coordinates of new shot is the same as the shot its testing against
				return false;
			}
		}
		return true;
	}
}
