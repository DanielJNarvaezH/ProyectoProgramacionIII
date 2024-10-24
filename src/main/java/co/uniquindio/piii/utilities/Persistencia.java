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

import co.uniquindio.piii.model.Vendedor;




public class Persistencia {
    private static Persistencia instance;
    public static Persistencia getInstance() {
        if (instance == null) {
            instance = new Persistencia();
        }
        return instance;
    }

    public static void serializarObjetoBinario(String nombre, Object objeto) throws IOException{
        ObjectOutputStream salida;

        salida = new  ObjectOutputStream(new FileOutputStream(nombre));
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

    public static void serializarObjetoXML(String nombre, Object objeto) throws IOException{

        XMLEncoder codificador;

        codificador = new XMLEncoder( new FileOutputStream(nombre));
        codificador.writeObject(objeto);
        codificador.close();
    }

    public static Object deserializarObjetoXML (String nombre) throws IOException{
        XMLDecoder decodificador;
        Object objeto;

        decodificador = new XMLDecoder( new FileInputStream(nombre));
        objeto = decodificador.readObject();
        decodificador.close();

        return objeto;
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


        public static void guardarVendedoresTXT(ArrayList<Vendedor> vendedores)  {

            try{
                FileWriter archivoSalida;
                BufferedWriter bufferSalida;

                archivoSalida = null;
                archivoSalida = new FileWriter("C:\\Users\\Dell\\Documents\\.vscode\\ProyectoProgramacionIII\\proyectoProgramacionIII\\src\\main\\java\\co\\uniquindio\\piii\\persistencia\\archivos\\vendedores.txt", true); // Appends to the archivosFile
                bufferSalida = new BufferedWriter(archivoSalida);
                //String personajeString;
                for (Vendedor vendedor: vendedores){
                    //personajeString = habitaciones.getNumero() +"%%" +personaje.getPais() +"%%" + personaje.getEdad() + "%%" + personaje.getCodigoPelicula();
                    bufferSalida.write(vendedor.toString()+ "\n" );
                }
                bufferSalida.flush();
                bufferSalida.close();

                archivoSalida.close();
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("Error al guardar el archivo: " + e.getMessage());
            }
        }
    

}