module co.uniquindio.piii {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics; 

    opens co.uniquindio.piii to javafx.fxml;
    opens co.uniquindio.piii.model to javafx.fxml;
    opens co.uniquindio.piii.controller;
    exports co.uniquindio.piii.controller;
    exports co.uniquindio.piii.model;
    exports co.uniquindio.piii;
}
