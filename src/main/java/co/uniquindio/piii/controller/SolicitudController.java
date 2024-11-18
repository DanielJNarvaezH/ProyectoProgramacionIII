package co.uniquindio.piii.controller;

import java.io.IOException;

import co.uniquindio.piii.model.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SolicitudController {

    @FXML
    private Label labelSolicitud;

    private String usuarioSolicitante;
    private Cliente cliente;

    public void inicializar(String usuarioSolicitante, Cliente cliente) {
        this.usuarioSolicitante = usuarioSolicitante;
        this.cliente = cliente;
        labelSolicitud.setText("Solicitud de contacto de: " + usuarioSolicitante);
    }

    @FXML
    private void handleAceptar() {
        try {
            cliente.enviarMensaje(usuarioSolicitante, "Solicitud aceptada");
            iniciarChat();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cerrarVentana();
    }

    @FXML
    private void handleRechazar() {
        cerrarVentana();
    }

    private void iniciarChat() {
        // Lógica para abrir la ventana de chat
    }

    private void cerrarVentana() {
        Stage stage = (Stage) labelSolicitud.getScene().getWindow();
        stage.close();
    }
}
