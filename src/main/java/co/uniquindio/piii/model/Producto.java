package co.uniquindio.piii.model;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("producto")
public class Producto implements Serializable {

    private String titulo;
    private String descripcion;
    private LocalDate fechaPublicacion;
    private EstadoProducto estadoProducto;
    private CategoriaProducto categoria;
    private Vendedor vendedor;
    private File imagen;

    // Constructor vacío para la serialización
    public Producto() {
    }

    // Constructor principal
    public Producto(String titulo, String descripcion, LocalDate fechaPublicacion, EstadoProducto estadoProducto, 
                    CategoriaProducto categoria, Vendedor vendedor, File imagen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaPublicacion = fechaPublicacion;
        this.estadoProducto = estadoProducto;
        this.categoria = categoria;
        this.vendedor = vendedor;
        this.imagen = imagen;
    }

    // Métodos getter y setter
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

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public File getImagen() {
        return imagen;
    }

    public void setImagen(File imagen) {
        this.imagen = imagen;
    }

    // Método para obtener una descripción breve del producto
    public String obtenerDescripcionBreve() {
        return titulo + " - " + descripcion;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaPublicacion=" + fechaPublicacion +
                ", estadoProducto=" + estadoProducto +
                ", categoria=" + categoria +
                ", vendedor=" + vendedor +
                ", imagen=" + imagen +
                '}';
    }
}