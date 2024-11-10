package co.uniquindio.piii.model;

import java.io.Serializable;
import java.time.LocalDate;
import co.uniquindio.piii.exceptions.ContactoYaExistenteException;
import co.uniquindio.piii.exceptions.LimiteContactosExcedidoException;
import co.uniquindio.piii.exceptions.ContactoNoEncontradoException;

public class Contacto extends Vendedor implements Serializable {

    private LocalDate fechaSolicitud;

    // Constructor
    public Contacto(String nombre, String usuario, String contraseña, String email, String direccion, String id,LocalDate fechaSolicitud) {
        super(nombre, usuario, contraseña, email, direccion, id); // Usa el constructor de Vendedor
        this.fechaSolicitud = fechaSolicitud;
    }

    // Métodos para aceptar y rechazar la solicitud
    public void aceptarContacto() throws ContactoYaExistenteException, LimiteContactosExcedidoException {
        if (getContactos().size() >= 9) {
            throw new LimiteContactosExcedidoException("El límite de contactos ha sido excedido.");
        }
        if (getContactos().contains(this)) {
            throw new ContactoYaExistenteException("Este contacto ya existe en la lista.");
        }
        getContactos().add(this); // Agregar este contacto a la lista de contactos del vendedor
    }

    public void rechazarContacto() throws ContactoNoEncontradoException {
        if (!getContactos().contains(this)) {
            throw new ContactoNoEncontradoException("El contacto no fue encontrado en la lista.");
        }
        getContactos().remove(this); // Eliminar este contacto de la lista de contactos del vendedor
    }

    // Getters y Setters específicos para Contacto
    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    @Override
    public String toString() {
        return "Contacto [nombre=" + getNombre() + ", email=" + getEmail() + ", fechaSolicitud=" + fechaSolicitud + "]";
    }
}