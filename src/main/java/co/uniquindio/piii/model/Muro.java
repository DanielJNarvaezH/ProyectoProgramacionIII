package co.uniquindio.piii.model;
import java.io.Serializable;
import java.util.ArrayList;  

public class Muro implements Serializable{

    private ArrayList<Producto> productos;

    

    
    public Muro() {
        productos = new ArrayList<>();
    }
    

    @Override
    public String toString() {
        return "Muro [productos=" + productos + "]";
    }


    public ArrayList<Producto> getProductos() {
        return productos;
    }
    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }
    public void agregarProducto(Producto producto){
        
    }
    public ArrayList<Producto> obtenerProductosOrdenadosPorFecha(){
        return new ArrayList<>();
    }


    


}
