module com.battleshipics4u.ics4u_cpt_battleship {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;


    opens com.battleshipics4u.ics4u_cpt_battleship to javafx.fxml;
    exports com.battleshipics4u.ics4u_cpt_battleship;
    exports com.battleshipics4u.ics4u_cpt_battleship.menuControllers;
}