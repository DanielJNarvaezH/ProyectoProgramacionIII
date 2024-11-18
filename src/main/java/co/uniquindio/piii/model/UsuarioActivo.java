package co.uniquindio.piii.model;

//Clase para obtener el usuario que se loguee en la app

public class UsuarioActivo {

    // Instancia única del UsuarioActivo
    private static UsuarioActivo instancia;

    // Objeto Vendedor que representa al usuario activo
    private Vendedor vendedor;

    // Constructor privado para evitar instancias directas
    private UsuarioActivo() {}

    // Método para obtener la instancia única
    public static UsuarioActivo getInstance() {
        if (instancia == null) {
            instancia = new UsuarioActivo();
        }
        return instancia;
    }

    // Método para establecer el vendedor activo
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    // Método para obtener el vendedor activo
    public Vendedor getVendedor() {
        return vendedor;
    }

    // Método para reiniciar la instancia (opcional, útil al cerrar sesión)
    public void reset() {
        vendedor = null;
    }
}


