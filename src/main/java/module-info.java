module co.uniquindio.piii {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.uniquindio.piii to javafx.fxml;
    exports co.uniquindio.piii;
}
