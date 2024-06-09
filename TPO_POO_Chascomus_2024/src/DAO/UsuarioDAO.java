package DAO;

import Clases.Administrador;
import Clases.Cliente;
import Clases.Usuario;

import java.io.*;
import java.util.ArrayList;

public class UsuarioDAO {

    // Definición de la ruta del archivo donde se guardan los usuarios
    private static final String ARCHIVO_USUARIOS = "Archivos/Usuarios.txt";

    // Método para cargar los usuarios desde el archivo
    public ArrayList<Usuario> cargarUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            // Verificar que exista el archivo.
            File archivo = new File(ARCHIVO_USUARIOS);
            if (!archivo.exists()) {
                // Si el archivo no existe, crearlo.
               archivo.createNewFile();
            }

            try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_USUARIOS))) {
                String linea;
                // Leer cada línea del archivo y crear el objeto Usuario correspondiente
                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split(";");
                    String tipoUsuario = partes[0];
                    String nombreCompleto = partes[1];
                    String correoElectronico = partes[2];
                    String contrasena = partes[3];

                    Usuario usuario;
                    // Verificar el tipo de usuario y crear el objeto correspondiente
                    if (tipoUsuario.equals("Cliente")) {
                        usuario = new Cliente(nombreCompleto, correoElectronico, contrasena);
                    } else {
                        usuario = new Administrador(nombreCompleto, correoElectronico, contrasena);
                    }

                    usuarios.add(usuario);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    // Método para guardar un nuevo usuario en el archivo
    public boolean guardarUsuario(Usuario usuario) {
        boolean exito = false;
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(ARCHIVO_USUARIOS, true));
            // Escribir la representación del usuario en el archivo
            bw.write(usuario.toString());
            exito = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return exito;
    }

}
