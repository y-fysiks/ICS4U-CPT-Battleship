package com.battleshipics4u.game.menuClasses;

public class EndMenu extends Menu {
    private boolean playerWon;

    /**
     * The constructor for the End menu
     */
    public EndMenu() {
        super("end-menu.fxml");
    }

    /**
     * overloaded showMenu() so that we can pass a boolean that indicates who has won
     * @param didPlayerWin true if the player has won, false if the enemy has won
     */
    public void showMenu(boolean didPlayerWin) {
        playerWon = didPlayerWin;
        super.showMenu();
    }

    /**
     * getter for seeing who won so we can display the correct message in the controller
     * @return playerWon
     */
    public boolean getPlayerWin() {
        return playerWon;
    }
}
