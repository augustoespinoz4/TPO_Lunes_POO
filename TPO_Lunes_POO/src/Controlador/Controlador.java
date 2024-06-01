package Controlador;
import java.io.*;

import Clases.*;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class Controlador {
    private ArrayList<Cliente> clientes;
    private ArrayList<Administrador> administradores;
    private  CatalogoProductos catalogo;

    private Carrito carrito;

    public Controlador() {
        this.clientes = new ArrayList<>();
        this.administradores = new ArrayList<>();
        this.catalogo = new CatalogoProductos(new ArrayList<>());
        this.carrito = new Carrito();
    }
    public boolean registro(String nombreCompleto,String email , String contrasena){
        // Inicializar la variable de registro como verdadera
        boolean noEstaRegistrado = true;

        // Inicializar el índice
        int i = 0;

        // Verificar si el correo electrónico ya está en uso
        while (noEstaRegistrado && i < clientes.size()) {
            Usuario usuario = clientes.get(i);
            if (usuario.getCorreoEletronico().equals(email)) {
                noEstaRegistrado = false; // El correo electrónico ya está en uso
            }
            i++; // Incrementar el índice para pasar al siguiente usuario
        }

        // Si el correo electrónico no está en uso, proceder con el registro
        if (noEstaRegistrado) {
            // Intentar guardar el nuevo usuario
            registrarCliente ( nombreCompleto ,  email ,  contrasena);
        }

        // Devolver el estado del registro
        return noEstaRegistrado;
    }
    public String realizarLogin(String email , String contrasena){ // de volver true o false para comprobar la existecia del usuario
        int i =0;
        String seEncontro="Ninguno";
        while (i < administradores.size() && !seEncontro.equals("Administrador")){
            if (Objects.equals(administradores.get(i).getCorreoEletronico(), email) && Objects.equals(administradores.get(i).getContrasena(), contrasena)){
                seEncontro="Administrador";
                // invocar el tipo de usuario con mesaje
            }
            else {
                i++;
            }
        }
        int j=0;
        while (j < clientes.size() && !seEncontro.equals("Cliente") && !seEncontro.equals("Administrador")){
            if (Objects.equals(clientes.get(j).getCorreoEletronico(), email) && Objects.equals(clientes.get(j).getContrasena(), contrasena)){
                seEncontro="Cliente";
                carrito.setCliente(clientes.get(j));
            }
            else {
                j++;
            }
        }
        return seEncontro;

    }
    public void registrarCliente (String nombreCompleto , String gmail , String contrasena){
        Cliente cliente= new Cliente(nombreCompleto,gmail,contrasena);
        clientes.add(cliente);
        String nombreArchivo = "clientes.txt"; // Nombre del archivo

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
            writer = new FileWriter(rutaArchivo, true); // "true" indica que se agregará al final del archivo
            PrintWriter pw = new PrintWriter(writer);

            // Escribir los datos del cliente en una línea nueva
            pw.println("Cliente," + nombreCompleto + "," + gmail + "," + contrasena);

            System.out.println("Cliente registrado exitosamente.");
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

    //-------------------------------------------- metodos para el carrito del usuario
    public void agregarProductoAlCarrito(Producto producto) {
        carrito.agregarItem(producto);
    }
    public void eleminarProductoCarrito(Producto producto) {
        carrito.eliminarItem(producto);
    }
    public void borrarCarrito(){
        carrito.borrarCarrito();
    }

    // ------------------------------------------ metodos para usar catalogo producto
    public void DarAltaProducto(Producto producto){
        catalogo.Agregar(producto);
    }
    public void DarBajaProducto(int codigo){
        catalogo.Eliminar(codigo);
    }

    public void modoficarProducto(int codigo,String nombre,String descripcion,float precio){
        catalogo.Modificar(codigo,nombre,descripcion,precio);
    }

    //-------------------------------------------
    // Cargar archivos

    public void cargarArchivos(){
        String nombreArchivoClientes = "clientes.txt";
        String rutaCarpeta = "archivos/";
        String rutaArchivoClientes = rutaCarpeta + nombreArchivoClientes;

        File archivoClientes = new File(rutaArchivoClientes);
        if (archivoClientes.exists()) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(archivoClientes));
                String linea;
                while ((linea = br.readLine()) != null) {
                    if(!linea.equals("")){
                        // Separar los datos de la línea y crear un cliente
                        String[] datosCliente = linea.split(",");
                        String tipo = datosCliente[0];
                        String nombreCompleto = datosCliente[1];
                        String gmail = datosCliente[2];
                        String contrasena = datosCliente[3];
                        if (Objects.equals(tipo, "Cliente")) {
                            Cliente cliente = new Cliente(nombreCompleto, gmail, contrasena);
                            clientes.add(cliente);
                        }
                        if(Objects.equals(tipo, "Administrador")){
                            Administrador administrador = new Administrador(nombreCompleto, gmail, contrasena);
                            administradores.add(administrador);
                        }
                    }
                }
                System.out.println("Clientes cargados desde el archivo correctamente.");
            } catch (IOException e) {
                System.err.println("Error al cargar clientes desde el archivo: " + e.getMessage());
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        System.err.println("Error al cerrar BufferedReader: " + e.getMessage());
                    }
                }
            }
        }


        ArrayList<Producto> ListaProducto = new ArrayList<>();
        nombreArchivoClientes = "productos.txt";
        rutaCarpeta = "archivos/";
        rutaArchivoClientes = rutaCarpeta + nombreArchivoClientes;

        File archivoProducots = new File(rutaArchivoClientes);
        if (archivoProducots.exists()) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(archivoProducots));
                String linea;
                while ((linea = br.readLine()) != null) {
                    // Separar los datos de la línea y crear un cliente
                    if(!linea.equals("")){
                        String[] datosProducto = linea.split(",");
                        String tipo = datosProducto[0];
                        int codigo = Integer.parseInt(datosProducto[1]);
                        String nombre =datosProducto[2];
                        String descripcion =datosProducto[3];
                        float precio = Float.parseFloat(datosProducto[4]);

                        if (Objects.equals(tipo, "Electronico")) {
                            int garantia = Integer.parseInt(datosProducto[5]);
                            Electronico electronico = new Electronico(codigo,nombre,descripcion,precio,garantia);
                            ListaProducto.add(electronico);


                        }
                        if (Objects.equals(tipo, "Comida")) {
                            String fechaDeVencimiento = datosProducto[5];;
                            float peso = Float.parseFloat(datosProducto[6]);
                            Comida comida = new Comida(codigo,nombre,descripcion,precio,fechaDeVencimiento,peso);
                            ListaProducto.add(comida);
                        }
                    }
                }
                System.out.println("Clientes cargados desde el archivo correctamente.");
            } catch (IOException e) {
                System.err.println("Error al cargar clientes desde el archivo: " + e.getMessage());
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        System.err.println("Error al cerrar BufferedReader: " + e.getMessage());
                    }
                }
            }
            CatalogoProductos CatalogoActual = new CatalogoProductos(ListaProducto);
            this.catalogo = CatalogoActual;
        }



    }

    public Carrito getCarrito() {
        return carrito;
    }
}
