package co.uniquindio.piii.utilities;

import java.time.LocalDate;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import co.uniquindio.piii.model.Producto;
import co.uniquindio.piii.model.Vendedor;


//Clase para definir la estructura de serialización en XML de un producto 
class ProductoConverter implements Converter {
    @Override
    public boolean canConvert(@SuppressWarnings("rawtypes") Class type) {
        return type.equals(Producto.class);
    }

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        Producto producto = (Producto) source;
        writeNode(writer, "titulo", producto.getTitulo());
        writeNode(writer, "descripcion", producto.getDescripcion());
        writeNode(writer, "fechaPublicacion", producto.getFechaPublicacion() != null ? producto.getFechaPublicacion().toString() : null);
        writeNode(writer, "Categoria", String.valueOf(producto.getCategoria()));
        //writeNode(writer, "numLikes", String.valueOf(producto.getNumLikes()));
        writeNode(writer, "vendedor", producto.getVendedor() != null ? producto.getVendedor().getNombre() : null);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Producto producto = new Producto();
        producto.setTitulo(readNodeValue(reader, "titulo"));
        producto.setDescripcion(readNodeValue(reader, "descripcion"));
        String fechaPublicacion = readNodeValue(reader, "fechaPublicacion");
        if (fechaPublicacion != null) {
            producto.setFechaPublicacion(LocalDate.parse(fechaPublicacion));
        }
        /*String numLikes = readNodeValue(reader, "numLikes");
        if (numLikes != null) {
            producto.setNumLikes(Integer.parseInt(numLikes));
        }*/
        String vendedorNombre = readNodeValue(reader, "vendedor");
        if (vendedorNombre != null) {
            producto.setVendedor(new Vendedor(vendedorNombre, null, null, null,null, null));
        }
        // Agrega más campos, si los hay
        return producto;
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
}

