package co.uniquindio.piii.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import co.uniquindio.piii.model.Producto;
import co.uniquindio.piii.model.Vendedor;

public class VendedorPlantilla {
    private List<Producto> productos = new ArrayList<Producto>();
    private List<Vendedor> contactos = new ArrayList<Vendedor>();

    public void add(Producto producto) {
        productos.add(producto);
    }

    public void addContact(Vendedor vendedor){
        contactos.add(vendedor);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public List<Vendedor> getContactos() {
        return contactos;
    }

    public void saveToXml(String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            XStream xstream = new XStream(new DomDriver());
            xstream.registerConverter(new ProductoConverter());
            xstream.alias("vendedor-plantilla", VendedorPlantilla.class);
            xstream.alias("vendedor", Vendedor.class);
            xstream.toXML(this, fos);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo XML: " + e.getMessage());
        }
    }

    public static VendedorPlantilla loadFromXml(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName)) {
            XStream xstream = new XStream(new DomDriver());
            xstream.registerConverter(new ProductoConverter());
            xstream.alias("vendedor-plantilla", VendedorPlantilla.class);
            xstream.alias("vendedor", Vendedor.class);
            return (VendedorPlantilla) xstream.fromXML(fis);
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo XML: " + e.getMessage());
            return null;
        }
    }

    //Agregar casos de pruebas
}
