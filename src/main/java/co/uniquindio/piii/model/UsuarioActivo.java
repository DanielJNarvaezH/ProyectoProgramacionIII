package co.uniquindio.piii.model;

//Clase para obtener el usuario que se loguee en la app

public class UsuarioActivo extends Vendedor {

    private static UsuarioActivo instance;
    private String username;

    // Constructor privado para evitar instancias externas
    private UsuarioActivo() {}

    // Método para obtener la única instancia de la clase
    public static UsuarioActivo getInstance() {
        if (instance == null) {
            instance = new UsuarioActivo();
        }
        return instance;
    }

    // Métodos para establecer y obtener el usuario logueado
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    // Método para limpiar el usuario activo
    public void clear() {
        username = null;
    }
}

