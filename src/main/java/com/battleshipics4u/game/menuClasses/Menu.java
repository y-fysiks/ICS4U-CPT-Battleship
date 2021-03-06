package com.battleshipics4u.game.menuClasses;

import com.battleshipics4u.game.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * @author Yubo Wang
 * An abstract class that simplifies some scene loading code.
 */
public abstract class Menu {
    public Scene sc; // The scene that the menu will be displayed on. Can be switched to another scene to switch between menus.
    public FXMLLoader fxmlLoader; // The FXML loader that will be used to load the FXML file for the GUI layout.

    /**
     * The constructor for the menu abstract class. This will be called by the subclasses to set up the menu.
     * @param fxmlPath the filename of the fxml file that will be used to load the GUI layout.
     */
    public Menu(String fxmlPath) {
        fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxmlPath));
    }

    /**
     * This method will be called by the subclasses to display the menu by switching the scene to the menu's scene.
     */
    public void showMenu() {
        try { //creates a 720p scene
            sc = new Scene(fxmlLoader.load(), 1280, 720);
        } catch (IOException e) {
            System.out.println("FXML File not found");
            e.printStackTrace();
        }
        MainApplication.setScene(sc);
    }
}
