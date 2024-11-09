package co.uniquindio.piii.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import co.uniquindio.piii.exceptions.ComentarioNoEncontradoException;

public class Publicacion implements Serializable {
    private String contenido; // Contenido o mensaje de la publicación
    private Producto producto; // Producto relacionado con la publicación
    private List<Comentario> comentarios; // Lista de comentarios
    private int likes; // Contador de likes

    // Constructor
    public Publicacion(String contenido, Producto producto) {
        this.contenido = contenido;
        this.producto = producto;
        this.comentarios = new ArrayList<>();
        this.likes = 0;
    }

    // Transacción para agregar un comentario a la publicación
    public void agregarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

    // Transacción para eliminar un comentario específico
    public void eliminarComentario(Comentario comentario) throws ComentarioNoEncontradoException {
        if (!comentarios.remove(comentario)) {
            throw new ComentarioNoEncontradoException("El comentario no se encuentra en la publicación.");
        }
    }

    // Transacción para incrementar los likes de la publicación
    public void incrementarLikes() {
        likes++;
    }

    // Transacción para decrementar los likes de la publicación (si es mayor que 0)
    public void decrementarLikes() {
        if (likes > 0) {
            likes--;
        }
    }

    // Transacción para mostrar todos los comentarios
    public List<Comentario> mostrarComentarios() {
        return new ArrayList<>(comentarios); // Devuelve una copia de la lista de comentarios
    }

    // Transacción para mostrar los detalles de la publicación
    public String mostrarDetalles() {
        StringBuilder detalles = new StringBuilder();
        detalles.append("Contenido: ").append(contenido).append("\n")
                .append("Likes: ").append(likes).append("\n")
                .append("Producto: ").append(producto.getTitulo()).append("\n")
                .append("Comentarios:\n");
        for (Comentario comentario : comentarios) {
            detalles.append("- ").append(comentario).append("\n");
        }
        return detalles.toString();
    }

    // Getters y Setters
    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getLikes() {
        return likes;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    @Override
    public String toString() {
        return "Publicacion{" +
                "contenido='" + contenido + '\'' +
                ", producto=" + producto +
                ", likes=" + likes +
                ", comentarios=" + comentarios +
                '}';
    }
}