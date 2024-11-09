package co.uniquindio.piii.controller;

import java.io.IOException;
import co.uniquindio.piii.App;
import co.uniquindio.piii.exceptions.EmailYaRegistradoException;
import co.uniquindio.piii.model.Registro;
import co.uniquindio.piii.model.Tienda;
import co.uniquindio.piii.model.Vendedor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class RegistroController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField userTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordTextField;

    private Tienda tienda = Tienda.getInstance("MiTienda"); // Instancia de la tienda

    @FXML
    private void handleRegister() {
        String nombre = nameTextField.getText();
        String username = userTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
    
        if (nombre.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showAlert(AlertType.ERROR, "Error de registro", "Por favor, completa todos los campos.");
        } else if (Registro.esUsuarioDuplicado(username)) {
            showAlert(AlertType.ERROR, "Error de registro", "El nombre de usuario ya está registrado.");
        } else if (Registro.esCorreoDuplicado(email)) {  // Validación de correo duplicado
            showAlert(AlertType.ERROR, "Error de registro", "El correo electrónico ya está registrado.");
        } else {
            try {
                // Crear el nuevo vendedor
                Vendedor nuevoVendedor = new Vendedor(nombre, username, password, email);
    
                // Guardar los datos de registro y añadir a la lista de vendedores
                Registro.guardarDatosRegistro(nombre, username, email, password);
                tienda.getVendedores().add(nuevoVendedor);
    
                showAlert(AlertType.INFORMATION, "Registro exitoso", "Usuario registrado correctamente.");
                limpiarCampos();
                abrirVentanaLogin();
            } catch (EmailYaRegistradoException e) {
                showAlert(AlertType.ERROR, "Error de registro", e.getMessage());
            }
        }
    }
    
    private void limpiarCampos() {
        nameTextField.clear();
        userTextField.clear();
        emailTextField.clear();
        passwordTextField.clear();
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void abrirVentanaLogin() {
        try {
            Parent root = FXMLLoader.load(App.class.getResource("login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();
            
            Stage currentStage = (Stage) userTextField.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "No se pudo abrir la ventana de Login.");
        }
    }

    @FXML
    private void irALogin() {
        try {
            // Cargar la ventana de login
            Parent root = FXMLLoader.load(App.class.getResource("login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();

            // Cerrar la ventana de registro actual
            Stage currentStage = (Stage) userTextField.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana de Login.");
        }
    }

}
