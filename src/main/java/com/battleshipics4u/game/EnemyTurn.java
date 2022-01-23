package com.battleshipics4u.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Daniel Gordon
 * class which stores all the logic behind the enemy bots moves
 */
public class EnemyTurn {
	Random rand = new Random(); // sets random num generator
	private final ArrayList<Shot> shots = new ArrayList<>(); //creates an array which stores all previous shots in order to prevent repetition

	/**
	 * Generates the next turn for the
	 * @param gameBoard the gameBoard that is being fired upon
	 * @return a Shot object indicating the coordinates to fire upon
	 * @throws Exception if the AI is unable to find a spot to shoot at
	 */
	public Shot generateNextTurn(GameBoard gameBoard) throws Exception { //generates the enemies next turn
		boolean validTurn = false;
		Shot newShot = null;
		int n = 0;
		while (!validTurn) { //while validTurn is false
			//System.out.println("turn attempt: " + n++);
			if(n > 100) {
				throw new Exception("Unable to find a spot to hit");
			}
			int x = 0;
			int y = 0;
			if (gameBoard.injuredShips == null || gameBoard.injuredShips.size() == 0) { //if injuredShip is empty
				x = rand.nextInt(GameBoard.DEFAULT_COLS); // generates a random int from 0-7 in order to choose
																// position of shot
				y = rand.nextInt(GameBoard.DEFAULT_ROWS); // generates a random int from 0-7 in order to choose
																// position
			} else {
				List<Shot> injuredShip = gameBoard.injuredShips.values().iterator().next();
				if (injuredShip.size() == 1) { //if injuredShip has a value of 1 (1 shot hit)
					
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
						int yMin = Math.min(shot1.getY(), shot2.getY()); //gets the smaller y value
						int yMax = Math.max(shot1.getY(), shot2.getY()); //gets the bigger y value
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
						int xMin = Math.min(shot1.getX(), shot2.getX()); //gets the smaller x value
						int xMax = Math.max(shot1.getX(), shot2.getX()); //gets the bigger x value
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
			}
			
			newShot = new Shot(x, y); //it is set as the new shot
			if (newShot.isShotValid(x, y, shots)) { //if the shot has not been previously used
				validTurn = true; //the shot is valid
				shots.add(newShot); // saves coordinates of fired shot
			}
			n++;
		}
		return newShot; //returns the valid shot
	}

	/*
	//Testing
	public static void main(String[] args) {
		EnemyTurns enemyTurns = new EnemyTurns();
		GameBoard gameBoard = new GameBoard();
		List<Shot> injuredShip = new ArrayList<Shot>();
		injuredShip.add(new Shot(3, 4));
		injuredShip.add(new Shot(4, 4));
		gameBoard.injuredShip = injuredShip;
		Shot shot = enemyTurns.generateNextTurn(gameBoard);
		System.out.println("Shot = " + shot.getX() + " / " + shot.getY());
	}
	*/
}