package co.uniquindio.piii.controller;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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


     // Método para guardar datos de registro sin email
    public static void guardarDatosRegistro(String nombreUsuario, String contrasena) {
        String rutaArchivo = "registros.txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            // Formato: nombreUsuario%%contrasena
            writer.write(nombreUsuario + "%%" + contrasena);
            writer.newLine();  // Agrega un salto de línea al final
            // Mensaje de confirmación en consola
            System.out.println("Datos guardados, para comprobar que el programa está funcionando correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos de registro: " + e.getMessage());
        }
    }

}