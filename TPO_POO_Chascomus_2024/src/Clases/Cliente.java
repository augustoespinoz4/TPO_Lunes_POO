package Clases;

public class Cliente extends Usuario {

    public Cliente(String nombreCompleto, String correoElectronico, String contrasena) {
        super(nombreCompleto, correoElectronico, contrasena);
    }

    @Override
    public String getTipo() {
        return "Cliente";
    }

    @Override
    public String toString() {
        return "Cliente;" + nombreCompleto + ";" + correoElectronico + ";" + contrasena + "\n";
    }
}
