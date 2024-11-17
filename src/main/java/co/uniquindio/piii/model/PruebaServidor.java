package co.uniquindio.piii.model;

import java.util.ArrayList;

import co.uniquindio.piii.utilities.*;

public class PruebaServidor {

    public static void main(String[] args) {

        EjemploLog.logInfo("primera prueba del servidor");
        EjemploLog.logInfo("segunda prueba del servidor");



        Estadistica estadistica1 = new Estadistica(10, 20, 5, 0);
        Estadistica estadistica2 = new Estadistica(3, 2, 4, 0);
        Estadistica estadistica3 = new Estadistica(1, 0, 2, 0);
        Estadistica estadistica4 = new Estadistica(5, 7, 8, 0);


        ArrayList<Estadistica> listaEstadisticas = new ArrayList<>();

        listaEstadisticas.add(estadistica1);
        listaEstadisticas.add(estadistica2);
        listaEstadisticas.add(estadistica3);
        listaEstadisticas.add(estadistica4);

        Persistencia.guardarEstadisticasTXT(listaEstadisticas);

        

    }

}
