package co.uniquindio.piii.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class ManejoArchivos {

    private static File directorio;
    private static String salida;

    public static String obtenerListaDirectoriosNoOcultos( String dirConsulta){

        directorio = new File(dirConsulta);

        salida = "Los directorios(carpetas) no ocultas de "+ dirConsulta + "son:\n";

        for (File elemento : directorio.listFiles()){

            if(elemento.isDirectory() && !elemento.isHidden()){
                salida += elemento.getName() + "\n";
            }
        } 

        return salida;
    }
    
    public static void almacenarDatos(String nombre, String formato, Object...args) throws IOException{

            
            FileWriter archivoSalida;
            Formatter archivo;
        
            archivoSalida = new FileWriter(nombre, true);
            archivo = new Formatter(archivoSalida);
            archivo.format(formato, args);
            archivo.flush();
            archivo.close();
    }

    public static void escribirArchivo(String nombreArchivo, ArrayList<String> texto, boolean adicionar) throws IOException{
        
            FileWriter archivoSalida;
            BufferedWriter bufferSalida;
        
            archivoSalida = null;
            archivoSalida = new FileWriter(nombreArchivo,adicionar);
            bufferSalida = new BufferedWriter(archivoSalida);

            for( String linea : texto){
                bufferSalida.write(linea + "\n");

            }
            bufferSalida.flush();
            bufferSalida.close();
            
            archivoSalida.close();
           
    }

    public static ArrayList<String> leerLineas(String nombreArchivo) throws IOException{
        Scanner datosEntrada; 
        ArrayList<String> contenidoTexto;

        contenidoTexto = new ArrayList<>();

        datosEntrada = new Scanner(new File(nombreArchivo));

        while(datosEntrada.hasNext()){
            contenidoTexto.add(datosEntrada.nextLine());
        }
        datosEntrada.close();
        return contenidoTexto;
    }

    public static ArrayList<String> leerArchivo(String nombreArchivo)throws IOException {

        FileReader archivoEntrada;
        BufferedReader bufferEntrada;
        ArrayList<String> contenidoTexto;
        String linea;

        archivoEntrada = null;
        contenidoTexto = new ArrayList<>();

        archivoEntrada = new FileReader(nombreArchivo);
        bufferEntrada = new BufferedReader(archivoEntrada);

        while((linea = bufferEntrada.readLine()) != null){
            contenidoTexto.add(linea);
        }

        bufferEntrada.close();
        archivoEntrada.close();

        return contenidoTexto;
        
    }

}
