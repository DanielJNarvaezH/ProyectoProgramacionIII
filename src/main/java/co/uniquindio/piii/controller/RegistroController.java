package co.uniquindio.piii.controller;

import java.io.IOException;
import java.util.ResourceBundle;

import co.uniquindio.piii.App;
import co.uniquindio.piii.exceptions.EmailYaRegistradoException;
import co.uniquindio.piii.model.Registro;
import co.uniquindio.piii.model.Tienda;
import co.uniquindio.piii.model.Vendedor;
import co.uniquindio.piii.utilities.EjemploLog;
import co.uniquindio.piii.utilities.Persistencia;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    private TextField addressTextField;
    @FXML
    private TextField IdTextField;

    @FXML
    private Button btnIrALogin;
    @FXML
    private PasswordField passwordTextField;

    private Tienda tienda = Tienda.getInstance("MiTienda"); // Instancia de la tienda
    private static final ResourceBundle config = ResourceBundle.getBundle("archivosProperties.config");
    private static final String RUTA_VENDEDORES_XML = config.getString("rutaVendedoresXML");
    private static final String RUTA_VENDEDORES_BIN = config.getString("rutaVendedoresBin");
    @FXML
    private void handleRegister() throws IOException {
        String nombre = nameTextField.getText();
        String username = userTextField.getText();
        String email = emailTextField.getText();
        String direccion = addressTextField.getText();
        String id = IdTextField.getText();
        String password = passwordTextField.getText();
    
        if (nombre.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()|| direccion.isEmpty() || id.isEmpty()) {
            showAlert(AlertType.ERROR, "Error de registro", "Por favor, completa todos los campos.");
        } else if (Registro.esUsuarioDuplicado(username)) {
            showAlert(AlertType.ERROR, "Error de registro", "El nombre de usuario ya está registrado.");
        } else if (Registro.esCorreoDuplicado(email)) {  // Validación de correo duplicado
            showAlert(AlertType.ERROR, "Error de registro", "El correo electrónico ya está registrado.");
        } else {
            try {
                // Crear el nuevo vendedor
                Vendedor nuevoVendedor = new Vendedor(nombre, username, password, email, direccion, id);
    
                // Guardar los datos de registro y añadir a la lista de vendedores
                Registro.guardarDatosRegistro(nombre, username, email, password,direccion, id);
                tienda.getVendedores().add(nuevoVendedor);

                Persistencia.salvarRecursoSerializadoXML(RUTA_VENDEDORES_XML, nuevoVendedor);
                //Arreglar método, para que se sobreescriba
                Persistencia.serializarObjetoBinario(RUTA_VENDEDORES_BIN, nuevoVendedor);
                System.out.println("Serialización registro completada");
    
                showAlert(AlertType.INFORMATION, "Registro exitoso", "Usuario registrado correctamente.");
                EjemploLog.logInfo("El usuario"+ nuevoVendedor.getNombre()+ "realizó su registro en la App, y se dirige a la escena de login");
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
        addressTextField.clear();
        IdTextField.clear();
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
            EjemploLog.logInfo("El usuario se dirige hacia la ventana de login");
            
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

    @FXML
    void initialize() {
        
        IdTextField.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("\\d")) {
                event.consume();
            }
        });

        assert nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'registro.fxml'.";
        assert userTextField != null : "fx:id=\"userTextField\" was not injected: check your FXML file 'registro.fxml'.";
        assert emailTextField != null : "fx:id=\"emailTextField\" was not injected: check your FXML file 'registro.fxml'.";
        assert addressTextField != null : "fx:id=\"addressTextField\" was not injected: check your FXML file 'registro.fxml'.";
        assert IdTextField != null : "fx:id=\"IdTextField\" was not injected: check your FXML file 'registro.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'registro.fxml'.";
        assert btnIrALogin != null : "fx:id=\"btnIrALogin\" was not injected: check your FXML file 'registro.fxml'.";

    }
}
