package co.uniquindio.piii.model;

import java.time.LocalDate;
import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.File;
import java.io.Serializable;


@XStreamAlias("producto")
public class Producto implements Serializable {

    private String titulo;
    private String descripcion;
    private LocalDate fechaPublicacion;
    private EstadoProducto estadoProducto;
    private CategoriaProducto categoria;
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

    public Producto(){
        //Constructor vacio para la serialización
    }



    public Producto(String titulo, String descripcion, LocalDate fechaPublicacion, EstadoProducto estadoProducto, CategoriaProducto categoria, int numLikes, Vendedor vendedor, File imagen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaPublicacion = fechaPublicacion;
        this.estadoProducto = estadoProducto;
        this.categoria = categoria;
        this.numLikes = numLikes;
        this.vendedor = vendedor;
        comentarios = new ArrayList<>();
        this.imagen = imagen;
    }

    public void agregarComentario(Comentario comentario){
        comentarios.add(comentario);

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
    public EstadoProducto getEstadoProducto() {
        return estadoProducto;
    }
    public void setEstadoProducto(EstadoProducto estadoProducto) {
        this.estadoProducto = estadoProducto;
    }
    public CategoriaProducto getCategoria() {
        return categoria;
    }
    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
    }
    @Override
    public String toString() {
        return "Producto [titulo=" + titulo + ", descripcion=" + descripcion + ", fechaPublicacion=" + fechaPublicacion
                + ", estadoProducto=" + estadoProducto + ", categoria=" + categoria + ", numLikes=" + numLikes
                + ", vendedor=" + vendedor + ", comentarios=" + comentarios + "]";
    }



}
