package co.uniquindio.piii.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button languageButton; // Botón para cambiar idioma
    
    @FXML
    private Label welcomeLabel;
    
    @FXML
    private Label usernameLabel;
    
    @FXML
    private Label passwordLabel;
    
    @FXML
    private Button loginButton;


    private ResourceBundle messages;

    // Inicializar el idioma predeterminado (por ejemplo, en español)
    public void initialize() {
        cargarIdioma("es"); // Predeterminado a español
    }

    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (validarCredenciales(username, password)) {
            showAlert(AlertType.INFORMATION, messages.getString("login.success"), 
                      messages.getString("welcome") + ", " + username);
        } else {
            showAlert(AlertType.ERROR, messages.getString("login.failed"), 
                      messages.getString("error.credentials"));
        }
    }

    private boolean validarCredenciales(String username, String password) {
        String rutaArchivo = "registros.txt";
        String linea;

        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("%%");
                if (partes.length == 2) {
                    String usuarioRegistrado = partes[0];
                    String contrasenaRegistrada = partes[1];

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

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }

    // Cargar archivo de idioma
    public void cambiarIdioma() {
        String idiomaActual = messages.getLocale().getLanguage();
        if ("es".equals(idiomaActual)) {
            cargarIdioma("en");
        } else {
            cargarIdioma("es");
        }
        actualizarTextos();
    }

    private void cargarIdioma(String idioma) {
        Locale locale = new Locale(idioma);
        messages = ResourceBundle.getBundle("archivosProperties.messages", locale);

    }

    private void actualizarTextos() {
        usernameField.setPromptText(messages.getString("username"));
        passwordField.setPromptText(messages.getString("password"));
        languageButton.setText(messages.getString("language.button"));
        welcomeLabel.setText(messages.getString("welcome"));
        usernameLabel.setText(messages.getString("username.label"));
        passwordLabel.setText(messages.getString("password.label"));
        loginButton.setText(messages.getString("login.button"));
    }
    
}
