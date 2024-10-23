package co.uniquindio.piii.model;

import java.util.ArrayList;
import java.io.Serializable;
import java.time.LocalDate;

public class Vendedor implements Serializable {

    private String nombre;
    private String usuario;
    private String contrasena;
    private String email;
    
    
    private ArrayList<Producto> productos;
    private ArrayList<Vendedor> contactos;

    

    public Vendedor(String nombre, String usuario, String contrasena, String email) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.email = email;
        this.productos = new ArrayList<>();
        this.contactos = new ArrayList<>();

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

    public void publicarProducto(Producto producto){
        productos.add(producto);
    }
    public void agregarContacto(Vendedor vendedor){

    }
    public void enviarMensaje(Vendedor destinatario, String mensaje){

    }
    public void dejarComentario(Vendedor vendedor, Comentario comentario ){

    }
    public ArrayList<Producto> obtenerProductosRed(Producto productos){
        return new ArrayList<>();
    }
    public ArrayList<Producto> buscarProductoPorFecha(LocalDate fecha){
        return new ArrayList<>();
    }

    public void recibirLike(Producto producto){

    }



}
