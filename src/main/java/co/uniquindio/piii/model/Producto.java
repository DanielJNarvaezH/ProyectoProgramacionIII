package co.uniquindio.piii.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.io.File;

public class Producto {

    private String titulo;
    private String descripcion;
    private LocalDate fechaPublicacion;
    private int numLikes;
    private Vendedor vendedor;
    private ArrayList<Comentario> comentarios;
    private File imagen;

    
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }
    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    public int getNumLikes() {
        return numLikes;
    }
    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }
    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }
    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    
    }
    public Producto(String titulo, String descripcion, LocalDate fechaPublicacion, int numLikes, Vendedor vendedor, File imagen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaPublicacion = fechaPublicacion;
        this.numLikes = numLikes;
        this.vendedor = vendedor;
        comentarios = new ArrayList<>();
        this.imagen = imagen;
    }

    public void agregarComentario(Comentario comentario){
        comentarios.add(comentario);

    }
    @Override
    public String toString() {
        return "Producto [titulo=" + titulo + ", descripcion=" + descripcion + ", fechaPublicacion=" + fechaPublicacion
                + ", numLikes=" + numLikes + ", vendedor=" + vendedor + ", comentarios=" + comentarios + "]";
    }
    
    public void recibirLike(){

    }
    public ArrayList<Producto> obtenerComentarios(){
        return new ArrayList<>();
    }
    public Vendedor getVendedor() {
        return vendedor;
    }
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    public File getImagen() { 
        return imagen; 
    }
    public void setImagen(File imagen){
        this.imagen = imagen;
    }



}
