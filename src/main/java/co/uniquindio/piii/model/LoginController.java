package co.uniquindio.piii.model;


import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    // Método para manejar el inicio de sesión (por ejemplo, puedes validarlo aquí)
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Aquí puedes agregar la lógica de validación del usuario y contraseña
        if (username.equals("admin") && password.equals("1234")) {
            System.out.println("Inicio de sesión exitoso");
        } else {
            System.out.println("Credenciales incorrectas");
        }
    }

}

