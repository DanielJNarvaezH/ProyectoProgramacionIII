package co.uniquindio.piii.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import co.uniquindio.piii.model.Producto;
import co.uniquindio.piii.model.Vendedor;
public class ProductoList {
    private List<Producto> productos = new ArrayList<>();

    public void add(Producto producto) {
        productos.add(producto);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void saveToXml(String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            XStream xstream = new XStream(new DomDriver());
            xstream.registerConverter(new ProductoConverter());
            xstream.alias("producto-list", ProductoList.class);
            xstream.alias("producto", Producto.class);
            xstream.toXML(this, fos);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo XML: " + e.getMessage());
        }
    }

    public static ProductoList loadFromXml(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName)) {
            XStream xstream = new XStream(new DomDriver());
            xstream.registerConverter(new ProductoConverter());
            xstream.alias("producto-list", ProductoList.class);
            xstream.alias("producto", Producto.class);
            return (ProductoList) xstream.fromXML(fis);
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo XML: " + e.getMessage());
            return null;
        }
    }
    //Casos de pruebas
    public static void main(String[] args) {
        // Crear algunos productos de ejemplo
        Producto producto1 = new Producto();
        producto1.setTitulo("Producto 1");
        producto1.setDescripcion("Descripción del producto 1");
        producto1.setFechaPublicacion(LocalDate.of(2023, 5, 1));
        producto1.setNumLikes(50);
        producto1.setVendedor(new Vendedor(null, null, null, null));

        Producto producto2 = new Producto();
        producto2.setTitulo("Producto 2");
        producto2.setDescripcion("Descripción del producto 2");
        producto2.setFechaPublicacion(LocalDate.of(2023, 6, 15));
        producto2.setNumLikes(30);
        producto2.setVendedor(new Vendedor("Vendedor 2", null, null, null));

        // Crear la lista de productos
        ProductoList productoList = new ProductoList();
        productoList.add(producto1);
        productoList.add(producto2);

        // Guardar la lista de productos en un archivo XML
        productoList.saveToXml("productos.xml");
        System.out.println("Productos guardados en productos.xml");

        // Cargar la lista de productos desde el archivo XML
        ProductoList loadedProductoList = ProductoList.loadFromXml("productos.xml");
        System.out.println("Productos cargados desde productos.xml:");
        for (Producto p : loadedProductoList.productos) {
            System.out.println("Título: " + p.getTitulo());
            System.out.println("Descripción: " + p.getDescripcion());
            System.out.println("Fecha de publicación: " + p.getFechaPublicacion());
            System.out.println("Número de likes: " + p.getNumLikes());
            System.out.println("Vendedor: " + p.getVendedor().getNombre());
            System.out.println();
        }
    }
}