package com.battleshipics4u.game.menuClasses;

import java.io.IOException;
import java.io.InputStream;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StartMenu extends Menu {
    
    public StartMenu(Stage stage) throws IOException {
        super("start-menu.fxml");
//        ClassLoader classLoader = getClass().getClassLoader();
//        InputStream inputStream = classLoader.getResourceAsStream("battleship_logo.png");
        Pane pane = new HBox(15);

        Image logo = new Image("battleship_logo.png");
//        ImageView selectedImage = new ImageView(logo);
        
        pane.getChildren().add(new ImageView(logo));
//        
//        selectedImage.setImage(logo);
//        selectedImage.setX(10);
//        selectedImage.setY(10);
//        selectedImage.setFitWidth(575);
//        selectedImage.setPreserveRatio(true);
//        
//        Group root = new Group(selectedImage);
        Scene scene = new Scene(pane, 600, 300);
        stage.setScene(scene);
        stage.show();

        //logoStream = new FileInputStream("battleship_logo.png");
        //logo = new Image(logoStream);
    }
}
