package Controlador;

import Clases.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Controlador {
    private ArrayList<Cliente> clientes;
    private ArrayList<Administrador> administradores;
    private  CatalogoProducto catalogo;

    public Controlador(ArrayList<Cliente> clientes, ArrayList<Administrador> administradores, CatalogoProducto catalogo) {
        this.clientes = clientes;
        this.administradores = administradores;
        this.catalogo = catalogo;
    }
    public boolean realizarLogin(String nombreCompleto , String email , String contrasena){ // de volver true o false para comprobar la existecia del usuario
        int i =0;
        boolean seEncontro=false;
        while (i < administradores.size() && !seEncontro){
            if ( administradores.get(i).getNombreCompleto() == nombreCompleto && administradores.get(i).getCorreoEletronico() == email && administradores.get(i).getContrasena()==contrasena){
                seEncontro=true;
                // invocar el tipo de usuario con mesaje
            }
            else {
                i++;
            }
        }
        int j=0;
        while (j < clientes.size() && !seEncontro){
            if (clientes.get(j).getNombreCompleto() == nombreCompleto && clientes.get(j).getCorreoEletronico() == email && clientes.get(j).getContrasena()==contrasena){
                seEncontro=true;
            }
            else {
                j++;
            }
        }
        return seEncontro;

    }
    public void registrarCliente (String nombre , String email , String contrasena){
        Cliente cliente= new Cliente(nombre,email,contrasena);
        int j=0;
        boolean seEncontro=false;
        while (j < clientes.size() && !seEncontro){
            if ( clientes.get(j).getNombreCompleto() == nombre && clientes.get(j).getCorreoEletronico() == email && clientes.get(j).getContrasena()==contrasena){
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
                
            } catch (IOException e) {
                System.err.println("Error al escribir en el archivo.");
                e.printStackTrace();
            }
        }
    }
}
