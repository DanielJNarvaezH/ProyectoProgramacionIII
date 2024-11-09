package co.uniquindio.piii.model;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import co.uniquindio.piii.exceptions.EmailYaRegistradoException;

public class Registro implements Serializable {
    private static final String RUTA_ARCHIVO = "registros.txt";
    
    public static boolean esUsuarioDuplicado(String nombreUsuario) {
        Set<String> usuarios = obtenerUsuariosYCorreos(1); // índice 1 para usuario
        return usuarios.contains(nombreUsuario);
    }

    public static boolean esCorreoDuplicado(String correo) {
        Set<String> correos = obtenerUsuariosYCorreos(2); // índice 2 para correo
        return correos.contains(correo);
    }
    
    
    public static void guardarDatosRegistro(String nombre, String nombreUsuario, String correo, String password) throws EmailYaRegistradoException {
        if (esCorreoDuplicado(correo)) {
            throw new EmailYaRegistradoException("El correo electrónico ya está registrado.");
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, true))) {
            writer.write(nombre + "%%" + nombreUsuario + "%%" + correo + "%%" + password);
            writer.newLine();
            System.out.println("Datos guardados.");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos de registro: " + e.getMessage());
        }
    }
    private static Set<String> obtenerUsuariosYCorreos(int indice) {
        Set<String> datos = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("%%");
                // Asegúrate de que la línea tiene la cantidad esperada de datos
                if (partes.length == 4) { 
                    datos.add(partes[indice]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer los datos de registro: " + e.getMessage());
        }
        return datos;
    }
    
}
