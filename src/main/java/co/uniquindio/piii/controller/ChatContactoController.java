package co.uniquindio.piii.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.uniquindio.piii.model.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ChatContactoController {

    @FXML
    private ListView<String> listViewUsuarios;

    private final String RUTA_REGISTRO = "registros.txt";

    private Cliente cliente;

    @FXML
    public void initialize() {
        List<String> usuarios = cargarUsuarios();
        ObservableList<String> usuariosObservable = FXCollections.observableArrayList(usuarios);
        listViewUsuarios.setItems(usuariosObservable);
    }

    /**
     * Configura la instancia de Cliente para este controlador.
     * @param cliente La instancia del cliente.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private List<String> cargarUsuarios() {
        List<String> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_REGISTRO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("%%");
                if (datos.length >= 2) {
                    usuarios.add(datos[1]); // Agrega el nombre de usuario
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
    
    @FXML
    private void handleIniciarContacto(ActionEvent event) {
        if (cliente == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "El cliente no está inicializado. Por favor, inicia sesión.");
            return;
        }
    
        String usuarioSeleccionado = listViewUsuarios.getSelectionModel().getSelectedItem();
    
        if (usuarioSeleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selecciona un usuario", "Por favor, selecciona un usuario para iniciar contacto.");
            return;
        }
    
        try {
            // Envía la solicitud de contacto al servidor
            cliente.enviarMensaje(usuarioSeleccionado, "Solicitud de contacto");
    
            // Notifica al usuario que la solicitud fue enviada
            mostrarAlerta(Alert.AlertType.INFORMATION, "Solicitud enviada", "La solicitud de contacto fue enviada a " + usuarioSeleccionado);
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo enviar la solicitud de contacto.");
        }
    }
    

    @FXML
    private void handleVolver(ActionEvent event) {
        // Cierra la ventana actual
        Stage stage = (Stage) listViewUsuarios.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
