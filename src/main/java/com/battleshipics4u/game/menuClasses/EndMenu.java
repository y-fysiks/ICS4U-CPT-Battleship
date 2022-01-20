package com.battleshipics4u.game.menuClasses;

public class EndMenu extends Menu {
    private boolean playerWon;

    /**
     * The constructor for the End menu
     */
    public EndMenu() {
        super("end-menu.fxml");
    }

    public void showMenu(boolean didPlayerWin) {
        playerWon = didPlayerWin;
        super.showMenu();
    }

    public boolean getPlayerWin() {
        return playerWon;
    }
}
