package co.uniquindio.piii.model;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
            // Llama al método para guardar datos
            Registro.guardarDatosRegistro(username, password);
            showAlert(AlertType.INFORMATION, "Registro exitoso", "Usuario registrado correctamente.");
            limpiarCampos();
            abrirVentanaLogin();  // Llamar al método para abrir la ventana de login

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

    // Método para abrir la ventana de login
    @FXML
    private void abrirVentanaLogin() {
        try {
            System.out.println(getClass().getResource("login.fxml"));  // Línea de depuración

            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml")); // Asegúrate de que la ruta sea correcta
            Parent root = loader.load();
        
            // Si deseas abrir una nueva ventana (Stage)
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();
        
            // Cerrar la ventana actual si es necesario
            Stage currentStage = (Stage) userTextField.getScene().getWindow();
            currentStage.close();
    }  
        catch (IOException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "No se pudo abrir la ventana de Login.");
    }
}

}
