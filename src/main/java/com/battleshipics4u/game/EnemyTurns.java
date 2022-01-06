package com.battleshipics4u.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyTurns {
	Random rand = new Random(); // sets random num generator
	private List<Shot> shots = new ArrayList<>();

	private boolean previouslyShot(int x, int y) {
		for (Shot shot : shots) {
			if (shot.getX() == x && shot.getY() == y) {
				return true;
			}
		}
		return false;
	}

	public Shot generateNextTurn(GameBoard gameBoard) {
		boolean validTurn = false;
		Shot newShot = null;
		while (!validTurn) {
			int x = 0;
			int y = 0;
			if (gameBoard.injuredShip == null || gameBoard.injuredShip.size() == 0) {
				x = rand.nextInt(GameBoard.DEFAULT_COLS) + 1; // generates a random int from 1-8 in order to choose
																// position of shot
				y = rand.nextInt(GameBoard.DEFAULT_ROWS) + 1; // generates a random int from 1-8 in order to choose
																// position
			} else {
				if (gameBoard.injuredShip.size() == 1) {
					Shot shot = gameBoard.injuredShip.get(0);
					x = shot.getX();
					y = shot.getY();
					int direction = rand.nextInt(4);
					System.out.println("Direction: " + direction);
					if (direction == 0) {
						x = shot.getX() - 1; // goes left
						if (x < 1) {
							x = shot.getX() + 1; // goes right
						}
					}
					if (direction == 1) {
						x = shot.getX() + 1; // goes right
						if (x > GameBoard.DEFAULT_COLS + 1) {
							x = shot.getX() - 1; // goes left
						}
					}
					if (direction == 2) {
						y = shot.getY() - 1; // goes up
						if (y < 1) {
							y = shot.getY() + 1; // goes down
						}
					}
					if (direction == 3) {
						y = shot.getY() + 1; // goes down
						if (y > GameBoard.DEFAULT_ROWS + 1) {
							y = shot.getY() - 1; // goes up
						}
					}
				}
				if(gameBoard.injuredShip.size() > 1) {
					Shot shot1 = gameBoard.injuredShip.get(0);
					Shot shot2 = gameBoard.injuredShip.get(gameBoard.injuredShip.size()-1);
					int direction = rand.nextInt(2);
					if(shot1.getX() == shot2.getX()) {
						x = shot1.getX();
						int yMin = Math.min(shot1.getY(), shot2.getY());
						int yMax = Math.max(shot1.getY(), shot2.getY());
						if(direction == 0) {
							y = yMin-1;
							if(y<1) {
								y = yMax+1;
							}
						}
						if(direction == 1) {
							y = yMax+1;
							if(y>GameBoard.DEFAULT_ROWS + 1) {
								y = yMin-1;
							}
						}
					}
					if(shot1.getY() == shot2.getY()) {
						y = shot1.getY();
						int xMin = Math.min(shot1.getX(), shot2.getX());
						int xMax = Math.max(shot1.getX(), shot2.getX());
						if(direction == 0) {
							x = xMin-1;
							if(x<1) {
								x = xMax+1;
							}
						}
						if(direction == 1) {
							x = xMax+1;
							if(x>GameBoard.DEFAULT_COLS + 1) {
								x = xMin-1;
							}
						}
					}
				}
			}
			if (!previouslyShot(x, y)) {
				validTurn = true;
				newShot = new Shot(x, y);
				shots.add(newShot); // saves coordinates of fired shot
			}
		}
		return newShot;
	}

	/*
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
