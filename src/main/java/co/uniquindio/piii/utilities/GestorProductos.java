package co.uniquindio.piii.utilities;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import co.uniquindio.piii.model.CategoriaProducto;
import co.uniquindio.piii.model.EstadoProducto;
import co.uniquindio.piii.model.Producto;
import co.uniquindio.piii.model.Vendedor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class GestorProductos {
    private List<Producto> productosDisponibles = new ArrayList<>();
    private List<Producto> productosPublicados = new ArrayList<>();
    private List<Producto> productosVendidos = new ArrayList<>();
    private static GestorProductos instance;

    public static GestorProductos getInstance() {  
        if (instance == null) {  
            instance = new GestorProductos();  
        }  
        return instance;  
    }

    private XStream xstream = new XStream(new DomDriver());

    public GestorProductos() {
        xstream.processAnnotations(Producto.class); // Activa las anotaciones de XStream
    }

    public synchronized void agregarProductoDisponible(Producto producto) {
        productosDisponibles.add(producto);
        actualizarArchivoXML("Productos_disponibles.xml", productosDisponibles);
        actualizarArchivoBinario("Productos_disponibles.dat", productosDisponibles);
    }

    public synchronized void publicarProducto(Producto producto) {
        productosDisponibles.remove(producto);
        productosPublicados.add(producto);
        actualizarArchivoXML("Productos_disponibles.xml", productosDisponibles);
        actualizarArchivoXML("Productos_publicados.xml", productosPublicados);
        actualizarArchivoBinario("Productos_disponibles.dat", productosDisponibles);
        actualizarArchivoBinario("Productos_publicados.dat", productosPublicados);
    }

    public synchronized void venderProducto(Producto producto) {
        producto.setEstadoProducto(EstadoProducto.VENDIDO);
        productosPublicados.remove(producto);
        productosVendidos.add(producto);
        actualizarArchivoXML("Productos_publicados.xml", productosPublicados);
        actualizarArchivoXML("Productos_vendidos.xml", productosVendidos);
        actualizarArchivoBinario("Productos_publicados.dat", productosPublicados);
        actualizarArchivoBinario("Productos_vendidos.dat", productosVendidos);
    }

    // Serialización en XML
    private void actualizarArchivoXML(String nombreArchivo, List<Producto> listaProductos) {
        new Thread(() -> {
            try (FileWriter writer = new FileWriter(nombreArchivo)) {
                xstream.toXML(listaProductos, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Serialización en Binario
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
;
    public static void main(String[] args) {
        GestorProductos gestor = new GestorProductos();

        Producto producto1 = new Producto();
        producto1.setTitulo("Producto de Prueba");
        producto1.setDescripcion("Descripción del producto de prueba");
        producto1.setCategoria(CategoriaProducto.ROPA);
        producto1.setEstadoProducto(EstadoProducto.PUBLICADO);
        producto1.setFechaPublicacion(LocalDate.now());
        producto1.setVendedor(new Vendedor("Mario", null, null, null));
        // Configura otros atributos de producto1 según sea necesario

        Producto producto2 = new Producto();
        producto2.setTitulo("Muebles");
        producto2.setDescripcion("Hechos en madera");
        producto2.setCategoria(CategoriaProducto.HOGAR);
        producto2.setEstadoProducto(EstadoProducto.PUBLICADO);
        producto2.setFechaPublicacion(LocalDate.now());

        gestor.agregarProductoDisponible(producto1);
        gestor.agregarProductoDisponible(producto2);  // Agrega producto a la lista de disponibles
        gestor.publicarProducto(producto1);           // Mueve el producto a la lista de publicados
        gestor.venderProducto(producto1);
        gestor.publicarProducto(producto2);              
    }
}



