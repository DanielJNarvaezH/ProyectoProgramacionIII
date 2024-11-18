package co.uniquindio.piii.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import co.uniquindio.piii.App;
import co.uniquindio.piii.model.CategoriaProducto;
import co.uniquindio.piii.model.Producto;
import co.uniquindio.piii.model.UsuarioActivo;
import co.uniquindio.piii.model.Vendedor;
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

    
    @FXML
void regresarVentana(MouseEvent event) {
    try {
        App.setRoot("MenuGeneral");
    } catch (IOException e) {
        e.printStackTrace(); // Esto imprimirá el error en la consola
    }
}

@FXML
void agregarProducto(MouseEvent event) throws IOException {
    Vendedor vendedorActual = UsuarioActivo.getInstance().getVendedor();
    Producto producto = new Producto();
    producto.setCodigo(txtCodigo.getText());
    producto.setTitulo(txtNombre.getText());
    int precio = Integer.parseInt(txtPrecio.getText());
    producto.setPrecio(precio); 
    producto.setDescripcion(txtDescripcion.getText());
    producto.setVendedor(vendedorActual);
    producto.setCategoria(cbCategoria.getValue());
    producto.setFechaPublicacion(LocalDateTime.now());
    producto.setImagen(imageViewProducto.getImage());

    vendedorActual.publicarProducto(producto);

    JOptionPane.showMessageDialog(null, "Producto creado exitosamente:\n" +
                "Nombre: " + producto.getTitulo() + "\n" +
                "Código: " + producto.getCodigo() + "\n" +
                "Precio: " + producto.getPrecio() + "\n" +
                "Descripción: " + producto.getDescripcion(),
                "Creación de Producto", JOptionPane.INFORMATION_MESSAGE);
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
}
