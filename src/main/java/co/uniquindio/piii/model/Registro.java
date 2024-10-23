package co.uniquindio.piii.model;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Registro implements Serializable {

    // Método para guardar datos de registro sin email
    public static void guardarDatosRegistro(String nombreUsuario, String contrasena) {
        String rutaArchivo = "registros.txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            // Formato: nombreUsuario%%contrasena
            writer.write(nombreUsuario + "%%" + contrasena);
            writer.newLine();  // Agrega un salto de línea al final
            // Mensaje de confirmación en consola
            System.out.println("Datos guardados, para comprobar que el programa está funcionando correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos de registro: " + e.getMessage());
        }
    }
}
