package co.uniquindio.piii;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import co.uniquindio.piii.controller.ChatContactoController;
import co.uniquindio.piii.model.Cliente;
import co.uniquindio.piii.model.Servidor; // Asegúrate de importar la clase Servidor

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    private static Cliente cliente;
    private static Servidor servidor;  // Agrega la variable para el servidor

    /**
     * Inicializa el cliente con el usuario proporcionado y lo conecta al servidor.
     * 
     * @param usuario el nombre del usuario
     * @throws IOException si ocurre un error al conectar al servidor
     */
    public static void inicializarCliente(String usuario) throws IOException {
        if (cliente == null) {
            System.out.println("Inicializando el cliente con el usuario: " + usuario); // Log para depurar
            cliente = new Cliente(usuario);
            cliente.conectar();
            System.out.println("Cliente conectado al servidor."); // Log para confirmar conexión
        } else {
            System.out.println("El cliente ya está inicializado.");
        }
    }
    

    /**
     * Obtiene la instancia actual del cliente.
     * 
     * @return la instancia del cliente
     */
    public static Cliente getCliente() {
        return cliente;
    }

    /**
     * Inicializa y arranca el servidor en un hilo separado para que no bloquee la interfaz.
     */
    public static void inicializarServidor() {
        try {
            servidor = new Servidor();  // Crear el servidor
            servidor.iniciar();  // Iniciar el servidor
            System.out.println("Servidor iniciado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Inicializamos el servidor antes de cargar la vista de login
        inicializarServidor();
        
        // Cargamos la vista de login como primera pantalla
        scene = new Scene(loadFXML("login"), 400, 300);
        stage.setScene(scene);
        stage.setTitle("Registro de Usuario");
        stage.show();
    }

    /**
     * Cambia la raíz de la escena actual al FXML especificado.
     * 
     * @param fxml el nombre del archivo FXML sin la extensión
     * @throws IOException si ocurre un error al cargar el FXML
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Muestra la ventana de chat de contacto con el cliente configurado.
     * 
     * @param cliente la instancia del cliente para el chat
     * @throws IOException si ocurre un error al cargar el FXML
     */
    public static void mostrarVentanaChatContacto(Cliente cliente) throws IOException {
        if (cliente == null) {
            throw new IllegalStateException("El cliente no está inicializado");
        }
        System.out.println("Abriendo la ventana de chat para el cliente: ");
    
        FXMLLoader loader = new FXMLLoader(App.class.getResource("chatcontacto.fxml"));
        Parent root = loader.load();
    
        // Obtén el controlador del FXML
        ChatContactoController controller = loader.getController();
        // Configura el cliente
        controller.setCliente(cliente);
    
        Stage stage = new Stage();
        stage.setTitle("Chat de Contacto");
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }
}
