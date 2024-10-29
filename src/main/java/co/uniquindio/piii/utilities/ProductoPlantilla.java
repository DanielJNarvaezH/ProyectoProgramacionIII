package co.uniquindio.piii.utilities;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import co.uniquindio.piii.model.Producto;


@XmlRootElement(name = "productos")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductoPlantilla {
    @XmlElement(name = "producto")
    private List<Producto> productos = new ArrayList<>();

    public void add(Producto producto) {
        productos.add(producto);
    }

    public void saveToXml(String fileName) {
        try {
            JAXBContext context = JAXBContext.newInstance(ProductoList.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            File file = new File(fileName);
            marshaller.marshal(this, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static ProductoPlantilla loadFromXml(String fileName) {
        try {
            JAXBContext context = JAXBContext.newInstance(ProductoPlantilla.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            File file = new File(fileName);
            return (ProductoPlantilla) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}