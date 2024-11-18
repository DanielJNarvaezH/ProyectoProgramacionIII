package co.uniquindio.piii.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import co.uniquindio.piii.App;
import co.uniquindio.piii.utilities.EjemploLog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import co.uniquindio.piii.model.*;

public class MenuGeneralController {

    @FXML
    private Button btnChatContacto;
    
    @FXML
    private Button btnMostrarVentasTotalesMes;

    @FXML
    private ComboBox<Month> comboBoxMeses;

    @FXML
    private Label lblResultados;

    private ArrayList<Vendedor> vendedores;
    
    @FXML
    private Button btnProducto;

    @FXML
    private Label labelPrueba;

    @FXML
    private Button btnMuro;

    @FXML
    private void handleChatContacto(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(App.class.getResource("chatContacto.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Chat de Contacto");
            stage.setScene(new Scene(root));
            stage.show();
            EjemploLog.logInfo("El usuario" + UsuarioActivo.getInstance().getVendedor().getNombre()+ " accedió a la escena de chat desde el Menu General");
            
            // Cerrar la ventana actual
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana de Chat de Contacto.");
        }
    }
    

    @FXML
    private void handleComentario() {
        // Lógica para redirigir a la vista de comentarios
        System.out.println("Ir a comentarios.");
    }

    @FXML
    private void handleProducto(ActionEvent event) {
        try {
            // Cargar la ventana de registro de productos
            Parent root = FXMLLoader.load(App.class.getResource("producto.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Productos vendedor");
            stage.setScene(new Scene(root));
            stage.show();
            EjemploLog.logInfo("El usuario" + UsuarioActivo.getInstance().getVendedor().getNombre()+ " accedió a la escena de producto desde el Menu General");

            // Cerrar la ventana actual
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana de productos.");
        }
    }

    @FXML
    private void muroVendedor(MouseEvent event) {
        try {
            // Cargar la ventana del muro de productos
            Parent root = FXMLLoader.load(App.class.getResource("muroProductos.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Muro vendedor");
            stage.setScene(new Scene(root));
            stage.show();
            EjemploLog.logInfo("El usuario" + UsuarioActivo.getInstance().getVendedor().getNombre()+ "accedió a la escena del muro desde el Menu General");

            // Cerrar la ventana actual
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana de Registro.");
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }

    
    @FXML
    void mostrarVentasTotales(ActionEvent event) {

        Month mesSeleccionado = comboBoxMeses.getValue();

        if (mesSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona un mes.");
        } else {
            String resultado = Estadistica.calcularVentasPorMes(vendedores, mesSeleccionado);
            lblResultados.setText(resultado);
            JOptionPane.showMessageDialog(null, resultado, "Ventas del Mes", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    @FXML
    void initialize() {
        String nombreUsuario = UsuarioActivo.getInstance().getVendedor().getNombre();
        labelPrueba.setText(nombreUsuario);
        assert btnChatContacto != null : "fx:id=\"btnChatContacto\" was not injected: check your FXML file 'MenuGeneral.fxml'.";
        assert btnProducto != null : "fx:id=\"btnProducto\" was not injected: check your FXML file 'MenuGeneral.fxml'.";
        assert btnMuro != null : "fx:id=\"btnMuro\" was not injected: check your FXML file 'MenuGeneral.fxml'.";
        comboBoxMeses.getItems().addAll(Month.values());
        vendedores = generarDatosSimulados();

    }

     /**
     * Genera datos simulados para pruebas.
     */
    private ArrayList<Vendedor> generarDatosSimulados() {

          // Crear vendedores
          Vendedor pedro = new Vendedor("Pedro", "123", "password", "pedro@gmail.com", "Calle 1", "001");
          Vendedor juan = new Vendedor("Juan", "345", "password", "juan@gmail.com", "Calle 2", "002");
  
          // Crear productos
        Producto iphone = new Producto("Iphone 11", "Smartphone de Apple", "001",
                LocalDateTime.now(), EstadoProducto.NUEVO, CategoriaProducto.TECNOLOGIA, 1000000, pedro, null);
        Producto guitarra = new Producto("Guitarra", "Instrumento musical acústico", "002",
                LocalDateTime.now(), EstadoProducto.USADO, CategoriaProducto.MUSICA, 200000, pedro, null);
        Producto portatil = new Producto("Portatil Dell", "Laptop Dell con Core i7", "003",
                LocalDateTime.now(), EstadoProducto.NUEVO, CategoriaProducto.TECNOLOGIA, 2000000, pedro, null);


        // Publicar productos
        pedro.publicarProducto(iphone);
        pedro.publicarProducto(guitarra);

        // Registrar venta
        pedro.registrarVenta(portatil);

        // Crear lista de vendedores
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        vendedores.add(pedro);
        vendedores.add(juan);
        
        return vendedores;
    }
}
