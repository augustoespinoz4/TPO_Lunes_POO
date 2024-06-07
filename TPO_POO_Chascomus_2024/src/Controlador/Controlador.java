package Controlador;

import Clases.*;
import DAO.UsuarioDAO;

import java.util.ArrayList;

import static Mail.EnvioCorreo.*;

public class Controlador {

    private final UsuarioDAO usuarioDAO;
    private final ArrayList<Usuario> listaUsuarios;
    private final CatalogoProductos catalogoProductos;
    private final Carrito carrito;

    public Controlador() {
        this.usuarioDAO = new UsuarioDAO();
        this.listaUsuarios = usuarioDAO.cargarUsuarios();
        this.catalogoProductos = new CatalogoProductos();
        this.carrito = new Carrito();
    }

    // ------------------------------------------------------ USUARIOS -------------------------------------------------------
    public Usuario login(String correoElectronico, String contrasena) {
        // Inicializar el resultado como null
        Usuario resultado = null;

        // Inicializar el índice
        int i = 0;

        // Buscar el usuario mientras no se haya encontrado y queden elementos por revisar
        while (resultado == null && i < listaUsuarios.size()) {
            Usuario usuario = listaUsuarios.get(i);
            // Verificar si el correo electrónico y la contraseña coinciden con algún usuario
            if (usuario.getCorreoElectronico().equals(correoElectronico) && usuario.getContrasena().equals(contrasena)) {
                resultado = usuario; // Asignar el usuario correspondiente al resultado
            }
            i++; // Incrementar el índice para pasar al siguiente usuario
        }

        // Devolver el resultado del inicio de sesión
        return resultado;
    }

    public boolean registro(String nombreCompleto, String correoElectronico, String contrasena) {
        // Inicializar la variable de registro como verdadera
        boolean noEstaRegistrado = true;

        // Inicializar el índice
        int i = 0;

        // Verificar si el correo electrónico ya está en uso
        while (noEstaRegistrado && i < listaUsuarios.size()) {
            Usuario usuario = listaUsuarios.get(i);
            if (usuario.getCorreoElectronico().equals(correoElectronico)) {
                noEstaRegistrado = false; // El correo electrónico ya está en uso
            }
            i++; // Incrementar el índice para pasar al siguiente usuario
        }

        // Si el correo electrónico no está en uso, proceder con el registro
        if (noEstaRegistrado) {
            // Crear un nuevo usuario
            Usuario nuevoUsuario = new Cliente(nombreCompleto, correoElectronico, contrasena);
            // guardar el nuevo usuario
            listaUsuarios.add(nuevoUsuario);
            noEstaRegistrado = usuarioDAO.guardarUsuario(nuevoUsuario);
        }

        // Devolver el estado del registro
        return noEstaRegistrado;
    }

    // ------------------------------------------------------ CATALOGO DE PRODUCTOS -------------------------------------------------------
    public boolean darAltaProducto(Producto producto) {
        return catalogoProductos.agregarProducto(producto);
    }

    public int generarCodigoProductoUnico() {
        int codigo = 1;
        while (catalogoProductos.existeCodigoProducto(codigo)) {
            codigo++;
        }
        return codigo;
    }

    public boolean darBajaProducto(Producto producto) {
        return catalogoProductos.eliminarProducto(producto);
    }

    public boolean modificarProducto(Producto productoModificado) {
        return catalogoProductos.modificarProducto(productoModificado);
    }

    public Producto obtenerProductoPorCodigo(int codigo) {
        return catalogoProductos.obtenerProductoPorCodigo(codigo);
    }

    public ArrayList<Producto> obtenerProductos() {
        return catalogoProductos.obtenerProductos();
    }


    // ------------------------------------------------------ CARRITO DE COMPRAS -------------------------------------------------------
    public Carrito getCarrito() {
        return carrito;
    }

    public ArrayList<Producto> obtenerProdCarrito() {
        return carrito.obtenerProductos();
    }

    public void agregarProdCarrito(Producto producto) {
        carrito.agregarItem(producto);
    }

    public void eliminarProdCarrito(Producto producto) {
        carrito.eliminarItem(producto);
    }

    public void limpiarCarrito() {
        carrito.borrarCarrito();
    }

    // ------------------------------------------------------ ENVÍO DE CORREOS -------------------------------------------------------
    public boolean enviarMail(String destinatario, String nombreUsuario, String tipoMensaje) {
        return enviarCorreo(destinatario, nombreUsuario, tipoMensaje);
    }

}