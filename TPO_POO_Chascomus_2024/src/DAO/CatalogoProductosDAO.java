package DAO;

import Clases.Producto;
import Clases.Electronico;
import Clases.Comida;
import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.ArrayList;

public class CatalogoProductosDAO {

    // Definición de la ruta del archivo donde se guarda el catálogo de productos
    private static final String ARCHIVO_CATALOGO = "Archivos/CatalogoProductos.txt";

    // Método para cargar los productos desde el archivo de catálogo
    public ArrayList<Producto> cargarProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        try {
            // Verificar que exista el archivo.
            File archivo = new File(ARCHIVO_CATALOGO);
            if (!archivo.exists()) {
                // Si el archivo no existe, crearlo.
                archivo.createNewFile();
            }

            // Leer los productos del archivo
            try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_CATALOGO))) {
                String linea;
                // Leer cada línea del archivo y crear el objeto Producto correspondiente
                while ((linea = br.readLine()) != null) {
                    Producto producto = obtenerProducto(linea);
                    productos.add(producto);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return productos;
    }

    // Método para guardar un nuevo producto en el catálogo
    public void guardarProducto(Producto producto) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_CATALOGO, true))) {
            // Escribir la representación del producto en el archivo
            bw.write(producto.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para modificar el catálogo de productos
    public void modificarProducto(ArrayList<Producto> listaProductos) {
        // Eliminar el archivo original
        File archivoOriginal = new File(ARCHIVO_CATALOGO);
        archivoOriginal.delete();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoOriginal))) {
            // Escribir la lista actualizada en el nuevo archivo
            for (Producto p : listaProductos) {
                bw.write(p.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un producto del catálogo
    public void eliminarProducto(ArrayList<Producto> listaProductos) {
        // Eliminar el archivo original
        File archivoOriginal = new File(ARCHIVO_CATALOGO);
        archivoOriginal.delete();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoOriginal))) {
            // Escribir la lista actualizada en el nuevo archivo
            for (Producto p : listaProductos) {
                bw.write(p.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método privado para obtener un objeto Producto a partir de una línea del archivo
    private Producto obtenerProducto(String linea) {
        String[] partes = linea.split(";");
        String tipoProducto = partes[0];
        int codigo = Integer.parseInt(partes[1]);
        String nombre = partes[2];
        String descripcion = partes[3];
        double precio = Double.parseDouble(partes[4]);

        Producto producto;
        // Verificar el tipo de producto y crear el objeto correspondiente
        if (tipoProducto.equals("Electronico")) {
            int garantia = Integer.parseInt(partes[5]);
            producto = new Electronico(codigo, nombre, descripcion, precio, garantia);
        } else {
            String fechaVencimiento = partes[5];
            double peso = Double.parseDouble(partes[6]);
            producto = new Comida(codigo, nombre, descripcion, precio, fechaVencimiento, peso);
        }
        return producto;
    }
}
