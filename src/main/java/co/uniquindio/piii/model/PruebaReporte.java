package co.uniquindio.piii.model;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import co.uniquindio.piii.utilities.*;

public class PruebaReporte {

    public static void main(String[] args) {
        
        EjemploLog.logInfo("Primer prueba de creación reporte");

          // Crear vendedores
          Vendedor pedro = new Vendedor("Pedro", "123", "password", "pedro@gmail.com", "Calle 1", "001");
          Vendedor juan = new Vendedor("Juan", "345", "password", "juan@gmail.com", "Calle 2", "002");
  
          // Crear productos
        Producto iphone = new Producto("Iphone 11", "Smartphone de Apple", "001",
                LocalDateTime.now(), EstadoProducto.NUEVO, CategoriaProducto.TECNOLOGIA, 1000000, pedro, null);
        Producto guitarra = new Producto("Guitarra", "Instrumento musical acústico", "002",
                LocalDateTime.now(), EstadoProducto.USADO, CategoriaProducto.MUSICA, 200000, pedro, null);
        Producto portatil = new Producto("Portatil Dell", "Laptop Dell con Core i7", "003",
                LocalDateTime.now(), EstadoProducto.NUEVO, CategoriaProducto.TECNOLOGIA, 2000000, pedro, null);


        // Publicar productos
        pedro.publicarProducto(iphone);
        pedro.publicarProducto(guitarra);

        // Registrar venta
        pedro.registrarVenta(portatil);

        // Crear lista de vendedores
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        vendedores.add(pedro);
        vendedores.add(juan);

        // Generar reporte
        Persistencia.generarReporte(vendedores);
        // Mostrar ventas del mes seleccionado
        Month mesSeleccionado = Month.NOVEMBER; // Por ejemplo, noviembre
        Estadistica.calcularVentasPorMes(vendedores, mesSeleccionado);


    }

}
