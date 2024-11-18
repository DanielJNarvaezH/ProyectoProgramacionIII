package co.uniquindio.piii.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SolicitudContactoController {

    @FXML
    private Label labelMensaje;

    private Runnable onAceptar; // Acción al aceptar
    private Runnable onRechazar; // Acción al rechazar

    public void setMensaje(String mensaje) {
        labelMensaje.setText(mensaje);
    }

    public void setOnAceptar(Runnable onAceptar) {
        this.onAceptar = onAceptar;
    }

    public void setOnRechazar(Runnable onRechazar) {
        this.onRechazar = onRechazar;
    }

    @FXML
    void handleAceptar(ActionEvent event) {
        if (onAceptar != null) {
            onAceptar.run();
        }
        cerrarVentana();
    }

    @FXML
    void handleRechazar(ActionEvent event) {
        if (onRechazar != null) {
            onRechazar.run();
        }
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) labelMensaje.getScene().getWindow();
        stage.close();
    }
}
