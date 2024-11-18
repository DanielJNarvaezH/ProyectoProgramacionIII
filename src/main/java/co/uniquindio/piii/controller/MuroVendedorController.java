package co.uniquindio.piii.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import co.uniquindio.piii.App;
import co.uniquindio.piii.model.Producto;
import co.uniquindio.piii.model.UsuarioActivo;
import co.uniquindio.piii.model.Vendedor;
import co.uniquindio.piii.utilities.EjemploLog;
import co.uniquindio.piii.utilities.Persistencia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MuroVendedorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelVendedor;

    @FXML
    private TextField txtVendedor;

    @FXML
    private Button btnBuscarVendedor;

    @FXML
    private Button btnComentarios;

    @FXML
    private TextArea txtProducto;

    @FXML
    private Label labelLikes;

    @FXML
    private Label labelFechaHora;

    @FXML
    private Button btnRegresar;

    @FXML
    private VBox productContainer;

    private static final ResourceBundle config = ResourceBundle.getBundle("archivosProperties.config");
    private static final String RUTA_PRODUCTOS_XML = config.getString("rutaProductosXML");

    @FXML
    void buscarVendedor(ActionEvent event) {

    }

    @FXML
    void regresarVentana(ActionEvent event) {
        try {
            // Cargar la ventana de registro de productos
            Parent root = FXMLLoader.load(App.class.getResource("MenuGeneral.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Menu General");
            stage.setScene(new Scene(root));
            stage.show();
            EjemploLog.logInfo("El usuario" + UsuarioActivo.getInstance().getVendedor().getNombre()+ "cambió de escena de muro hacia el Menu General");

            // Cerrar la ventana actual
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana del menu principal.");
        }
    }

    @FXML
    void comentariosView(ActionEvent event) {
        try {
            // Cargar la ventana de registro de productos
            Parent root = FXMLLoader.load(App.class.getResource("comentarios.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Comentarios Muro Vendedor");
            stage.setScene(new Scene(root));
            stage.show();

            // Cerrar la ventana actual
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana de comentarios.");
        }
    }
    
    public void mostrarProductos() {
        try {
            // Cargar productos desde el archivo XML como List<Object>
            List<Object> objetos = Persistencia.cargarRecursoSerializadoXML(RUTA_PRODUCTOS_XML);
            System.out.println("Productos cargados desde XML: " + objetos.size() + " objetos.");
    
            // Convertir List<Object> a List<Producto>
            List<Producto> productos = objetos.stream()
                    .filter(obj -> obj instanceof Producto) // Filtrar solo los objetos de tipo Producto
                    .map(obj -> (Producto) obj) // Convertir a Producto
                    .collect(Collectors.toList());
            System.out.println("Productos filtrados: " + productos.size() + " productos.");
    
            // Obtener el vendedor actual
            Vendedor vendedor = UsuarioActivo.getInstance().getVendedor();
            System.out.println("Vendedor actual: " + vendedor.getNombre());
    
            // Filtrar productos por el vendedor
            List<Producto> productosFiltrados = productos.stream()
                    .filter(producto -> producto.getVendedor().getNombre().equals(vendedor.getNombre()))
                    .collect(Collectors.toList());
            System.out.println("Productos filtrados por el vendedor: " + productosFiltrados.size() + " productos.");
    
            System.out.println("Cargando lista de productos");
            // Limpiar contenedor antes de añadir productos
            productContainer.getChildren().clear();
    
            // Mostrar los productos filtrados
            if (productosFiltrados.isEmpty()) {
                System.out.println("No hay productos para mostrar.");
            } else {
                for (Producto producto : productosFiltrados) {
                    // Crear un contenedor horizontal para la imagen, detalles y botones
                    System.out.println("Procesando solicitud para el producto: " + producto.getTitulo());
                    HBox productBox = new HBox(10);
                    productBox.setStyle(
                            "-fx-padding: 10; -fx-border-color: lightgray; -fx-border-radius: 5; -fx-background-color: #f9f9f9;");
                    productBox.setSpacing(10);
    
                    // Crear ImageView
                    ImageView imageView = new ImageView();
                    imageView.setFitWidth(100);
                    imageView.setFitHeight(100);
                    imageView.setPreserveRatio(true);
                    Image image = producto.getImagen(); // Ruta de la imagen
                    imageView.setImage(image);
    
                    // Crear TextArea
                    TextArea textArea = new TextArea();
                    textArea.setEditable(false);
                    textArea.setWrapText(true);
                    textArea.setText("Nombre: " + producto.getTitulo() + "\n" +
                            "Código: " + producto.getCodigo() + "\n" +
                            "Precio: $" + producto.getPrecio() + "\n" +
                            "Descripción: " + producto.getDescripcion());
                    HBox.setHgrow(textArea, Priority.ALWAYS);
    
                    // Crear botones
                    Button commentButton = new Button("Comentario");
                    commentButton.setStyle(
                            "-fx-font-size: 16px; " + // Tamaño de la fuente
                                    "-fx-padding: 10 30; " + // Relleno interno (vertical y horizontal)
                                    "-fx-min-width: 150px; " + // Ancho mínimo
                                    "-fx-min-height: 25px; " + // Altura mínima
                                    "-fx-text-fill: black; " + // Color del texto
                                    "-fx-border-radius: 4; " + // Bordes redondeados
                                    "-fx-background-radius: 4;" // Bordes del fondo
                    );
                    commentButton.setOnAction(e -> handleComment(producto));
    
                    Button likeButton = new Button("Me gusta");
                    likeButton.setStyle(
                            "-fx-font-size: 16px; " +
                                    "-fx-padding: 10 30; " +
                                    "-fx-min-width: 150px; " +
                                    "-fx-min-height: 25px; " +
                                    "-fx-text-fill: black; " +
                                    "-fx-border-radius: 4; " +
                                    "-fx-background-radius: 4;");
                    likeButton.setOnAction(e -> handleLike(producto));
    
                    // Agregar los elementos al HBox
                    productBox.getChildren().addAll(imageView, textArea, commentButton, likeButton);
    
                    // Agregar el HBox al VBox principal
                    productContainer.getChildren().add(productBox);
                    System.out.println("Mostrando producto: " + producto.getTitulo());
                    EjemploLog.logInfo("El usuario" + UsuarioActivo.getInstance().getVendedor().getNombre()+ " accedió a su muro de productos");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudieron cargar los productos.");
        }
    }

    // Métodos para manejar los eventos de los botones
    private void handleComment(Producto producto) {
        System.out.println("Comentario para: " + producto.getTitulo());
        // Aquí puedes abrir una ventana o un campo de texto para añadir un comentario
    }

    private void handleLike(Producto producto) {
        System.out.println("¡Te gusta el producto: " + producto.getTitulo() + "!");
        // Aquí puedes incrementar el contador de likes o realizar otra acción
    }

    @FXML
    void initialize() {
        Vendedor vendedor = UsuarioActivo.getInstance().getVendedor();
        labelVendedor.setText(vendedor.getNombre());
        //ArrayList<Producto> productos = vendedor.getProductos();
        mostrarProductos();
        assert labelVendedor != null
                : "fx:id=\"labelVendedor\" was not injected: check your FXML file 'muroProductos.fxml'.";
        assert txtVendedor != null
                : "fx:id=\"txtVendedor\" was not injected: check your FXML file 'muroProductos.fxml'.";
        assert btnBuscarVendedor != null
                : "fx:id=\"btnBuscarVendedor\" was not injected: check your FXML file 'muroProductos.fxml'.";
        assert txtProducto != null
                : "fx:id=\"txtProducto\" was not injected: check your FXML file 'muroProductos.fxml'.";
        assert labelLikes != null : "fx:id=\"labelLikes\" was not injected: check your FXML file 'muroProductos.fxml'.";
        assert labelFechaHora != null
                : "fx:id=\"labelFechaHora\" was not injected: check your FXML file 'muroProductos.fxml'.";
        assert btnRegresar != null
                : "fx:id=\"btnBuscarVendedor\" was not injected: check your FXML file 'muroProductos.fxml'.";
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }
}
