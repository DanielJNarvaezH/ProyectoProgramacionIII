package co.uniquindio.piii.utilities;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import co.uniquindio.piii.model.CategoriaProducto;
import co.uniquindio.piii.model.EstadoProducto;
import co.uniquindio.piii.model.Producto;
import co.uniquindio.piii.model.Vendedor;
import co.uniquindio.piii.model.Tienda;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

//Clase para manejar la serialización de los productos y actualizar los archivos segun su estado 

public class GestorProductos {
    Tienda tienda = Tienda.getInstance(null);
    private List<Producto> productosCancelados = tienda.obtenerProductosPorEstado(EstadoProducto.CANCELADO);
    private List<Producto> productosPublicados = tienda.obtenerProductosPorEstado(EstadoProducto.PUBLICADO);
    private List<Producto> productosVendidos = tienda.obtenerProductosPorEstado(EstadoProducto.VENDIDO);
    private static GestorProductos instance;
    
    //Singleton
    public static GestorProductos getInstance() {  
        if (instance == null) {  
            instance = new GestorProductos();  
        }  
        return instance;  
    }

    private XStream xstream = new XStream(new DomDriver());

    public GestorProductos() {
        xstream.processAnnotations(Producto.class); // Activa las anotaciones de XStream de la clase a serializar
    }
    //Método sincrinizado para el uso de las listas e hilos de serialización
    public synchronized void agregarProductoDisponible(Producto producto) {
        productosPublicados.add(producto);
        actualizarArchivoXML("Productos_publicados.xml", productosPublicados);
        actualizarArchivoBinario("Productos_publicados.dat", productosPublicados);
    }
    //Método sincrinizado para el uso de las listas e hilos de serialización
    public synchronized void cancelarProducto(Producto producto) {
        productosPublicados.remove(producto);
        productosCancelados.add(producto);
        actualizarArchivoXML("Productos_publicados.xml", productosPublicados);
        actualizarArchivoXML("Productos_cancelados.xml", productosCancelados);
        actualizarArchivoBinario("Productos_publicados.dat", productosPublicados);
        actualizarArchivoBinario("Productos_cancelados.dat", productosCancelados);
    }
    //Método sincrinizado para el uso de las listas e hilos de serialización
    public synchronized void venderProducto(Producto producto) {
        producto.setEstadoProducto(EstadoProducto.VENDIDO);
        productosPublicados.remove(producto);
        productosVendidos.add(producto);
        actualizarArchivoXML("Productos_publicados.xml", productosPublicados);
        actualizarArchivoXML("Productos_vendidos.xml", productosVendidos);
        actualizarArchivoBinario("Productos_publicados.dat", productosPublicados);
        actualizarArchivoBinario("Productos_vendidos.dat", productosVendidos);
    }

    // Serialización de los productos en XML usando hilos
    private void actualizarArchivoXML(String nombreArchivo, List<Producto> listaProductos) {
        new Thread(() -> {
            try (FileWriter writer = new FileWriter(nombreArchivo)) {
                xstream.toXML(listaProductos, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Serialización en Binario de los productos usando hilos
    private void actualizarArchivoBinario(String nombreArchivo, List<Producto> listaProductos) {
        new Thread(() -> {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
                oos.writeObject(listaProductos);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Método opcional para cargar listas de archivos binarios al iniciar el programa
    @SuppressWarnings("unchecked")
    public List<Producto> cargarProductosDesdeBinario(String nombreArchivo) {
        List<Producto> productos = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            productos = (List<Producto>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + nombreArchivo);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return productos;
    }

    // Método opcional para cargar listas desde archivos XML al iniciar el programa
    @SuppressWarnings("unchecked")
    public List<Producto> cargarProductosDesdeXML(String nombreArchivo) {
        List<Producto> productos = new ArrayList<>();
        try (FileReader reader = new FileReader(nombreArchivo)) {
            productos = (List<Producto>) xstream.fromXML(reader);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productos;
    }
    //Casos de prueba para la serialización de los productos
    public static void main(String[] args) {
        GestorProductos gestor = new GestorProductos();
        Tienda tienda = Tienda.getInstance(null);
        EjemploLog.logInfo("Iniciando prueba de serialización en XML y binario mediante hilos con datos quemados");
        Producto producto1 = new Producto();
        producto1.setTitulo("Producto de Prueba");
        producto1.setDescripcion("Descripción del producto de prueba");
        producto1.setCategoria(CategoriaProducto.ROPA);
        producto1.setEstadoProducto(EstadoProducto.PUBLICADO);
        producto1.setFechaPublicacion(LocalDate.now());
        producto1.setVendedor(new Vendedor("Mario", null, null, null, null, null));

        tienda.agregarProducto(producto1);

        Producto producto2 = new Producto();
        producto2.setTitulo("Muebles");
        producto2.setDescripcion("Hechos en madera");
        producto2.setCategoria(CategoriaProducto.HOGAR);
        producto2.setEstadoProducto(EstadoProducto.PUBLICADO);
        producto2.setFechaPublicacion(LocalDate.now());

        tienda.agregarProducto(producto2);

        gestor.agregarProductoDisponible(producto1);
        gestor.agregarProductoDisponible(producto2);  // Agrega producto a la lista de disponibles
        gestor.cancelarProducto(producto1);           // Mueve el producto a la lista de publicados
        gestor.venderProducto(producto1);
        EjemploLog.logInfo("Finalizando prueba de serialización en XML y binario mediante hilos con datos quemados");              
    }
}



