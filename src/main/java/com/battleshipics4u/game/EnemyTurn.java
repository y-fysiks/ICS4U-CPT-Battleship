package com.battleshipics4u.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Daniel Gordon
 * class which stores all the logic behind the enemy bots moves
 */
public class EnemyTurn {
	Random rand = new Random(); // sets random num generator
	private final ArrayList<Shot> shots = new ArrayList<>(); //creates an array which stores all previous shots in order to prevent repetition

	/**
	 * Generates the next turn for the
	 * @param gameBoard the gameBoard that is being fired upon
	 * @return a Shot object indicating the coordinates to fire upon
	 */
	public Shot generateNextTurn(GameBoard gameBoard) { //generates the enemies next turn
		boolean validTurn = false;
		Shot newShot = null;
		int n = 0;
		if (gameBoard.injuredShips == null || gameBoard.injuredShips.size() == 0) { //if injuredShip is empty
			Shot s;
			do {
				s = new Shot(rand.nextInt(GameBoard.DEFAULT_COLS), rand.nextInt(GameBoard.DEFAULT_ROWS)); // generates a random shot
			} while (!s.isShotValid(shots));
			return s;
		}

		List<Shot> injuredShip = gameBoard.injuredShips.values().iterator().next();
		while (!validTurn) { //while validTurn is false
			//System.out.println("turn attempt: " + n++);
			int x = 0;
			int y = 0;
			if(n > 100) {
				//failsafe, if loop more than 100 times then generate a random position.
				x = rand.nextInt(GameBoard.DEFAULT_COLS);
				y = rand.nextInt(GameBoard.DEFAULT_ROWS);
				gameBoard.injuredShips.clear();
			} else if (injuredShip.size() == 1) { //if injuredShip has a value of 1 (1 shot hit)
				Shot shot = injuredShip.get(0); //gets x and y of that shot
				x = shot.getX(); //gets x
				y = shot.getY(); //gets y
				int direction = rand.nextInt(4); //randomly generates a direction it will shoot to try and hit the ship
				//System.out.println("Direction: " + direction);
				if (direction == 0) {
					x = shot.getX() - 1; // goes left
					if (x < 0) { //if you can not go any further left
						x = shot.getX() + 1; // goes right
					}
				}
				if (direction == 1) {
					x = shot.getX() + 1; // goes right
					if (x >= GameBoard.DEFAULT_COLS) { //if you can not go any further right
						x = shot.getX() - 1; // goes left
					}
				}
				if (direction == 2) {
					y = shot.getY() - 1; // goes up
					if (y < 0) { //if you can not go any further up
						y = shot.getY() + 1; // goes down
					}
				}
				if (direction == 3) {
					y = shot.getY() + 1; // goes down
					if (y >= GameBoard.DEFAULT_ROWS) { //if you can not go any further down
						y = shot.getY() - 1; // goes up
					}
				}
			} else {
				Shot shot1 = injuredShip.get(0); //gets first shot hit
				Shot shot2 = injuredShip.get(injuredShip.size()-1); //and the last shot hit
				int direction = rand.nextInt(2); //randomly generates a direction
				if(shot1.getX() == shot2.getX()) { //if the x values are equal on both shots
					x = shot1.getX(); //saves the x value
					int yMin = getYMin(injuredShip); //gets the smaller y value
					int yMax = getYMax(injuredShip); //gets the bigger y value
					if(direction == 0) {
						y = yMin-1; //uses the smaller y value and moves one space up
						if(y<0) { //if you can not go up any further
							y = yMax+1; //goes down
						}
					}
					if(direction == 1) {
						y = yMax+1; //uses the bigger y value and moves one space down
						if(y>=GameBoard.DEFAULT_ROWS) { //if you can not go down any further
							y = yMin-1; //goes up
						}
					}
				}
				if(shot1.getY() == shot2.getY()) { //if the y values are the same
					y = shot1.getY(); //saves the y value
					int xMin = getXMin(injuredShip); //gets the smaller x value
					int xMax = getXMax(injuredShip); //gets the bigger x value
					if(direction == 0) {
						x = xMin-1; //uses the smaller x value and moves one space left
						if(x<0) { //if you can not go left any further
							x = xMax+1; //goes right
						}
					}
					if(direction == 1) {
						x = xMax+1; //uses the bigger x value and moves one space right
						if(x>=GameBoard.DEFAULT_COLS) { //if you can not go right any further
							x = xMin-1; //goes left
						}
					}
				}
			}
			
			newShot = new Shot(x, y); //it is set as the new shot
			if (newShot.isShotValid(shots)) { //if the shot has not been previously used
				validTurn = true; //the shot is valid
				shots.add(newShot); // saves coordinates of fired shot
			}
			n++;
		}
		return newShot; //returns the valid shot
	}

	/**
	 * Gets the minimum y value
	 * @param list list of shots
	 * @return min
	 */
	public int getYMin(List<Shot> list) {
		int min = Integer.MAX_VALUE;
		for (Shot s : list) {
			if (s.getY() < min) min = s.getY();
		}
		return min;
	}

	/**
	 * Gets the maximum y value
	 * @param list list of shots
	 * @return max
	 */
	public int getYMax(List<Shot> list) {
		int max = Integer.MIN_VALUE;
		for (Shot s : list) {
			if (s.getY() > max) max = s.getY();
		}
		return max;
	}

	/**
	 * Gets the minimum x value
	 * @param list list of shots
	 * @return min
	 */
	public int getXMin(List<Shot> list) {
		int min = Integer.MAX_VALUE;
		for (Shot s : list) {
			if (s.getX() < min) min = s.getX();
		}
		return min;
	}

	/**
	 * Gets the minimum x value
	 * @param list list of shots
	 * @return max
	 */
	public int getXMax(List<Shot> list) {
		int max = Integer.MIN_VALUE;
		for (Shot s : list) {
			if (s.getX() > max) max = s.getX();
		}
		return max;
	}
}