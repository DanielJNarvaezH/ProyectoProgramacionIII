package co.uniquindio.piii.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import javafx.scene.image.Image;

@XStreamAlias("producto")
public class Producto implements Serializable {

    private String titulo;
    private String descripcion;
    private String codigo;
    private LocalDateTime fechaPublicacion;
    private EstadoProducto estadoProducto;
    private CategoriaProducto categoria;
    private int precio;
    private Vendedor vendedor;
    private Image imagen;

    // Constructor vacío para la serialización
    public Producto() {
    }

    // Constructor principal
    public Producto(String titulo, String descripcion,String codigo, LocalDateTime fechaPublicacion, EstadoProducto estadoProducto, 
                    CategoriaProducto categoria, int precio, Vendedor vendedor, Image imagen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.fechaPublicacion = fechaPublicacion;
        this.estadoProducto = estadoProducto;
        this.categoria = categoria;
        this.precio = precio;
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

    public String getCodigo() {
        return titulo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
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

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
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
                ", precio=" + codigo +
                ", fechaPublicacion=" + fechaPublicacion +
                ", estadoProducto=" + estadoProducto +
                ", categoria=" + categoria +
                ", vendedor=" + vendedor +
                ", imagen=" + imagen +
                '}';
    }
}