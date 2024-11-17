package co.uniquindio.piii.model;

import java.io.*;
import java.net.Socket;
import java.util.function.Consumer;

public class Cliente {
    private String usuario;
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public Cliente(String usuario) {
        this.usuario = usuario;
    }

    public void conectar() throws IOException {
        socket = new Socket("localhost", 12345);
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());

        // Envía el nombre de usuario al servidor
        output.writeUTF(usuario);
        output.flush();
    }

    /**
     * Enviar mensaje al servidor indicando destinatario y mensaje.
     *
     * @param destinatario Usuario destinatario del mensaje.
     * @param mensaje      Mensaje a enviar.
     * @throws IOException Si ocurre un error al enviar.
     */
    public void enviarMensaje(String destinatario, String mensaje) throws IOException {
        output.writeUTF(destinatario + ":" + mensaje);
        output.flush();
    }

    /**
     * Escucha mensajes desde el servidor y los procesa usando un callback.
     *
     * @param onMensajeRecibido Función que recibe cada mensaje y lo procesa.
     */
    public void escucharMensajes(Consumer<String> onMensajeRecibido) {
        new Thread(() -> {
            try {
                String mensaje;
                while ((mensaje = input.readUTF()) != null) {
                    onMensajeRecibido.accept(mensaje); // Procesa el mensaje recibido
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Desconecta el cliente del servidor.
     *
     * @throws IOException Si ocurre un error al cerrar la conexión.
     */
    public void desconectar() throws IOException {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }

    // Getters y Setters para los atributos necesarios

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
