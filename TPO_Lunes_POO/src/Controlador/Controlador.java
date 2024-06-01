package Controlador;

import Clases.*;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Controlador {
    private ArrayList<Cliente> clientes;
    private ArrayList<Administrador> administradores;
    private  CatalogoProductos catalogo;

    private Carrito carrito;

    public Controlador(ArrayList<Cliente> clientes, ArrayList<Administrador> administradores, CatalogoProductos catalogo) {
        this.clientes = clientes;
        this.administradores = administradores;
        this.catalogo = catalogo;
    }
    public String realizarLogin(String nombreCompleto , String email , String contrasena){ // de volver true o false para comprobar la existecia del usuario
        int i =0;
        String seEncontro="Ninguno";
        while (i < administradores.size() && seEncontro != "Administrador"){
            if (administradores.get(i).getCorreoEletronico() == email && administradores.get(i).getContrasena()==contrasena){
                seEncontro="Administrador";
                // invocar el tipo de usuario con mesaje
            }
            else {
                i++;
            }
        }
        int j=0;
        while (j < clientes.size() && seEncontro != "Cliente"){
            if (clientes.get(j).getCorreoEletronico() == email && clientes.get(j).getContrasena()==contrasena){
                seEncontro="Cliente";
                this.carrito = new Carrito(clientes.get(j));
            }
            else {
                j++;
            }
        }
        return seEncontro;

    }
    public void registrarCliente (String nombreCompleto , String gmail , String contrasena){
        if (gmail.endsWith("@gmail.com")) {
            Cliente cliente= new Cliente(nombreCompleto,gmail,contrasena);
            int j=0;
            boolean seEncontro=false;
            while (j < clientes.size() && !seEncontro){
                if ( clientes.get(j).getCorreoEletronico() == gmail ){
                    seEncontro=true;
                }
                else {
                    j++;
                }
            }
            if (!seEncontro){
                clientes.add(cliente);
                try {
                    FileWriter fw = new FileWriter("clientes.txt", true);
                    PrintWriter pw = new PrintWriter(fw);

                    pw.println(nombreCompleto + "," + gmail + "," + contrasena);

                    pw.close();
                    fw.close();
                    System.out.println("Cliente registrado exitosamente.");
                    JOptionPane.showMessageDialog(null, "Se registro correctamente!!!", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                } catch (IOException e) {
                    System.err.println("Error al escribir en el archivo.");
                    e.printStackTrace();
                }

            }else{
                JOptionPane.showMessageDialog(null, "Mail ya en uso!!!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }else if (!gmail.endsWith("@gmail.com")){
            JOptionPane.showMessageDialog(null, "Rellene el campo gmail", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void agregarProducto(Producto producto) {
        carrito.agregarItem(producto);
    }
    public void eleminarProducto(Producto producto) {
        carrito.eliminarItem(producto);
    }
    public void borrarCarrito(){
        carrito.borrarCarrito();
    }


}
