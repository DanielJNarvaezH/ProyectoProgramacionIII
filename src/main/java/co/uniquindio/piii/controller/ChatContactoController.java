package co.uniquindio.piii.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.uniquindio.piii.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ChatContactoController {

    @FXML
    private ListView<String> listViewUsuarios;

    private final String RUTA_REGISTRO = "registros.txt";

    @FXML
    public void initialize() {
        List<String> usuarios = cargarUsuarios();
        ObservableList<String> usuariosObservable = FXCollections.observableArrayList(usuarios);
        listViewUsuarios.setItems(usuariosObservable);
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
    private void handleVolver(ActionEvent event) {
        try {
            // Cargar la ventana de registro de productos
            Parent root = FXMLLoader.load(App.class.getResource("MenuGeneral.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Menu General");
            stage.setScene(new Scene(root));
            stage.show();

            // Cerrar la ventana actual
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana de Registro.");
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }
}
