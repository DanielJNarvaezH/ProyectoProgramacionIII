package co.uniquindio.piii.controller;

import co.uniquindio.piii.model.Cliente;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        iniciarEscuchaMensajes(); // Inicia la escucha de mensajes una vez que el cliente está configurado
    }

    public void setUsuarioDestino(String usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
    }

    private void iniciarEscuchaMensajes() {
        // Inicia un nuevo hilo para escuchar mensajes
        new Thread(() -> cliente.escucharMensajes(mensaje -> {
            // Actualiza el área de texto del chat con los mensajes entrantes
            Platform.runLater(() -> textAreaChat.appendText(mensaje + "\n"));
        })).start();
    }

    @FXML
    private void enviarMensaje() {
        String mensaje = textFieldMensaje.getText();
        if (!mensaje.isEmpty()) {
            try {
                cliente.enviarMensaje(usuarioDestino, mensaje); // Método para enviar mensajes al servidor
                textAreaChat.appendText("Tú: " + mensaje + "\n");
                textFieldMensaje.clear();
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
            Stage stage = new Stage();
            Scene scene = new Scene(root);
    
            SegundoChatController segundoChatController = loader.getController();
            segundoChatController.setCliente(cliente);
            segundoChatController.setUsuarioDestino(usuarioDestino);
            segundoChatController.setTextAreaChatCompartido(textAreaChat); // Compartir el área de texto
    
            stage.setTitle("Segundo Chat");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
