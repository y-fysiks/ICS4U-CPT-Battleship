package com.battleshipics4u.ics4u_cpt_battleship.menuControllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.*;
import java.util.*;

abstract class Menu {
    Scene sc;

    public void createScene(FXMLLoader fxmlLoader) throws IOException {
        sc = new Scene(fxmlLoader.load(), 1280, 720);
    }

}
