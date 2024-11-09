package co.uniquindio.piii.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import co.uniquindio.piii.exceptions.*;

public class Tienda implements Serializable {

    private static Tienda instance;
    private List<Vendedor> contactos = new ArrayList<>();
    private ArrayList<Vendedor> vendedores;
    private ArrayList<Producto> productos;
    private Map<Producto, Publicacion> publicacionesDeProductos; // Mapa para asociar productos con publicaciones
    private String nombre;

    public static Tienda getInstance(String nombre) {
        if (instance == null) {
            instance = new Tienda(nombre);
        }
        return instance;
    }

    public Tienda(String nombre) {
        this.vendedores = new ArrayList<>();
        this.nombre = nombre;
        this.productos = new ArrayList<>();
        this.contactos = new ArrayList<>();
        this.publicacionesDeProductos = new HashMap<>(); // Inicializar el mapa de publicaciones
    }

    public ArrayList<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(ArrayList<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        
        // Crear y asociar una nueva publicación para el producto
        Publicacion publicacion = new Publicacion("Publicación para el producto: " + producto.getTitulo(), producto);
        publicacionesDeProductos.put(producto, publicacion);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Tienda [vendedores=" + vendedores + ", nombre=" + nombre + "]";
    }

    public void agregarVendedor(Vendedor vendedor) {
        vendedores.add(vendedor);
    }

    public ArrayList<Producto> obtenerProductosPorEstado(EstadoProducto estado) {
        ArrayList<Producto> productosFiltrados = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getEstadoProducto() == estado) {
                productosFiltrados.add(producto);
            }
        }
        return productosFiltrados;
    }

    // Método adaptado para agregar un comentario a la publicación asociada a un producto
    public void agregarComentarioProducto(String usuario, Producto producto, String texto) throws ProductoNoEncontradoException {
        // Verificar si el producto tiene una publicación asociada
        Publicacion publicacion = publicacionesDeProductos.get(producto);
        
        if (publicacion == null) {
            throw new ProductoNoEncontradoException("El producto no tiene una publicación asociada en la tienda.");
        }

        // Crear y agregar el comentario a la publicación
        Comentario comentario = new Comentario(texto, LocalDate.now(), usuario);
        publicacion.agregarComentario(comentario);
    }

    public void procesarSolicitudesPendientes(Vendedor vendedor) {
        for (Contacto solicitud : vendedor.getSolicitudesPendientes()) {
            // Lógica para aceptar o rechazar automáticamente las solicitudes según ciertas condiciones
        }
    }

    // Método para obtener la publicación asociada a un producto
    public Publicacion obtenerPublicacionProducto(Producto producto) {
        return publicacionesDeProductos.get(producto);
    }
}
