package co.uniquindio.piii.model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Estadistica implements Serializable {

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

    // Obtener el total de productos publicados en una fecha específica
    public int obtenerTotalProductosPorFecha(List<Producto> productos, LocalDateTime fecha) {
        return (int) productos.stream()
                .filter(producto -> producto.getFechaPublicacion().equals(fecha))
                .count();
    }

   // Obtener las 10 publicaciones con más likes
    public List<Publicacion> obtenerTop10PublicacionesConMasLikes(List<Publicacion> publicaciones) {
        return publicaciones.stream()
                .sorted(Comparator.comparingInt(Publicacion::getLikes).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    // Obtener el total de contactos
    public int obtenerTotalContactos(List<Vendedor> vendedores) {
        return vendedores.stream()
                .mapToInt(vendedor -> vendedor.getContactos().size())
                .sum();
    }

    // Obtener el total de publicaciones por un vendedor específico
    public int obtenerTotalPublicacionesPorVendedor(Vendedor vendedor) {
        return vendedor.getPublicaciones().size();
    }

    // Obtener el total de solicitudes pendientes para un vendedor específico
    public int obtenerTotalSolicitudesPendientes(Vendedor vendedor) {
        return vendedor.getSolicitudesPendientes().size();
    }

    // Obtener el total de comentarios en todas las publicaciones de la tienda
    public int obtenerTotalComentarios(List<Publicacion> publicaciones) {
        return publicaciones.stream()
                .mapToInt(publicacion -> publicacion.getComentarios().size())
                .sum();
    }

    // Obtener el total de likes en todas las publicaciones de la tienda
    public int obtenerTotalLikes(List<Publicacion> publicaciones) {
        return publicaciones.stream()
                .mapToInt(Publicacion::getLikes)
                .sum();
    }

    // Obtener el promedio de productos publicados por vendedor
    public double obtenerPromedioProductosPorVendedor(List<Vendedor> vendedores) {
        return vendedores.isEmpty() ? 0 : (double) totalProductoPublicados / vendedores.size();
    }

    // Actualizar estadísticas generales de la tienda
    public void actualizarEstadisticasGenerales(List<Producto> productos, List<Publicacion> publicaciones, List<Vendedor> vendedores) {
        this.totalProductoPublicados = productos.size();
        this.totalLikes = obtenerTotalLikes(publicaciones);
        this.totalComentarios = obtenerTotalComentarios(publicaciones);
        this.totalContactos = obtenerTotalContactos(vendedores);
    }

        /**
     * Calcula las ventas totales de los vendedores para un mes específico.
     */
    public static String calcularVentasPorMes(ArrayList<Vendedor> vendedores, Month mesSeleccionado) {
        StringBuilder resultado = new StringBuilder("----- Ventas totales para " + mesSeleccionado + " -----\n");
        int sumaTotal = 0;

        for (Vendedor vendedor : vendedores) {
            resultado.append("Vendedor: ").append(vendedor.getNombre()).append("\n");
            ArrayList<Producto> ventasMes = vendedor.getProductosVendidosPorMes(mesSeleccionado);

            if (ventasMes.isEmpty()) {
                resultado.append("- Sin ventas en este mes.\n");
            } else {
                for (Producto producto : ventasMes) {
                    resultado.append("  Producto vendido: ").append(producto.getTitulo())
                            .append(", Precio: $").append(producto.getPrecio()).append("\n");
                    sumaTotal += producto.getPrecio();
                }
            }
        }

        resultado.append("Total vendido en ").append(mesSeleccionado).append(": $").append(sumaTotal).append("\n");
        return resultado.toString();
    }
}