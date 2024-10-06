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
    private PasswordField passwordField;

    @FXML
    private void handleRegister() {
        String username = userTextField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(AlertType.ERROR, "Error de registro", "Por favor, completa todos los campos.");
        } else {
            // Aquí agregarías la lógica para registrar al usuario en tu base de datos o sistema
            // Por ejemplo, podrías validar si el usuario ya existe o guardar los datos.
            
            showAlert(AlertType.INFORMATION, "Registro exitoso", "Usuario registrado correctamente.");
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        userTextField.clear();
        passwordField.clear();
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
