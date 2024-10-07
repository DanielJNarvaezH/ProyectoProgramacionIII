package co.uniquindio.piii.model;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RegistroController {

    @FXML
    private TextField userTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private void handleRegister() {
        String username = userTextField.getText();
        String password = passwordTextField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(AlertType.ERROR, "Error de registro", "Por favor, completa todos los campos.");
        } else {
            Registro.guardarDatosRegistro(username, password);  // Llama al método para guardar datos
            showAlert(AlertType.INFORMATION, "Registro exitoso", "Usuario registrado correctamente.");
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        userTextField.clear();
        passwordTextField.clear();
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
