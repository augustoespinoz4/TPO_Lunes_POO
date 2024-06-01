package Clases;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CatalogoProductos {
    private  ArrayList<Producto> ListaProducto;

    public CatalogoProductos(ArrayList<Producto> listaProducto) {
        ListaProducto = listaProducto;
    }

    private void registrarEnArchivoProductos(){
        String nombreArchivo = "productos.txt"; // Nombre del archivo

        // Ruta de la carpeta fuera de src
        String rutaCarpeta = "archivos/";
        // Ruta completa del archivo
        String rutaArchivo = rutaCarpeta + nombreArchivo;

        // Verificar si la carpeta existe, si no existe, crearla
        File carpeta = new File(rutaCarpeta);
        if (!carpeta.exists()) {
            carpeta.mkdirs(); // Crea la carpeta y cualquier carpeta padre necesaria
        }

        // Escribir en el archivo de clientes
        FileWriter writer = null;
        try {
            writer = new FileWriter(rutaArchivo);
            for (Producto productoLista : ListaProducto) {
                writer.write(productoLista.toString() + "\n"); // Escribir cada objeto del tipo Producto seguido de un salto de línea
            }
            System.out.println("La lista de productos se ha guardado en el archivo " + rutaArchivo + " correctamente.");
            JOptionPane.showMessageDialog(null, "Se realizo correctamnte la accion!!!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar el archivo: " + e.getMessage());
                }
            }
        }

    }

    private boolean existeProducto(int codigo){
        boolean existe = false;
        int i = 0;
        while (i < ListaProducto.size() && !existe){
            if(ListaProducto.get(i).getCodigo() == codigo){
                existe = true;
            }
            i++;
        }
        return existe;
    }

    public void Agregar(Producto producto){
        if(!existeProducto(producto.getCodigo())){
            ListaProducto.add(producto);
            registrarEnArchivoProductos();
            // agraga el producto al archivo y a la lista
        }
        else{
            JOptionPane.showMessageDialog(null, "Error al agregar el producto, revisar codigo ya existente", "Error", JOptionPane.ERROR_MESSAGE);
            // mostrar error al encontrar codigo existente
        }

    }
    public void Modificar(int codigo, String nombre,String descripcion, float precio){
        boolean encontre = false;
        int i = 0;
        if (existeProducto(codigo)) {
            while (i < ListaProducto.size() && !encontre) {
                if (ListaProducto.get(i).getCodigo() == codigo) {
                    // Mensaje de confirmacion de la accion
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas modificar este producto?", "Confirmación", JOptionPane.YES_NO_OPTION);

                    // Si el usuario confirma
                    if (respuesta == JOptionPane.YES_OPTION) {
                        ListaProducto.get(i).setDescripcion(descripcion);
                        ListaProducto.get(i).setNombre(nombre);
                        ListaProducto.get(i).setPrecio(precio);
                        encontre = true;
                        registrarEnArchivoProductos();
                    } else {
                        // Si rechaza
                        JOptionPane.showMessageDialog(null, "Modificación cancelada.");
                        return; // Salir del método sin hacer cambios
                    }
                }
                i++;
            }
        } else {
            // Mostrar mensaje de error si el producto no existe
            JOptionPane.showMessageDialog(null, "El producto con el código " + codigo + " no existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void Eliminar(int codigo){
        int i = 0;
        boolean soyEste = false;
        if (existeProducto(codigo)) {
            while (i < ListaProducto.size() && !soyEste) {
                if (ListaProducto.get(i).getCodigo() == codigo) {
                    soyEste = true;
                } else {
                    i++;
                }
            }
            // Obtener el producto que se eliminará
            Producto productoAEliminar = ListaProducto.get(i);

            // Mostrar un cuadro de diálogo de confirmación antes de eliminar el producto
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres eliminar el producto?\n\n" + "De nombre: "+ productoAEliminar.getNombre() +"\n" + "De codigo: "+ListaProducto.get(i).codigo +"\n", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

            // (hace clic en "Sí")
            if (confirmacion == JOptionPane.YES_OPTION) {
                ListaProducto.remove(i);
                registrarEnArchivoProductos();
            }
        } else {
            // Mostrar un mensaje emergente de error si el producto no existe
            JOptionPane.showMessageDialog(null, "El producto no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
