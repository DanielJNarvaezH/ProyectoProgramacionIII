package co.uniquindio.piii.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.uniquindio.piii.exceptions.ContactoNoEncontradoException;
import co.uniquindio.piii.exceptions.ContactoYaExistenteException;
import co.uniquindio.piii.exceptions.FormatoInvalidoException;
import co.uniquindio.piii.exceptions.LimiteContactosExcedidoException;
import co.uniquindio.piii.exceptions.ProductoNoEncontradoException;
import co.uniquindio.piii.exceptions.ProductoSinNombreException;
import co.uniquindio.piii.exceptions.ProductoYaExistenteException;  
import co.uniquindio.piii.model.*;
import co.uniquindio.piii.utilities.EjemploLog;

public class Muro implements Serializable{

    private ArrayList<Publicacion> publicaciones;
    private ArrayList<Producto> productos;
    private Vendedor vendedor;
    // Lista de contactos asociados al muro (vendedor)
    private List<Vendedor> contactos;
    private List<Contacto> solicitudesPendientes;


    // Método para obtener la lista de contactos
    public List<Vendedor> getContactos() {
        return contactos;
    }
    
    public Muro(Vendedor vendedor) {
        productos = new ArrayList<>();
        this.vendedor = vendedor;
        this.contactos = new ArrayList<>();
        this.solicitudesPendientes = new ArrayList<>();
    }

    public List<Contacto> getSolicitudesPendientes() {
        return solicitudesPendientes;
    }

    public void agregarSolicitudPendiente(Contacto contacto) {
        solicitudesPendientes.add(contacto);
    }
    

    @Override
    public String toString() {
        return "Muro [productos=" + productos + "]";
    }


    public ArrayList<Producto> getProductos() {
        return productos;
    }
    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public Vendedor getVendedor(){
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor){
        this.vendedor = vendedor;
    }

    public ArrayList<Producto> obtenerProductosOrdenadosPorFecha(){
        return new ArrayList<>();
    }

    public void agregarContacto(Contacto contacto) throws ContactoYaExistenteException, LimiteContactosExcedidoException {
    if (contactos.size() >= 9) {
        throw new LimiteContactosExcedidoException("No se puede agregar más contactos. Límite alcanzado.");
    }
    if (contactos.contains(contacto)) {
        throw new ContactoYaExistenteException("El contacto ya existe en el muro.");
    }
    contactos.add(contacto);
}

    public void removerContacto(Contacto contacto) throws ContactoNoEncontradoException {
        if (!contactos.contains(contacto)) {
            throw new ContactoNoEncontradoException("El contacto no se encuentra en el muro.");
        }
        contactos.remove(contacto);
    }

    public void aceptarSolicitud(Contacto contacto) throws ContactoNoEncontradoException, ContactoYaExistenteException, LimiteContactosExcedidoException {
        // Comprobamos si el contacto está en la lista de solicitudes pendientes
        if (!solicitudesPendientes.contains(contacto)) {
            throw new ContactoNoEncontradoException("El contacto no está en la lista de solicitudes pendientes del muro.");
        }
    
        // Delegar la aceptación de solicitud al método de la clase Contacto
        contacto.aceptarContacto(); // Aceptar contacto con el vendedor actual
    
        // Eliminar la solicitud de la lista de solicitudes pendientes
        solicitudesPendientes.remove(contacto);
    
        // Aquí se pueden incluir acciones adicionales si son necesarias
        EjemploLog.logInfo("Solicitud de contacto aceptada en el muro para el contacto: " + contacto.getNombre());
    }

    public void solicitarContacto(Contacto contacto) throws ContactoYaExistenteException, LimiteContactosExcedidoException {
        if (contactos.size() >= 9) {
            throw new LimiteContactosExcedidoException("Límite de contactos alcanzado. No se puede enviar solicitud.");
        }
        if (contactos.contains(contacto)) {
            throw new ContactoYaExistenteException("Ya tienes este contacto en tu muro.");
        }
        solicitudesPendientes.add(contacto); // Asume que solicitudesPendientes es una lista de solicitudes.
    }
    public void agregarProducto(Producto producto) throws ProductoYaExistenteException, ProductoSinNombreException {
        if (producto.getTitulo() == null || producto.getTitulo().isEmpty()) {
            throw new ProductoSinNombreException("El producto debe tener un nombre.");
        }
        if (productos.contains(producto)) {
            throw new ProductoYaExistenteException("El producto ya está registrado en el muro.");
        }
        productos.add(producto);
    }

    public void removerProducto(Producto producto) throws ProductoNoEncontradoException {
        if (!productos.contains(producto)) {
            throw new ProductoNoEncontradoException("El producto no se encuentra en el muro.");
        }
        productos.remove(producto);
    }

    public void agregarComentario(Publicacion publicacion, Comentario comentario) throws ProductoNoEncontradoException {
        if (!publicaciones.contains(publicacion)) {
            throw new ProductoNoEncontradoException("La publicación no se encuentra en el muro.");
        }
        publicacion.agregarComentario(comentario);
    }

    public void darLike(Publicacion publicacion) throws ProductoNoEncontradoException {
        if (!publicaciones.contains(publicacion)) {
            throw new ProductoNoEncontradoException("La publicación no se encuentra en el muro.");
        }
        publicacion.incrementarLikes();
    }

    public Producto buscarProductoPorNombre(String nombreProducto) throws ProductoNoEncontradoException {
        for (Producto producto : productos) {
            if (producto.getTitulo().equalsIgnoreCase(nombreProducto)) {
                return producto;
            }
        }
        throw new ProductoNoEncontradoException("Producto con el nombre especificado no fue encontrado.");
    }

}