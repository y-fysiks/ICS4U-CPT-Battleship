package com.battleshipics4u.game.menuClasses;

import java.io.*;
import java.util.*;

public class MainGameplayMenu extends Menu {
    /**
     * The constructor for the menu abstract class. This will be called by the subclasses to set up the menu.
     *
     * @throws IOException if the fxml file cannot be found.
     */
    public MainGameplayMenu() throws IOException {
        super("main-gameplay-menu.fxml");
    }
}
