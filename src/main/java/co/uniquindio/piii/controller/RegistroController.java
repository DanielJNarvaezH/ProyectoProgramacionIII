package co.uniquindio.piii.controller;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import co.uniquindio.piii.App;
import co.uniquindio.piii.model.Registro;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    @FXML
private void abrirVentanaLogin() {
    try {
        // Depuración: Imprimir la ruta para confirmar que es correcta
        System.out.println(App.class.getResource("login.fxml")); 

        // Usar la misma lógica que en la clase App para cargar el FXML
        Parent root = FXMLLoader.load(App.class.getResource("login.fxml")); // Asegúrate de que esta ruta sea correcta
        
        // Abrir una nueva ventana (Stage)
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
        
        // Cerrar la ventana actual si es necesario
        Stage currentStage = (Stage) userTextField.getScene().getWindow();
        currentStage.close();
    } catch (IOException e) {
        e.printStackTrace();
        showAlert(AlertType.ERROR, "Error", "No se pudo abrir la ventana de Login.");
    }
}


}