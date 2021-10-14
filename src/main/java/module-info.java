module com.battleshipics4u.ics4u_cpt_battleship {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.battleshipics4u.ics4u_cpt_battleship to javafx.fxml;
    exports com.battleshipics4u.ics4u_cpt_battleship;
}