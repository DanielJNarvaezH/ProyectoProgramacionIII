package co.uniquindio.piii.controller;

import co.uniquindio.piii.model.Producto;
import co.uniquindio.piii.model.Vendedor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VendedorController {

    @FXML
    private VBox productosContainer; // Contenedor donde se agregarán los productos
    @FXML
    private Button btnMostrarProductos; // Botón "Mostrar más"
    
    private List<Producto> listaProductos;  // Lista de productos a cargar
    private int productosMostrados = 0;     // Controla cuántos productos se han mostrado
    private final int PRODUCTOS_POR_PAGINA = 3;  // Cantidad de productos que se muestran por clic
    private Vendedor vendedor;

    @FXML
    public void initialize() {
        vendedor = new Vendedor("Mario", null, null, null);
        Producto producto1 = new Producto("Balón", "Balón para futbol de tamaño mediano", LocalDate.now(), 0, vendedor, new File("src/imagenes/images/producto1.jpg"));
        Producto producto2 = new Producto("Mesa", "Mesa para el piano", LocalDate.now(), 0, vendedor, new File("src/imagenes/images/producto2.png"));
        Producto producto3 = new Producto("Funda", "Funda de tamaño mediano", LocalDate.now(), 0, vendedor, new File("src/imagenes/images/producto3.png"));
        Producto producto4 = new Producto("Zapatos", "Zapatos deportivos", LocalDate.now(), 0, vendedor, new File("src/imagenes/images/producto4.png"));
        Producto producto5 = new Producto("Gafas", "Gafas de sol", LocalDate.now(), 0, vendedor, new File("src/imagenes/images/producto5.png"));
        vendedor.publicarProducto(producto5);
        vendedor.publicarProducto(producto4);
        vendedor.publicarProducto(producto3);
        vendedor.publicarProducto(producto2);
        vendedor.publicarProducto(producto1);
        // Inicializar la lista de productos
        listaProductos = vendedor.getProductos();
        
        // Cargar los primeros productos
        cargarProductos();

        // Configurar la acción del botón "Mostrar más"
        btnMostrarProductos.setOnAction(event -> cargarProductos());
    }

    private void cargarProductos() {
        // Determina hasta cuántos productos se van a mostrar en esta carga
        int hasta = Math.min(productosMostrados + PRODUCTOS_POR_PAGINA, listaProductos.size());

        // Añadir los productos al VBox
        for (int i = productosMostrados; i < hasta; i++) {
            Producto producto = listaProductos.get(i);
            agregarProductoAInterfaz(producto);
        }

        productosMostrados = hasta;

        // Oculta el botón si ya no hay más productos que mostrar
        if (productosMostrados >= listaProductos.size()) {
            btnMostrarProductos.setVisible(false);
        }
    }

    private void agregarProductoAInterfaz(Producto producto) {
        // Crear un HBox que contenga la información del producto
        HBox productoBox = new HBox();
        productoBox.setSpacing(10);

        // Cargar la imagen del producto desde el archivo local
        ImageView imageView = new ImageView(new Image(producto.getImagen().toURI().toString()));
        imageView.setFitHeight(160);
        imageView.setFitWidth(200);

        // Crear un VBox para los detalles del producto
        VBox detallesBox = new VBox();
        detallesBox.setSpacing(10);

        // Crear los Labels y TextArea para los detalles del producto
        Label tituloLabel = new Label("Título: " + producto.getTitulo());
        TextArea descripcionArea = new TextArea(producto.getDescripcion());
        descripcionArea.setEditable(false);
        descripcionArea.setWrapText(true);
        descripcionArea.setPrefHeight(70);

        Label fechaLabel = new Label("Fecha de Publicación: " + producto.getFechaPublicacion().toString());
        Label likesLabel = new Label("Me gusta: " + producto.getNumLikes());
        Label comentariosLabel = new Label("Comentarios: " + producto.getComentarios().size());

        // Botones "Me gusta" y "Comentario"
        Button btnMeGusta = new Button("Dar Me gusta");
        Button btnComentario = new Button("Hacer Comentario");

        // Agregar los componentes al VBox de detalles
        detallesBox.getChildren().addAll(tituloLabel, descripcionArea, fechaLabel, likesLabel, comentariosLabel, btnMeGusta, btnComentario);

        // Añadir la imagen y los detalles al HBox principal
        productoBox.getChildren().addAll(imageView, detallesBox);

        // Añadir este HBox al contenedor principal (VBox)
        productosContainer.getChildren().add(productoBox);
    }

}
