module com.battleshipics4u.ics4u_cpt_battleship {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;


    opens com.battleshipics4u.game to javafx.fxml;
    exports com.battleshipics4u.game;
    exports com.battleshipics4u.game.menuControllers;
    exports com.battleshipics4u.game.menuClasses;
    exports com.battleshipics4u.game.ships;
}