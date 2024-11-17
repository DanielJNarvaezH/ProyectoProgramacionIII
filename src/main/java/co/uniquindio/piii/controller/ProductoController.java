package co.uniquindio.piii.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import co.uniquindio.piii.App;
import co.uniquindio.piii.exceptions.ProductoSinNombreException;
import co.uniquindio.piii.model.CategoriaProducto;
import co.uniquindio.piii.model.EstadoProducto;
import co.uniquindio.piii.model.Producto;
import co.uniquindio.piii.model.Tienda;
import co.uniquindio.piii.model.UsuarioActivo;
import co.uniquindio.piii.model.Vendedor;
import co.uniquindio.piii.utilities.Persistencia;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
public class ProductoController {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPrecio;
    @FXML
    private ComboBox<CategoriaProducto> cbCategoria;
    @FXML
    private ImageView imageViewProducto;
    @FXML
    private Button btSeleccionarImagen;
    @FXML
    private Button btnAnadir;
    @FXML
    private Button btnAtras;

    private Tienda tienda = Tienda.getInstance("MiTienda"); // Instancia de la tienda
    private static final ResourceBundle config = ResourceBundle.getBundle("archivosProperties.config");
    private static final String RUTA_PRODUCTOS_XML = config.getString("rutaProductosXML");
    private static final String RUTA_PRODUCTOS_BIN = config.getString("rutaProductosBin");
   
       
    
    private void limpiarCampos() {
        txtDescripcion.clear();
        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
        cbCategoria.getSelectionModel().clearSelection();
        if(imageViewProducto != null){
            imageViewProducto = null;
        }
    }

    
    @FXML
void regresarVentana(MouseEvent event) {
    try {
            // Cargar la ventana de registro de productos
            Parent root = FXMLLoader.load(App.class.getResource("MenuGeneral.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Menu General");
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

@SuppressWarnings("unused")
@FXML
void agregarProducto(MouseEvent event) throws IOException {
    Vendedor vendedorActual = UsuarioActivo.getInstance().getVendedor();
    Producto producto = new Producto();
    String codigo = txtCodigo.getText();
    String nombre = txtNombre.getText();
    int precio = Integer.parseInt(txtPrecio.getText());
    String descripcion = txtDescripcion.getText();
    CategoriaProducto categoria = cbCategoria.getSelectionModel().getSelectedItem();
    LocalDateTime fecha = LocalDateTime.now();
    Image imagen = imageViewProducto.getImage();
    
    if (nombre.isEmpty() || codigo.isEmpty() || precio <= 0 || descripcion.isEmpty()|| categoria == null || imagen == null) {
        showAlert(AlertType.ERROR, "Error de registro del producto", "Por favor, completa todos los campos.");
    } else if (nombre == null) {
        @SuppressWarnings("unused")
        ProductoSinNombreException exception = new ProductoSinNombreException("Excepción debido a que el producto no posee nombre");
        showAlert(AlertType.ERROR, "Error de registro", "El nombre del producto no se encuentra.");
    } else {
        // Crear el nuevo producto
        Producto nuevoProducto = new Producto(nombre,descripcion,codigo,fecha,EstadoProducto.PUBLICADO,categoria,precio,vendedorActual,imagen);

        //Añadir a la lista de productos
        tienda.getProductos().add(nuevoProducto);
        vendedorActual.getProductos().add(nuevoProducto);
        vendedorActual.publicarProducto(producto);

        Persistencia.salvarRecursoSerializadoXML(RUTA_PRODUCTOS_XML, nuevoProducto);
        //Arreglar método, para que se sobreescriba
        Persistencia.serializarObjetoBinario(RUTA_PRODUCTOS_BIN, nuevoProducto);
        System.out.println("Serialización registro de producto completada");

        showAlert(AlertType.INFORMATION, "Registro exitoso", "Producto registrado correctamente.");
        limpiarCampos();
    }
}
    
    // Método para seleccionar y mostrar la imagen
    @FXML
    private void seleccionarImagen(ActionEvent event) {
        // Crear un FileChooser
        FileChooser fileChooser = new FileChooser();
        // Filtros para que solo se muestren imágenes (.jpg, .png)
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Archivos de imagen (.jpg, *.png)", ".jpg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);
        // Mostrar el diálogo de selección de archivo
        File file = fileChooser.showOpenDialog(new Stage());
        // Verificar que el archivo no sea null (usuario no canceló)
        if (file != null) {
            // Crear una imagen a partir del archivo seleccionado
            Image image = new Image(file.toURI().toString());
            // Mostrar la imagen en el ImageView
            imageViewProducto.setImage(image);
        }
    }
    @FXML
    void initialize() {
        txtPrecio.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("\\d")) {
                event.consume();
            }
        });
        assert btnAtras != null : "fx:id=\"btnAtras\" was not injected: check your FXML file 'producto.fxml'.";
        assert txtDescripcion != null
                : "fx:id=\"txtDescripcion\" was not injected: check your FXML file 'Producto.fxml'.";
        assert txtCodigo != null : "fx:id=\"txtCodigo\" was not injected: check your FXML file 'Producto.fxml'.";
        assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'Producto.fxml'.";
        assert txtPrecio != null : "fx:id=\"txtPrecio\" was not injected: check your FXML file 'Producto.fxml'.";
        assert cbCategoria != null : "fx:id=\"cbCategoria\" was not injected: check your FXML file 'Producto.fxml'.";
        assert imageViewProducto != null
                : "fx:id=\"imageViewProducto\" was not injected: check your FXML file 'Producto.fxml'.";
        assert btSeleccionarImagen != null
                : "fx:id=\"btSeleccionarImagen\" was not injected: check your FXML file 'Producto.fxml'.";
        assert btnAnadir != null : "fx:id=\"btnAnadir\" was not injected: check your FXML file 'Producto.fxml'.";

        //ComboBox seteado con el enum CategoriaProducto
        cbCategoria.getItems().addAll(CategoriaProducto.values());
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }
}
