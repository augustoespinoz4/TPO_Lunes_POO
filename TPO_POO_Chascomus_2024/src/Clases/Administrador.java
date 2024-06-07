package Clases;

public class Administrador extends Usuario {

    public Administrador(String nombreCompleto, String correoElectronico, String contrasena) {
        super(nombreCompleto, correoElectronico, contrasena);
    }

    @Override
    public String getTipo() {
        return "Administrador";
    }

    @Override
    public String toString() {
        return "Administrador;" + nombreCompleto + ";" + correoElectronico + ";" + contrasena + "\n";
    }
}
