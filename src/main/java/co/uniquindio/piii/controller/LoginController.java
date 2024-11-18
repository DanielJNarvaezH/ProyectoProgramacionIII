package co.uniquindio.piii.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import co.uniquindio.piii.App;
import co.uniquindio.piii.model.Registro;
import co.uniquindio.piii.model.UsuarioActivo;
import co.uniquindio.piii.model.Vendedor;

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

    /*
     * public void initialize() {
     * cargarIdioma("es"); // Predeterminado a español
     * }
     */

    @FXML
    private Button btnRegistrarse;

    @FXML
    void IrVentanaRegistro(ActionEvent event) {
        try {
            // Cargar la ventana de registro
            Parent root = FXMLLoader.load(App.class.getResource("registro.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Registro Usuario");
            stage.setScene(new Scene(root));
            stage.show();

            // Cerrar la ventana actual
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana de Registro.");
        }
    }

    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
    
        // Validar las credenciales ingresadas
        Vendedor vendedorLogueado = validarCredenciales(username, password);
    
        if (vendedorLogueado != null) {
            // Guardar el vendedor en la clase UsuarioActivo
            UsuarioActivo.getInstance().setVendedor(vendedorLogueado);
            System.out.println("Usuario autenticado: " + vendedorLogueado.getUsuario());
    
            try {
                // Inicializar el cliente con el usuario logueado
                App.inicializarCliente(vendedorLogueado.getUsuario());
    
                // Verificar que el cliente está inicializado correctamente
                if (App.getCliente() != null) {
                    System.out.println("Cliente inicializado con éxito para el usuario: " + vendedorLogueado.getUsuario());
    
                    // Comprobar si es el administrador
                    if (vendedorLogueado.getUsuario().equals("Admin") && vendedorLogueado.getContrasena().equals("12345")) {
                        System.out.println("Bienvenido, Administrador.");
                        manejarOpcionesAdministrador();
                    } else {
                        // Mostrar mensaje de éxito
                        showAlert(AlertType.INFORMATION, messages.getString("login.success"),
                                  messages.getString("welcome") + ", " + username);
    
                        // Abrir el menú general
                        abrirMenuGeneral();
                    }
    
                    // Cerrar la ventana actual
                    Stage currentStage = (Stage) loginButton.getScene().getWindow();
                    currentStage.close();
                } else {
                    // Mostrar error si el cliente no se inicializó correctamente
                    showAlert(AlertType.ERROR, "Error", "El cliente no se pudo inicializar correctamente.");
                }
            } catch (IOException e) {
                showAlert(AlertType.ERROR, "Error", "No se pudo conectar al servidor.");
                e.printStackTrace();
            }
        } else {
            // Mostrar mensaje de error si las credenciales no son válidas
            showAlert(AlertType.ERROR, messages.getString("login.failed"),
                      messages.getString("error.credentials"));
        }
    }
    

private void manejarOpcionesAdministrador() {
    boolean salir = false;
    java.util.Scanner scanner = new java.util.Scanner(System.in);
    while (!salir) {
        System.out.println("\nOpciones del Administrador:");
        System.out.println("1. Listar registros");
        System.out.println("2. Eliminar un registro");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1:
                Registro.listarRegistros();
                break;
            case 2:
                System.out.print("Ingrese el número del registro a eliminar: ");
                int numero = scanner.nextInt();
                Registro.eliminarRegistro(numero);
                break;
            case 3:
                salir = true;
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }
}
    /*private boolean validarCredenciales(String username, String password) {
        String rutaArchivo = "registros.txt";
        String linea;

        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("%%");
                if (partes.length == 4) {
                    String usuarioRegistrado = partes[1];
                    String contrasenaRegistrada = partes[3];

                    if (usuarioRegistrado.equals(username) && contrasenaRegistrada.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de registro: " + e.getMessage());
        }

        return false;
    }*/

    private Vendedor validarCredenciales(String username, String password) { 
    String rutaArchivo = "registros.txt";
    String linea;

    try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
        while ((linea = reader.readLine()) != null) {
            // Suponiendo el formato: nombre%%usuario%%email%%contrasena%%direccion%%id
            String[] partes = linea.split("%%");
            if (partes.length == 6) {
                String nombre = partes[0];
                String usuarioRegistrado = partes[1];
                String email = partes[2];
                String contrasenaRegistrada = partes[3];
                String direccion = partes[4];
                String id = partes[5];

                if (usuarioRegistrado.equals(username) && contrasenaRegistrada.equals(password)) {
                    // Crear y retornar un objeto Vendedor con los datos del archivo
                    return new Vendedor(nombre, usuarioRegistrado, contrasenaRegistrada, email, direccion,id);
                }
            }
        }
    } catch (IOException e) {
        System.out.println("Error al leer el archivo de registro: " + e.getMessage());
    }

    // Retornar null si no se encuentran credenciales válidas
    return null;
}


    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }

    private void abrirMenuGeneral() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/uniquindio/piii/MenuGeneral.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Menú General");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "No se pudo abrir el Menú General.");
        }
    }

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
        @SuppressWarnings("deprecation")
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

    @FXML
    void initialize() {
        cargarIdioma("es");
        assert welcomeLabel != null : "fx:id=\"welcomeLabel\" was not injected: check your FXML file 'login.fxml'.";
        assert usernameLabel != null : "fx:id=\"usernameLabel\" was not injected: check your FXML file 'login.fxml'.";
        assert usernameField != null : "fx:id=\"usernameField\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordLabel != null : "fx:id=\"passwordLabel\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'login.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'login.fxml'.";
        assert languageButton != null : "fx:id=\"languageButton\" was not injected: check your FXML file 'login.fxml'.";
        assert btnRegistrarse != null : "fx:id=\"btnRegistrarse\" was not injected: check your FXML file 'login.fxml'.";

    }
}
