package co.uniquindio.piii.model;

import java.util.ArrayList;

import co.uniquindio.piii.utilities.*;

public class PruebaServidor {

    public static void main(String[] args) {

        EjemploLog.logInfo("primera prueba del servidor");
        EjemploLog.logInfo("segunda prueba del servidor");


 
        Vendedor vendedor1 = new Vendedor("Samuel", "", "", "");
        Vendedor vendedor2 = new Vendedor("Matías", "","", "");
        Vendedor vendedor3 = new Vendedor("Hugo", "", "", "");
        Vendedor vendedor4 = new Vendedor("Daniel", "", "", "");

        ArrayList<Vendedor> listaVendedores = new ArrayList<>();

        listaVendedores.add(vendedor1);
        listaVendedores.add(vendedor2);
        listaVendedores.add(vendedor3);
        listaVendedores.add(vendedor4);


        Persistencia.guardarVendedoresTXT(listaVendedores);

    }

}
