package co.uniquindio.piii.model;
import java.time.LocalDate;

public class Contacto {

    private Vendedor contacto;
    private LocalDate fechaSolicitud;

    


    public Vendedor getContacto() {
        return contacto;
    }
    public void setContacto(Vendedor contacto) {
        this.contacto = contacto;
    }
    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }
    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    
    public Contacto(Vendedor contacto, LocalDate fechaSolicitud) {
        this.contacto = contacto;
        this.fechaSolicitud = fechaSolicitud;
    }

    
    @Override
    public String toString() {
        return "Contacto [contacto=" + contacto + ", fechaSolicitud=" + fechaSolicitud + "]";
    }
    
    public void acpetarContacto(){

    }
    public void rechazarContacto(){

    } 

}
