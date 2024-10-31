package co.uniquindio.piii.model;

import java.io.Serializable;
import java.util.ArrayList; 

public class Tienda implements Serializable {

    private static Tienda instance;

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
    
    public ArrayList<Producto> obtenerProductosPorEstado(EstadoProducto estado) {
        ArrayList<Producto> productosFiltrados = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getEstadoProducto() == estado) {
                productosFiltrados.add(producto);
            }
        }
        return productosFiltrados;
    }
    



}
