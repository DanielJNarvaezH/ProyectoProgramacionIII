package co.uniquindio.piii.model;

import java.io.Serializable;
import java.util.ArrayList; 

public class Tienda implements Serializable {

    private static Tienda instance;

    private ArrayList<Vendedor> vendedores;

    private String nombre;

    public static Tienda getInstance(String nombre) {  
        if (instance == null) {  
            instance = new Tienda(nombre);  
        }  
        return instance;  
    }

    public Tienda(ArrayList<Vendedor> vendedores, String nombre) {
        this.vendedores = vendedores;
        this.nombre = nombre;
    }

    public ArrayList<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(ArrayList<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tienda(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Tienda [vendedores=" + vendedores + ", nombre=" + nombre + "]";
    } 
    
    



}
