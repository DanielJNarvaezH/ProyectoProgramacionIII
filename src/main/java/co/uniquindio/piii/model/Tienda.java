package co.uniquindio.piii.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Tienda implements Serializable {

    private static Tienda instance;
    private List<Vendedor> contactos = new ArrayList<>();
    private ArrayList<Vendedor> vendedores;
    private ArrayList<Producto> productos;
    private Map<Producto, Publicacion> publicacionesDeProductos; // Mapa para asociar productos con publicaciones
    private String nombre;
    private LinkedList<Vendedor> vendedorActual;

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
        this.vendedorActual = new LinkedList<>();
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

    public void removerVendedor(Vendedor vendedor) {
        vendedores.remove(vendedor);
    }

    public LinkedList<Vendedor> getVendedorActual() {
        return vendedorActual;
    }

    public void setVendedorActual(LinkedList<Vendedor> vendedorActual) {
        this.vendedorActual = vendedorActual;
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

    public Publicacion obtenerPublicacionProducto(Producto producto) {
        return publicacionesDeProductos.get(producto);
    }

    // Método para obtener un vendedor por su nombre
    public Vendedor obtenerVendedorPorNombre(String nombreVendedor) {
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getNombre().equalsIgnoreCase(nombreVendedor)) {
                return vendedor;
            }
        }
        return null; // Si no se encuentra, retorna null
    }

    // Método para obtener los detalles de la publicación asociada a un producto
    public String obtenerDetallesPublicacion(Producto producto) {
        Publicacion publicacion = publicacionesDeProductos.get(producto);
        return (publicacion != null) ? publicacion.mostrarDetalles() : "No hay publicación para este producto.";
    }

    // Método para obtener un producto por su nombre
    public Producto obtenerProductoPorNombre(String nombreProducto) {
        for (Producto producto : productos) {
            if (producto.getTitulo().equalsIgnoreCase(nombreProducto)) {
                return producto;
            }
        }
        return null; // Si no se encuentra, retorna null
    }
}