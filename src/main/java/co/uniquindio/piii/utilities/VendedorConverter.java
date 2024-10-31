package co.uniquindio.piii.utilities;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import co.uniquindio.piii.model.Producto;
import co.uniquindio.piii.model.Vendedor;

//Clase para definir la estructura de serialización en XML de un vendedor 
class VendedorConverter implements Converter {
    public UnmarshallingContext context;
    @Override
    public boolean canConvert(@SuppressWarnings("rawtypes") Class type) {
        return type.equals(Vendedor.class);
    }

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        Vendedor vendedor = (Vendedor) source;
        writeNode(writer, "nombre", vendedor.getNombre());
        writeNode(writer, "usuario", vendedor.getUsuario());
        writeNode(writer, "contrasena", vendedor.getContrasena());
        writeNode(writer, "email", vendedor.getEmail());
        writeCollection(writer, "productos", vendedor.getProductos());
        writeCollection(writer, "contactos", vendedor.getContactos());
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Vendedor vendedor = new Vendedor(null, null, null, null);
        vendedor.setNombre(readNodeValue(reader, "nombre"));
        vendedor.setUsuario(readNodeValue(reader, "usuario"));
        vendedor.setContrasena(readNodeValue(reader, "contrasena"));
        vendedor.setEmail(readNodeValue(reader, "email"));
        vendedor.setProductos((ArrayList<Producto>) readCollection(reader, "productos", Producto.class));
        vendedor.setContactos((ArrayList<Vendedor>) readCollection(reader, "contactos", Vendedor.class));
        return vendedor;
    }

    private void writeNode(HierarchicalStreamWriter writer, String nodeName, String value) {
        if (value != null) {
            writer.startNode(nodeName);
            writer.setValue(value);
            writer.endNode();
        }
    }

    private String readNodeValue(HierarchicalStreamReader reader, String nodeName) {
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            if (reader.getNodeName().equals(nodeName)) {
                String value = reader.getValue();
                reader.moveUp();
                return value;
            }
            reader.moveUp();
        }
        return null;
    }

    private void writeCollection(HierarchicalStreamWriter writer, String nodeName, List<?> collection) {
        if (collection != null) {
            for (Object item : collection) {
                writer.startNode(nodeName);
                context.convertAnother(item, item.getClass());
                writer.endNode();
            }
        }
    }

    private <T> List<T> readCollection(HierarchicalStreamReader reader, String nodeName, Class<T> type) {
        List<T> collection = new ArrayList<>();
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            if (reader.getNodeName().equals(nodeName)) {
                @SuppressWarnings("unchecked")
                T item = (T) context.convertAnother(null, type);
                collection.add(item);
            }
            reader.moveUp();
        }
        return collection;
    }
}


