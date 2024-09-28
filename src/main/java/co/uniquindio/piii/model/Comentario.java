package co.uniquindio.piii.model;

import java.time.LocalDate;

public class Comentario {

    private String autor;
    private String texto;
    private LocalDate fecha;

    


    public String getAutor() {
        return autor;
    }




    public void setAutor(String autor) {
        this.autor = autor;
    }




    public String getTexto() {
        return texto;
    }




    public void setTexto(String texto) {
        this.texto = texto;
    }




    public LocalDate getFecha() {
        return fecha;
    }




    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    


    public Comentario(String autor, String texto, LocalDate fecha) {
        this.autor = autor;
        this.texto = texto;
        this.fecha = fecha;
    }
    




    @Override
    public String toString() {
        return "Comentario [autor=" + autor + ", texto=" + texto + ", fecha=" + fecha + "]";
    }




    public void editarComentario(String nuevoTexto){

    }



}
