package co.uniquindio.piii.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List; 

public class Tienda implements Serializable {

    private static Tienda instance;
    List<Vendedor> contactos = new ArrayList<>(); 


    private ArrayList<Vendedor> vendedores;
    private ArrayList<Producto> productos;

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
    public void agregarProducto(Producto producto){
        productos.add(producto);
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

    
    public void agregarComentarioProducto(String usuario, Producto producto, String texto) {
        Comentario comentario = new Comentario(texto, LocalDate.now(), usuario);
        producto.agregarComentario(comentario);
    }

    public void procesarSolicitudesPendientes(Vendedor vendedor) {
        for (Contacto solicitud : vendedor.getSolicitudesPendientes()) {
            // Lógica para aceptar o rechazar automáticamente las solicitudes según ciertas condiciones
        }
    }



}
