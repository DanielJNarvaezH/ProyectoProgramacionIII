package co.uniquindio.piii.controller;

import co.uniquindio.piii.model.Cliente;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChatController {

    @FXML
    private TextArea textAreaChat;

    @FXML
    private TextField textFieldMensaje;

    @FXML
    private Button btnEnviar;

    private Cliente cliente;
    private String usuarioDestino;

    // Referencia al SegundoChatController
    private SegundoChatController segundoChatController;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        iniciarEscuchaMensajes();
    }

    public void setUsuarioDestino(String usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
    }

    public void setSegundoChatController(SegundoChatController segundoChatController) {
        this.segundoChatController = segundoChatController;
    }

    private void iniciarEscuchaMensajes() {
        new Thread(() -> cliente.escucharMensajes(mensaje -> {
            Platform.runLater(() -> {
                textAreaChat.appendText(mensaje + "\n");

                if (segundoChatController != null) {
                    segundoChatController.actualizarChat(mensaje);
                }
            });
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

                if (segundoChatController != null) {
                    segundoChatController.actualizarChat("Tú: " + mensaje);
                }
            } catch (Exception e) {
                e.printStackTrace();
                textAreaChat.appendText("Error al enviar el mensaje\n");
            }
        }
    }

    @FXML
    private void abrirSegundoChat() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/piii/segundoChat.fxml"));
            Parent root = loader.load();
            SegundoChatController controller = loader.getController();

            controller.setCliente(cliente);
            controller.setUsuarioDestino(usuarioDestino);
            controller.setChatController(this); // Pasa la referencia del primer chat
            this.segundoChatController = controller;

            Stage stage = new Stage();
            stage.setTitle("Segundo Chat");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarChat(String mensaje) {
        Platform.runLater(() -> textAreaChat.appendText(mensaje + "\n"));
    }
}