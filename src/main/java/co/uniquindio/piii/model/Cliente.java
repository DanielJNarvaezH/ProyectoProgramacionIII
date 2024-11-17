package co.uniquindio.piii.model;

import java.io.*;
import java.net.Socket;

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

        output.writeUTF(usuario);
        output.flush();

        new Thread(this::escucharMensajes).start();
    }

    public void enviarMensaje(String destinatario, String mensaje) throws IOException {
        output.writeUTF(destinatario + ":" + mensaje);
        output.flush();
    }

    private void escucharMensajes() {
        try {
            String mensaje;
            while ((mensaje = input.readUTF()) != null) {
                System.out.println(mensaje);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void desconectar() throws IOException {
        socket.close();
    }
}
