package Clases;

import DAO.CatalogoProductosDAO;

import java.util.ArrayList;

public class CatalogoProductos {

    // Declaración de variables de instancia
    private final CatalogoProductosDAO catalogoProductosDAO;
    private final ArrayList<Producto> listaProductos;

    // Constructor de la clase
    public CatalogoProductos() {
        // Inicialización del DAO para acceder al catálogo de productos
        catalogoProductosDAO = new CatalogoProductosDAO();
        // Carga inicial de productos desde el archivo
        listaProductos = catalogoProductosDAO.cargarProductos();
    }

    // Método para obtener la lista de productos actualizada
    public ArrayList<Producto> obtenerProductos() {
        return new ArrayList<>(listaProductos);
    }

    // Método para agregar un nuevo producto al catálogo
    public boolean agregarProducto(Producto producto) {
        // Verificar si el producto ya existe en el catálogo
        boolean existente = false;
        int i = 0;
        while (i < listaProductos.size() && !existente) {
            Producto p = listaProductos.get(i);
            if (p.getCodigo() == producto.getCodigo()) {
                existente = true;
            } else {
                i++;
            }
        }
        // Si el producto no existe, agregarlo al catálogo y guardar en el archivo
        if (!existente) {
            listaProductos.add(producto);
            catalogoProductosDAO.guardarProducto(producto);
        }
        return existente;
    }

    // Método para modificar un producto en el catálogo
    public boolean modificarProducto(Producto productoModificado) {
        boolean modificado = false;
        int i = 0;
        while (i < listaProductos.size() && !modificado) {
            Producto producto = listaProductos.get(i);
            if (producto.getCodigo() == productoModificado.getCodigo()) {
                modificado = true;
            } else {
                i++;
            }
        }
        if (modificado) {
            listaProductos.set(i, productoModificado);
            catalogoProductosDAO.modificarProducto(listaProductos);
        }
        return modificado;
    }

    // Método para eliminar un producto del catálogo
    public boolean eliminarProducto(Producto producto) {
        boolean existente = false;
        int i = 0;
        while (i < listaProductos.size() && !existente) {
            Producto p = listaProductos.get(i);
            if (p.getCodigo() == producto.getCodigo()) {
                existente = true;
            } else {
                i++;
            }
        }
        if (existente) {
            listaProductos.remove(i);
            catalogoProductosDAO.eliminarProducto(listaProductos);
        }
        return existente;
    }

    // Método para obtener un producto del catálogo por su código
    public Producto obtenerProductoPorCodigo(int codigo) {
        Producto productoEncontrado = null;
        int i = 0;
        while (i < listaProductos.size() && productoEncontrado == null) {
            Producto producto = listaProductos.get(i);
            if (producto.getCodigo() == codigo) {
                productoEncontrado = producto;
            }
            i++;
        }
        return productoEncontrado;
    }

    // Método para verificar si un código de producto existe en el catálogo
    public boolean existeCodigoProducto(int codigo) {
        boolean encontrado = false;
        int i = 0;
        while (!encontrado && i < listaProductos.size()) {
            Producto producto = listaProductos.get(i);
            if (producto.getCodigo() == codigo) {
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }
}
