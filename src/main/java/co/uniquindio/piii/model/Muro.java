package co.uniquindio.piii.model;
import java.io.Serializable;
import java.util.ArrayList;  

public class Muro implements Serializable{

    private ArrayList<Producto> productos;
    private Vendedor vendedor;

    

    
    public Muro(Vendedor vendedor) {
        productos = new ArrayList<>();
        this.vendedor = vendedor;
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

    public Vendedor getVendedor(){
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor){
        this.vendedor = vendedor;
    }

    public void agregarProducto(Producto producto){
        
    }
    public ArrayList<Producto> obtenerProductosOrdenadosPorFecha(){
        return new ArrayList<>();
    }


    


}
