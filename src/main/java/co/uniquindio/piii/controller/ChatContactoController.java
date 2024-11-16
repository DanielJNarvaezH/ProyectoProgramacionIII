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
import javafx.scene.control.ListView;
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
        // Cierra la ventana actual
        Stage stage = (Stage) listViewUsuarios.getScene().getWindow();
        stage.close();
    }
}
