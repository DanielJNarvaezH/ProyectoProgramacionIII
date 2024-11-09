package co.uniquindio.piii.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuGeneralController {

    @FXML
    private Button btnChatContacto;
    
    @FXML
    private Button btnComentario;
    
    @FXML
    private Button btnProducto;
    
    @FXML
    private Button btnVendedor;

    @FXML
    private void handleChatContacto() {
        // Lógica para redirigir a la vista de chat de contacto
        System.out.println("Ir a chat de contacto.");
    }

    @FXML
    private void handleComentario() {
        // Lógica para redirigir a la vista de comentarios
        System.out.println("Ir a comentarios.");
    }

    @FXML
    private void handleProducto() {
        // Lógica para redirigir a la vista de productos
        System.out.println("Ir a productos.");
    }

    @FXML
    private void handleVendedor() {
        // Lógica para redirigir a la vista de vendedores
        System.out.println("Ir a vendedores.");
    }
}
