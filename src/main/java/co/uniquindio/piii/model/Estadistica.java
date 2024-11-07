package co.uniquindio.piii.model;
import java.io.Serializable;
import java.time.LocalDate;

import java.util.ArrayList;

public class Estadistica implements Serializable{

    private int totalProductoPublicados;
    private int totalLikes;
    private int totalComentarios;
    private int totalContactos;

    public Estadistica(int totalProductoPublicados, int totalLikes, int totalComentarios, int totalContactos) {
        this.totalProductoPublicados = totalProductoPublicados;
        this.totalLikes = totalLikes;
        this.totalComentarios = totalComentarios;
        this.totalContactos = totalContactos;
    }
    public int getTotalProductoPublicados() {
        return totalProductoPublicados;
    }
    public void setTotalProductoPublicados(int totalProductoPublicados) {
        this.totalProductoPublicados = totalProductoPublicados;
    }
    public int getTotalLikes() {
        return totalLikes;
    }
    public void setTotalLikes(int totalLikes) {
        this.totalLikes = totalLikes;
    }
    public int getTotalComentarios() {
        return totalComentarios;
    }
    public void setTotalComentarios(int totalComentarios) {
        this.totalComentarios = totalComentarios;
    }
    public int getTotalContactos() {
        return totalContactos;
    }
    public void setTotalContactos(int totalContactos) {
        this.totalContactos = totalContactos;
    }

    
    @Override
    public String toString() {
        return "Estadistica [totalProductoPublicados=" + totalProductoPublicados + ", totalLikes=" + totalLikes
                + ", totalComentarios=" + totalComentarios + ", totalContactos=" + totalContactos + "]";
    }
    public int obtenerTotalProductosPorFecha(LocalDate fecha){
        return 0;
    }
    public ArrayList<Producto> obtenerTop10ProductosConMasLikes(){
        return new ArrayList<>();
    }

    public int obtenerTotalContactos(){
        return 0;

    }
    public int obtenerTotalProductoPorVendedor(){
        return 0;
    }

    public int obtenerTotalSolicitudesPendientes(Vendedor vendedor) {
        return vendedor.getSolicitudesPendientes().size();
    }
}
