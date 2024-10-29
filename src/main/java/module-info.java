module co.uniquindio.piii {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics; 
    requires java.desktop;
    requires java.logging;
    requires xstream;
 

    opens co.uniquindio.piii to javafx.graphics;
    opens co.uniquindio.piii.model to javafx.fxml;
    opens co.uniquindio.piii.controller to javafx.fxml;
    exports co.uniquindio.piii.controller;
    opens co.uniquindio.piii.utilities to xstream;
    exports co.uniquindio.piii.model;
    
}
