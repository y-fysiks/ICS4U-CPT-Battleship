package com.battleshipics4u.game.menuClasses;

/**
 * @author Daniel Bejenaru
 * Splash screen class
 */
public class SplashScreen extends Menu{
    /**
     * The constructor for the splashscreen.
     */
    public SplashScreen() {
        super("splash-screen.fxml");
    }

    /**
     * Displays the splash screen for 3 seconds
     */
    @Override
    public void showMenu() {
        super.showMenu();
        try {
            Thread.sleep(3000); //pauses the tread for 3 seconds to show the splash screen
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
