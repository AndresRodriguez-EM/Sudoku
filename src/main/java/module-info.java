module edward.sudokugame {
    requires javafx.controls;
    requires javafx.fxml;


    opens edward.sudokugame to javafx.fxml;
    exports edward.sudokugame;
}