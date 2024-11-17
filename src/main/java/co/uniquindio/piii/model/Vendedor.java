package co.uniquindio.piii.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import co.uniquindio.piii.exceptions.ContactoYaExistenteException;
import co.uniquindio.piii.exceptions.LimiteContactosExcedidoException;

public class Vendedor implements Serializable {

    private String nombre;
    private String usuario;
    private String contrasena;
    private String email;
    private String direccion;
    private String id;
    private ArrayList<Contacto> solicitudesPendientes;
    private ArrayList<Producto> productos;
    private ArrayList<Publicacion> publicaciones;
    private ArrayList<Vendedor> contactos;
    private ArrayList<Producto> productosVendidos; // Nueva lista para productos vendidos

    public Vendedor() {
        productosVendidos = new ArrayList<>();
    }

    public Vendedor(String nombre, String usuario, String contrasena, String email, String direccion, String id) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.email = email;
        this.direccion = direccion;
        this.id = id;
        this.productos = new ArrayList<>();
        this.contactos = new ArrayList<>();
        this.publicaciones = new ArrayList<>();
        this.solicitudesPendientes = new ArrayList<>();
        this.productosVendidos = new ArrayList<>();
    }

    public ArrayList<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(ArrayList<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public ArrayList<Vendedor> getContactos() {
        return contactos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public void setContactos(ArrayList<Vendedor> contactos) {
        this.contactos = contactos;
    }

    public void publicarProducto(Producto producto) {
        productos.add(producto);
        producto.setEstadoProducto(EstadoProducto.PUBLICADO);
    }

    public void agregarContacto(Vendedor vendedor) {
        contactos.add(vendedor);
    }

    public void enviarMensaje(Vendedor destinatario, String mensaje) {
        // Implementación pendiente
    }

    public void dejarComentario(Vendedor vendedor, Comentario comentario) {
        // Implementación pendiente
    }

    public ArrayList<Producto> obtenerProductosRed(Producto productos) {
        return new ArrayList<>();
    }

    public ArrayList<Producto> buscarProductoPorFecha(LocalDate fecha) {
        return new ArrayList<>();
    }

    public ArrayList<Contacto> getSolicitudesPendientes() {
        return solicitudesPendientes;
    }

    @Override
    public String toString() {
        return "Vendedor [nombre=" + nombre + ", usuario=" + usuario + ", contrasena=" + contrasena + ", email=" + email
                + ", productos=" + productos + ", contactos=" + contactos + "]";
    }

    public void enviarSolicitudContacto(Vendedor destinatario) {
        Contacto solicitud = new Contacto(this.getNombre(), this.getUsuario(), this.getContrasena(), this.getEmail(),
                this.getDireccion(), this.getId(), LocalDate.now());
        destinatario.getSolicitudesPendientes().add(solicitud);
    }

    public void aceptarSolicitudContacto(Contacto solicitud)
            throws ContactoYaExistenteException, LimiteContactosExcedidoException {
        this.getContactos().add(solicitud); // Agregar este contacto a la lista de contactos
        this.getSolicitudesPendientes().remove(solicitud); // Eliminar la solicitud pendiente
        solicitud.aceptarContacto(); // Agregar el contacto de manera recíproca
    }

    public void rechazarSolicitudContacto(Contacto solicitud) {
        this.solicitudesPendientes.remove(solicitud);
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSolicitudesPendientes(ArrayList<Contacto> solicitudesPendientes) {
        this.solicitudesPendientes = solicitudesPendientes;
    }

    // Métodos nuevos para registrar y obtener productos vendidos
    public void registrarVenta(Producto producto) {
        this.productosVendidos.add(producto);
    }

    public ArrayList<Producto> getProductosVendidos() {
        return productosVendidos;
    }
}