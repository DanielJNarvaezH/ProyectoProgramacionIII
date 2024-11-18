package co.uniquindio.piii.utilities;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Calendar;
import co.uniquindio.piii.model.Estadistica;
import co.uniquindio.piii.model.Producto;
import co.uniquindio.piii.model.Vendedor;




public class Persistencia {

    private static ResourceBundle config = ResourceBundle.getBundle("archivosProperties/config");
    private static Persistencia instance;
    public static Persistencia getInstance() {
        if (instance == null) {
            instance = new Persistencia();
        }
        return instance;
    }

    public static void serializarObjetoBinario(String rutaArchivo, Object objeto) throws IOException{
        ObjectOutputStream salida;

        salida = new  ObjectOutputStream(new FileOutputStream(rutaArchivo));
        salida.writeObject(objeto);
        salida.close();
    }

    public static Object deserializarObjetoBinario( String nombre) throws Exception{
        Object objeto;
        ObjectInputStream entrada;

        entrada = new ObjectInputStream( new FileInputStream(nombre));
        objeto = entrada.readObject();
        entrada.close();

        return objeto;
    }

    public static void serializarObjetoXML(String rutaArchivo, Object objeto) throws IOException {
        try (XMLEncoder codificador = new XMLEncoder(new FileOutputStream(rutaArchivo))) {
            codificador.writeObject(objeto);
            codificador.flush();
            System.out.println("Objeto serializado correctamente en XML: " + rutaArchivo);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al serializar el objeto en XML.");
        }
    }

    public static Object deserializarObjetoXML (String rutaArchivo) throws IOException{
        XMLDecoder decodificador;
        Object objeto;

        decodificador = new XMLDecoder( new FileInputStream(rutaArchivo));
        objeto = decodificador.readObject();
        decodificador.close();

        return objeto;
    }

    public static void crearRespaldoArchivoXML(String rutaArchivoOriginal) throws IOException {
        String rutaArchivoRespaldo = generarNombreArchivoRespaldo(rutaArchivoOriginal);
        File archivoOriginal = new File(rutaArchivoOriginal);
        File archivoRespaldo = new File(rutaArchivoRespaldo);

        if (!archivoRespaldo.getParentFile().exists()) {
            archivoRespaldo.getParentFile().mkdirs();
        }

        try (FileInputStream fis = new FileInputStream(archivoOriginal);
                FileOutputStream fos = new FileOutputStream(archivoRespaldo)) {
            byte[] buffer = new byte[1024];
            int longitud;

            while ((longitud = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, longitud);
            }
        }

        System.out.println("Copia de seguridad creada: " + rutaArchivoRespaldo);
    }

    public static String generarNombreArchivoRespaldo(String rutaArchivoOriginal) {
        Calendar cal = Calendar.getInstance();
        String dia = String.format("%02d", cal.get(Calendar.DATE));
        String mes = String.format("%02d", cal.get(Calendar.MONTH) + 1);
        String anio = String.valueOf(cal.get(Calendar.YEAR));
        String hora = String.format("%02d", cal.get(Calendar.HOUR_OF_DAY));
        String minuto = String.format("%02d", cal.get(Calendar.MINUTE));
        String segundo = String.format("%02d", cal.get(Calendar.SECOND));

        File archivoOriginal = new File(rutaArchivoOriginal);
        String nombreArchivo = archivoOriginal.getName().replaceFirst("[.][^.]+$", "");

        String nombreArchivoBackup = nombreArchivo + "_" + dia + mes + anio + "_" + hora + "_" + minuto + "_" + segundo;
        return archivoOriginal.getParent() + File.separator + "backup" +  File.separator + nombreArchivoBackup;
    }



        public static void guardarEstadisticasTXT(ArrayList<Estadistica> estadisticas)  {

            try{
                FileWriter archivoSalida;
                BufferedWriter bufferSalida;

                archivoSalida = null;
                archivoSalida = new FileWriter(config.getString("rutaEstadisticas"), true); // Appends to the archivosFile
                bufferSalida = new BufferedWriter(archivoSalida);
                //String personajeString;
                for (Estadistica estadistica: estadisticas){
                    //personajeString = habitaciones.getNumero() +"%%" +personaje.getPais() +"%%" + personaje.getEdad() + "%%" + personaje.getCodigoPelicula();
                    bufferSalida.write(estadistica.toString()+ "\n" );
                }
                bufferSalida.flush();
                bufferSalida.close();

                archivoSalida.close();
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("Error al guardar el archivo: " + e.getMessage());
            }
        }
    //-------------------------------------------------------------------------------
    public static List<Object> cargarRecursoSerializadoXML(String rutaArchivo) throws IOException {
        List<Object> objetosXML = new ArrayList<>();
        
        Thread hiloDeserializacion = new Thread(() -> {
            try (XMLDecoder decodificadorXML = new XMLDecoder(new FileInputStream(rutaArchivo))) {
                // Leer todos los objetos del archivo XML
                while (true) {
                    try {
                        Object objetoXML = decodificadorXML.readObject();
                        synchronized (objetosXML) {
                            objetosXML.add(objetoXML);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        break; // Fin del archivo
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Archivo no encontrado, se creará uno nuevo al guardar.");
            } catch (@SuppressWarnings("hiding") IOException e) {
                System.err.println("Error al deserializar el objeto: " + e.getMessage());
            }
        });
    
        // Iniciar el hilo
        hiloDeserializacion.start();
    
        // Esperar a que el hilo termine
        try {
            hiloDeserializacion.join();
        } catch (InterruptedException e) {
            System.err.println("El hilo fue interrumpido: " + e.getMessage());
        }
    
        return objetosXML;
    }

    public static void salvarRecursoSerializadoXML(String rutaArchivo, Object nuevoObjeto) throws IOException {
        List<Object> objetosExistentes = cargarRecursoSerializadoXML(rutaArchivo);
        objetosExistentes.add(nuevoObjeto); // Agregar el nuevo objeto a la lista
    
        // Crear un nuevo hilo para realizar la serialización
        Thread hiloSerializacion = new Thread(() -> {
            try (XMLEncoder codificadorXML = new XMLEncoder(new FileOutputStream(rutaArchivo))) {
                for (Object objeto : objetosExistentes) {
                    codificadorXML.writeObject(objeto); // Escribir cada objeto en el archivo XML
                }
                System.out.println("Serialización completada en el hilo: " + Thread.currentThread().getName());
            } catch (IOException e) {
                System.err.println("Error al serializar el objeto: " + e.getMessage());
            }
        });
    
        // Iniciar el hilo
        hiloSerializacion.start();
    }


    public static void generarReporte(ArrayList<Vendedor> vendedores) {
        StringBuilder reporte = new StringBuilder("-----Reporte de vendedores y productos-----\n");

        for (Vendedor vendedor : vendedores) {
            reporte.append(vendedor.getNombre()).append(" - ").append(vendedor.getUsuario()).append("\n");

            // Productos publicados
            List<Producto> productosPublicados = vendedor.getProductos();
            if (productosPublicados.isEmpty()) {
                reporte.append("-sin productos publicados-\n");
            } else {
                reporte.append("Productos publicados:\n");
                for (int i = 0; i < productosPublicados.size(); i++) {
                    Producto producto = productosPublicados.get(i);
                    reporte.append(i + 1).append("- ")
                            .append(producto.getTitulo()).append(", $")
                            .append(producto.getPrecio()).append("\n");
                }
            }

            // Productos vendidos
            List<Producto> productosVendidos = vendedor.getProductosVendidos(); // Asumimos que existe este método.
            if (productosVendidos == null || productosVendidos.isEmpty()) {
                reporte.append("-sin productos vendidos-\n");
            } else {
                reporte.append("Productos vendidos:\n");
                for (int i = 0; i < productosVendidos.size(); i++) {
                    Producto producto = productosVendidos.get(i);
                    reporte.append(i + 1).append("- ")
                            .append(producto.getTitulo()).append(", $")
                            .append(producto.getPrecio()).append("\n");
                }
            }
            reporte.append("\n");
        }

        // Mostrar en consola
        System.out.println(reporte);

        // Guardar en archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(config.getString("rutaEstadisticas"), true))) {
            writer.write(reporte.toString());
        } catch (IOException e) {
            System.err.println("Error al guardar el reporte en archivo: " + e.getMessage());
        }
    }
}