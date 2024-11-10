package co.uniquindio.piii.utilities;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.Calendar;

import co.uniquindio.piii.model.Estadistica;
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
    

    public static Object cargarRecursoSerializadoXML(String rutaArchivo) throws IOException {
        XMLDecoder decodificadorXML;
        Object objetoXML;
        decodificadorXML = new XMLDecoder(new FileInputStream(rutaArchivo));
        objetoXML = decodificadorXML.readObject();
        decodificadorXML.close();
        return objetoXML;
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
    /* 
    public void guardarHabitacionesTXT(ArrayList<Habitacion> habitaciones) throws IOException {
        writer = new BufferedWriter(new FileWriter("habitaciones.txt"));
        String personajeString;
        for (Personaje personaje: personajes){
            personajeString = habitaciones.getNumero() +"%%" +personaje.getPais() +"%%" + personaje.getEdad() + "%%" + personaje.getCodigoPelicula();
            writer.write(personajeString);
            writer.newLine();
            writer.flush();
        }
        writer.close();
    }
    public void guardarSeriesTXT(ArrayList<Habitacion> habitaciones) throws IOException{
        writer = new BufferedWriter(new FileWriter("habitaciones.txt"));
        String serieString;
        for (Serie serie: series){
            serieString = serie.getTitulo() +"%%" +serie.getGenero() +"%%" + serie.getAñoInicio() + "%%" + serie.getCodigo() + "%%"  + serie.getPersonajes();
            writer.write(serieString);
            writer.newLine();
            writer.flush();
        }*/


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
        public static void agregarObjetoXML(String rutaArchivo, Vendedor nuevoVendedor) throws IOException {
        List<Vendedor> vendedores = cargarObjetosDesdeXML(rutaArchivo);
        vendedores.add(nuevoVendedor);
        serializarListaObjetosXML(rutaArchivo, vendedores);
    }

    // Método para leer la lista de objetos del archivo XML
    @SuppressWarnings("unchecked")
    public static List<Vendedor> cargarObjetosDesdeXML(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            return new ArrayList<>(); // Si el archivo no existe, devuelve una lista vacía
        }
        try (FileInputStream fis = new FileInputStream(rutaArchivo)) {
            XStream xstream = new XStream(new DomDriver());
            xstream.alias("vendedor", Vendedor.class);
            return (List<Vendedor>) xstream.fromXML(fis);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar el archivo XML.");
            return new ArrayList<>();
        }
    }

    // Método para serializar una lista completa de objetos al archivo XML
    public static void serializarListaObjetosXML(String rutaArchivo, List<Vendedor> listaVendedores) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(rutaArchivo)) {
            XStream xstream = new XStream(new DomDriver());
            xstream.alias("vendedor", Vendedor.class);
            xstream.toXML(listaVendedores, fos);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al serializar la lista en XML.");
        }
    }
}