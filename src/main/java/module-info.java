module cz.vse.adventure {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;


    exports cz.vse.adventure.main;
    exports cz.vse.adventure.logic;
    opens cz.vse.adventure.main to javafx.controls, javafx.fxml;
}