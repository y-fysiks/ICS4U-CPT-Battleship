package com.battleshipics4u.game;

import com.battleshipics4u.game.ships.*;

import java.util.*;

/**
 * @author Daniel Bajenaru
 * @author Daniel Gordon
 */
public class GameBoard {
	public static final int DEFAULT_ROWS = 8;
	public static final int DEFAULT_COLS = 8;
	// stores ships that are injured for the AI. LinkedHashMap allows the program to remember the order of which ship was hit first, allowing the program to prioritize finishing off the first ship it hit
	public Map<Integer, List<Shot>> injuredShips = new LinkedHashMap<>();

	public int[][] gridStates = new int[DEFAULT_COLS][DEFAULT_ROWS];
	// 0 means not fired upon, 1 means hit, 2 means miss
	public ArrayList<Ship> shipList = new ArrayList<>(); // arraylist for all the ships

	private int lastSunkIdx = -1; // variable for the getLastSunkIdx() function

	/**
	 * constructor for the GameBoard.
	 * Creates all the different ships, their corresponding lengths, and adds them to the ShipList
	 */
	public GameBoard() {
		Ship.getShipIdx.put("Carrier", 0);
		Ship.getShipIdx.put("Battleship", 1);
		Ship.getShipIdx.put("Cruiser", 2);
		Ship.getShipIdx.put("Submarine", 3);
		Ship.getShipIdx.put("Destroyer", 4);

		Ship.getShipName.put(0, "Carrier");
		Ship.getShipName.put(1, "Battleship");
		Ship.getShipName.put(2, "Cruiser");
		Ship.getShipName.put(3, "Submarine");
		Ship.getShipName.put(4, "Destroyer");

		Ship carrier = new Ship("Carrier", 4, 280, 75, "carrier.png");
		Ship battleship = new Ship("Battleship", 4, 280, 50, "battleship.png");
		Ship cruiser = new Ship("Cruiser", 3, 215, 38, "cruiser.png");
		Ship submarine = new Ship("Submarine", 3, 215, 53, "submarine.png");
		Ship destroyer = new Ship("Destroyer", 2, 140, 30, "destroyer.png");

		shipList.add(carrier);
		shipList.add(battleship);
		shipList.add(cruiser);
		shipList.add(submarine);
		shipList.add(destroyer);

	}

	/**
	 * Checks if a shot hit a ship on the given gameBoard
	 * @param shot the shot object
	 * @return true if shot did hit, false if missed
	 */
	public boolean didShotHit(Shot shot) {
		for (Ship currentShip : shipList) {
			if (currentShip.checkHit(shot.getY(), shot.getX())) {
				List<Shot> shots = injuredShips.get(currentShip.shipIdx); //checks which ship is being hit
				if (shots != null) { //if the shot is empty
					shots.add(shot); //adds a shot to the list
				} else {
					List<Shot> injShots = new ArrayList<>();
					injShots.add(shot); //adds the shot to the list of injured shots
					injuredShips.put(currentShip.shipIdx, injShots); //stores the ships ID aswell as the shots that injured it
				}
				if (currentShip.checkSunk()) { //if ship is sunk
					lastSunkIdx = currentShip.shipIdx; //sets lastSunkIdx to the current ship
					injuredShips.remove(lastSunkIdx); //removes the ship from the injuredShips array
				} else
					lastSunkIdx = -1;
				return true;
			}
		}
		lastSunkIdx = -1;
		return false;
	}

	/**
	 * gets the index of the ship that was last sunk by the shot that was fired.
	 * @return the index of the last sunk ship. returns -1 if the last shot did not sink a ship
	 */
	public int getLastSunkIdx() {
		return lastSunkIdx;
	}

	/**
	 * checks whether all the ships have been sunk
	 * @return true if all ships on the GameBoard have been sunk, false otherwise
	 */
	public boolean allSunk() {
		for (Ship s : shipList) {
			if (!s.checkSunk())
				return false;
		}
		return true;
	}
}
