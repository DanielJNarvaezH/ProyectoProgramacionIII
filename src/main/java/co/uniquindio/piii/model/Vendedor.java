package co.uniquindio.piii.model;

import java.util.ArrayList;
import java.time.LocalDate;

public class Vendedor {

    private String nombre;
    private String usuario;
    private String contrasena;
    private String email;
    
    
    private ArrayList<Producto> productos;
    private ArrayList<Vendedor> contactos;

    public void publicarProducto(Producto producto){

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
