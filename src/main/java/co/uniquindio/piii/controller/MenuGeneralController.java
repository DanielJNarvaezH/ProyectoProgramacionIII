package co.uniquindio.piii.controller;

import java.io.IOException;

import co.uniquindio.piii.App;
import co.uniquindio.piii.model.UsuarioActivo;
import co.uniquindio.piii.model.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class MenuGeneralController {

    @FXML
    private Button btnChatContacto;
    
    @FXML
    private Button btnComentario;
    
    @FXML
    private Button btnProducto;
    
    @FXML
    private Button btnVendedor;

    @FXML
    private Label labelPrueba;

    private Vendedor usuario = UsuarioActivo.getInstance().getVendedor();

    @FXML
    private void handleChatContacto() {
        // Lógica para redirigir a la vista de chat de contacto
        System.out.println("Ir a chat de contacto.");
    }

    @FXML
    private void handleComentario() {
        // Lógica para redirigir a la vista de comentarios
        System.out.println("Ir a comentarios.");
    }

    @FXML
    private void handleProducto(ActionEvent event) {
        try {
            // Cargar la ventana de registro
            Parent root = FXMLLoader.load(App.class.getResource("producto.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Productos vendedor");
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

    @FXML
    private void handleVendedor() {
        // Lógica para redirigir a la vista de vendedores
        System.out.println("Ir a vendedores.");
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        String nombreUsuario = UsuarioActivo.getInstance().getVendedor().getNombre();
        labelPrueba.setText(nombreUsuario);
        assert btnChatContacto != null : "fx:id=\"btnChatContacto\" was not injected: check your FXML file 'MenuGeneral.fxml'.";
        assert btnComentario != null : "fx:id=\"btnComentario\" was not injected: check your FXML file 'MenuGeneral.fxml'.";
        assert btnProducto != null : "fx:id=\"btnProducto\" was not injected: check your FXML file 'MenuGeneral.fxml'.";
        assert btnVendedor != null : "fx:id=\"btnVendedor\" was not injected: check your FXML file 'MenuGeneral.fxml'.";

    }
}
