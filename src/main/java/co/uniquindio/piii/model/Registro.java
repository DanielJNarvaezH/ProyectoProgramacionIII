package co.uniquindio.piii.model;

import java.io.*;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import co.uniquindio.piii.exceptions.EmailYaRegistradoException;

public class Registro implements Serializable {
    private static final ResourceBundle config = ResourceBundle.getBundle("archivosProperties.config");
    private static final String RUTA_REGISTRO_TXT = config.getString("rutaRegistrosTxt");
    
    public static boolean esUsuarioDuplicado(String nombreUsuario) {
        Set<String> usuarios = obtenerUsuariosYCorreos(1); // índice 1 para usuario
        return usuarios.contains(nombreUsuario);
    }

    public static boolean esCorreoDuplicado(String correo) {
        Set<String> correos = obtenerUsuariosYCorreos(2); // índice 2 para correo
        return correos.contains(correo);
    }
    
    
    public static void guardarDatosRegistro(String nombre, String nombreUsuario, String correo, String password, String direccion, String id) throws EmailYaRegistradoException {
        if (esCorreoDuplicado(correo)) {
            throw new EmailYaRegistradoException("El correo electrónico ya está registrado.");
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_REGISTRO_TXT, true))) {
            writer.write(nombre + "%%" + nombreUsuario + "%%" + correo + "%%" + password+ "%%" + direccion + "%%" + id);
            writer.newLine();
            System.out.println("Datos guardados.");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos de registro: " + e.getMessage());
        }
    }
    private static Set<String> obtenerUsuariosYCorreos(int indice) {
        Set<String> datos = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_REGISTRO_TXT))) {
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
    public static void listarRegistros() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_REGISTRO_TXT))) {
            String linea;
            int contador = 1;
            System.out.println("Registros actuales:");
            while ((linea = reader.readLine()) != null) {
                System.out.println(contador++ + ". " + linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de registros: " + e.getMessage());
        }
    }
    
    public static void eliminarRegistro(int indice) {
        File archivo = new File(RUTA_REGISTRO_TXT);
        File archivoTemporal = new File("registros_temp.txt");
        
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo));
             BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTemporal))) {
            
            String linea;
            int contador = 1;
            while ((linea = reader.readLine()) != null) {
                if (contador != indice) {
                    writer.write(linea);
                    writer.newLine();
                }
                contador++;
            }
        } catch (IOException e) {
            System.out.println("Error al eliminar el registro: " + e.getMessage());
        }
        
        if (archivo.delete()) {
            archivoTemporal.renameTo(archivo);
            System.out.println("Registro eliminado con éxito.");
        } else {
            System.out.println("Error al actualizar el archivo de registros.");
        }
    }
    
    
}
