package com.battleshipics4u.game.menuClasses;

import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StartMenu extends Menu {
    private InputStream logoStream;
    private Image logo;
    
    public StartMenu() throws IOException {
        super("start-menu.fxml");
        //logoStream = new FileInputStream("battleship_logo.png");
        //logo = new Image(logoStream);
    }
}
