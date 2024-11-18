package co.uniquindio.piii.model;

import java.io.*;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;

public class Servidor {
    private static final int PORT = 12345;
    private static ConcurrentHashMap<String, ObjectOutputStream> clientes = new ConcurrentHashMap<>();
    private ServerSocket serverSocket;

    public Servidor() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado en el puerto " + PORT);
    }

    // Método para iniciar el servidor en un hilo separado
    public void iniciar() {
        new Thread(() -> {
            try {
                while (true) {
                    Socket socket = serverSocket.accept();
                    new Thread(new ManejadorCliente(socket)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Manejo de los clientes
    static class ManejadorCliente implements Runnable {
        private Socket socket;
        private String usuario;

        public ManejadorCliente(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                 ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {

                usuario = input.readUTF(); // Recibir el nombre del usuario
                clientes.put(usuario, output);

                String mensaje;
                while ((mensaje = input.readUTF()) != null) {
                    String[] partes = mensaje.split(":", 2);
                    String destinatario = partes[0];
                    String contenido = partes[1];

                    if (clientes.containsKey(destinatario)) {
                        ObjectOutputStream salidaDestinatario = clientes.get(destinatario);
                        salidaDestinatario.writeUTF("Solicitud de " + usuario + ": " + contenido);
                        salidaDestinatario.flush();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                clientes.remove(usuario);
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
