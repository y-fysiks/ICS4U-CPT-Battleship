package com.battleshipics4u.ics4u_cpt_battleship.menuClasses;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.*;
import java.util.*;

import com.battleshipics4u.ics4u_cpt_battleship.MainApplication; // import MainApplication so we can access the MainApplication resources

public abstract class Menu {
    private Scene sc; // The scene that the menu will be displayed on. Can be switched to another scene to switch between menus. 
    private FXMLLoader fxmlLoader; // The FXML loader that will be used to load the FXML file for the GUI layout.

    /**
     * The constructor for the menu abstract class. This will be called by the subclasses to set up the menu.
     * @param fxmlPath the filename of the fxml file that will be used to load the GUI layout.
     * @throws IOException if the fxml file cannot be found.
     */
    public Menu(String fxmlPath) throws IOException {
        fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxmlPath));
        sc = new Scene(fxmlLoader.load(), 1280, 720);
    }
    /**
     * This method will be called by the subclasses to display the menu by switching the scene to the menu's scene.
     */
    public void showMenu() {
        MainApplication.setScene(sc);
    }

}
