package co.uniquindio.piii.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import co.uniquindio.piii.App;
import co.uniquindio.piii.controller.LoginController;

import co.uniquindio.piii.model.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ChatContactoController {

    @FXML
    public ListView<String> listViewUsuarios;

    private final String RUTA_REGISTRO = "registros.txt";

    private Cliente cliente;

    @FXML
    public void initialize() {
        // Obtén la instancia del cliente desde App
        cliente = App.getCliente();
    
        if (cliente == null) {
            System.out.println("Error: El cliente no está inicializado.");
            return;
        }
    
        // Cargar usuarios
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
            // Crear y mostrar la ventana de solicitud
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/piii/SolicitudContacto.fxml"));
            Parent root = loader.load();
            SolicitudContactoController controller = loader.getController();
    
            // Configurar el mensaje y las acciones
            controller.setMensaje("¿Deseas aceptar la solicitud de contacto con " + usuarioSeleccionado + "?");
            controller.setOnAceptar(() -> iniciarChat(usuarioSeleccionado));
            controller.setOnRechazar(() -> System.out.println("Solicitud rechazada por " + usuarioSeleccionado));
    
            // Mostrar la ventana
            Stage stage = new Stage();
            stage.setTitle("Solicitud de Contacto");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana de solicitud de contacto.");
        }
    }
    
    private void iniciarChat(String usuarioSeleccionado) {
        System.out.println("Iniciando chat con " + usuarioSeleccionado);
        abrirVentanaChat(usuarioSeleccionado);
    }
    
    private void abrirVentanaChat(String usuarioSeleccionado) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/piii/Chat.fxml"));
            Parent root = loader.load();
            ChatController controller = loader.getController();
            controller.setCliente(cliente);
            controller.setUsuarioDestino(usuarioSeleccionado);
    
            Stage stage = new Stage();
            stage.setTitle("Chat con " + usuarioSeleccionado);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana del chat.");
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
