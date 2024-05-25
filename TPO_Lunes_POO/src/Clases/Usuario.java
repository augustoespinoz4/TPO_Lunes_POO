package Clases;

public abstract class Usuario {
    protected String nombreCompleto; // Nombre de usuario de la pagina
    protected String correoElectronico;
    protected String contrasena;

    protected  boolean Verificador(String nombreCompleto , String correoElectronico , String contrasena) {
        boolean SoyYo = false;
        if (this.nombreCompleto == nombreCompleto && this.correoElectronico == correoElectronico && this.contrasena == contrasena){
            SoyYo = true;
        }
        return SoyYo;
    }

    public Usuario(String nombreCompleto, String correoEletronico, String contrasena) {
        this.nombreCompleto= nombreCompleto;
        this.correoElectronico = correoEletronico;
        this.contrasena = contrasena;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getCorreoEletronico() {
        return correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }
}
