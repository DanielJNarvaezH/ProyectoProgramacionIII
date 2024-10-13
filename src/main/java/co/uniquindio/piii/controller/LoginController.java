package co.uniquindio.piii.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    // Método para manejar el inicio de sesión
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (validarCredenciales(username, password)) {
            showAlert(AlertType.INFORMATION, "Inicio de sesión exitoso", "Bienvenido, " + username);
        } else {
            showAlert(AlertType.ERROR, "Credenciales incorrectas", "Usuario o contraseña incorrectos.");
        }
    }

    // Método para validar las credenciales del usuario
    private boolean validarCredenciales(String username, String password) {
        String rutaArchivo = "registros.txt";
        String linea;

        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            while ((linea = reader.readLine()) != null) {
                // Cada línea tiene el formato: nombreUsuario%%contrasena
                String[] partes = linea.split("%%");
                if (partes.length == 2) {
                    String usuarioRegistrado = partes[0];
                    String contrasenaRegistrada = partes[1];

                    // Comprobar si el usuario y la contraseña coinciden
                    if (usuarioRegistrado.equals(username) && contrasenaRegistrada.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de registro: " + e.getMessage());
        }

        return false;
    }

    // Método para mostrar alertas en pantalla
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
