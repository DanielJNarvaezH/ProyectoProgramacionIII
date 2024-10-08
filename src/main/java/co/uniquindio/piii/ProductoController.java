package co.uniquindio.piii;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ProductoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<?> cbVendedor;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private ComboBox<?> cbCategoria;

    @FXML
    private ImageView imageViewProducto;

    @FXML
    private Button btSeleccionarImagen;

    @FXML
    private Button btnAnadir;

    // Método para seleccionar y mostrar la imagen
    @FXML
    private void seleccionarImagen(ActionEvent event) {
        // Crear un FileChooser
        FileChooser fileChooser = new FileChooser();

        // Filtros para que solo se muestren imágenes (.jpg, .png)
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Archivos de imagen (*.jpg, *.png)", "*.jpg", "*.png");
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
        assert cbVendedor != null : "fx:id=\"cbVendedor\" was not injected: check your FXML file 'Producto.fxml'.";
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

    }
}
