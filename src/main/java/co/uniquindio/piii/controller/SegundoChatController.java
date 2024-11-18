package co.uniquindio.piii.controller;

import co.uniquindio.piii.model.Cliente;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class SegundoChatController {

    @FXML
    private TextArea textAreaChat;

    @FXML
    private TextField textFieldMensaje;

    @FXML
    private Button btnEnviar;

    private Cliente cliente;
    private String usuarioDestino;

    // Referencia al ChatController principal
    private ChatController chatController;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        iniciarEscuchaMensajes();
    }

    public void setUsuarioDestino(String usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
    }

    public void setChatController(ChatController chatController) {
        this.chatController = chatController;
    }

    private void iniciarEscuchaMensajes() {
        new Thread(() -> cliente.escucharMensajes(mensaje -> {
            Platform.runLater(() -> textAreaChat.appendText(mensaje + "\n"));

            if (chatController != null) {
                chatController.actualizarChat(mensaje);
            }
        })).start();
    }

    @FXML
    private void enviarMensaje() {
        String mensaje = textFieldMensaje.getText();
        if (!mensaje.isEmpty()) {
            try {
                cliente.enviarMensaje(usuarioDestino, mensaje);
                textAreaChat.appendText("Tú: " + mensaje + "\n");
                textFieldMensaje.clear();

                if (chatController != null) {
                    chatController.actualizarChat("Tú: " + mensaje);
                }
            } catch (Exception e) {
                e.printStackTrace();
                textAreaChat.appendText("Error al enviar el mensaje\n");
            }
        }
    }

    public void actualizarChat(String mensaje) {
        Platform.runLater(() -> textAreaChat.appendText(mensaje + "\n"));
    }
}