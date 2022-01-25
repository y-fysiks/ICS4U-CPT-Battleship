package com.battleshipics4u.game.menuClasses;

/**
 * @author Daniel Bejenaru
 */
public class SplashScreen extends Menu{
    /**
     * The constructor for the splashscreen.
     */
    public SplashScreen() {
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
