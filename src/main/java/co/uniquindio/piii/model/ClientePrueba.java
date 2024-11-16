package co.uniquindio.piii.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import co.uniquindio.piii.model.Producto;
import co.uniquindio.piii.model.EstadoProducto;
import co.uniquindio.piii.model.CategoriaProducto;

public class ClientePrueba {
    public static void main(String[] args) {
        Producto producto1 = new Producto("Laptop", "Laptop de alto rendimiento", null, LocalDateTime.now(), EstadoProducto.PUBLICADO, CategoriaProducto.TECNOLOGIA,0, null, null);
        Producto producto2 = new Producto("Silla ergonómica", "Silla ideal para oficina", null,  LocalDateTime.now(), EstadoProducto.PUBLICADO, CategoriaProducto.HOGAR, 0, null, null);

        System.out.println("Productos disponibles:");
        System.out.println("1. " + producto1);
        System.out.println("2. " + producto2);

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nSeleccione un producto (1 o 2): ");
        int opcionProducto = scanner.nextInt();

        Producto productoSeleccionado = (opcionProducto == 1) ? producto1 : producto2;

        System.out.println("\n¿Desea comprar o cancelar el producto?");
        System.out.println("1. Comprar");
        System.out.println("2. Cancelar");
        System.out.print("Seleccione una opción: ");
        int opcionAccion = scanner.nextInt();

        if (opcionAccion == 1) {
            productoSeleccionado.setEstadoProducto(EstadoProducto.VENDIDO);
            System.out.println("Producto comprado exitosamente.");
        } else if (opcionAccion == 2) {
            productoSeleccionado.setEstadoProducto(EstadoProducto.CANCELADO);
            System.out.println("Compra cancelada.");
        } else {
            System.out.println("Opción no válida.");
        }

        System.out.println("\nEstado actual del producto seleccionado:");
        System.out.println(productoSeleccionado);

        scanner.close();
    }
}
