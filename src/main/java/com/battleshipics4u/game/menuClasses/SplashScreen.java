package com.battleshipics4u.game.menuClasses;

import java.io.IOException;

public class SplashScreen extends Menu{
    /**
     * The constructor for the menu abstract class. This will be called by the subclasses to set up the menu.
     * @throws IOException if the fxml file cannot be found.
     */
    public SplashScreen() throws IOException {
        super("splash-screen.fxml");
    }

    @Override
    public void showMenu() {
        super.showMenu();
        try {
            Thread.sleep(3000); //pauses the tread for 5 seconds, to show the splash screen
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
