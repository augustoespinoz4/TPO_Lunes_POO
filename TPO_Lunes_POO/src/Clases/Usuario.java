package Clases;

public abstract class Usuario {
    protected String nombreCompleto; // Nombre de usuario de la pagina
    protected String correoEletronico;
    protected String contrasena;

    public abstract void AgregarProducto(Producto producto);
    public  abstract  void EliminarProducto(Producto producto);

    protected  boolean Verificador(String nombreCompleto , String correoEletronico , String contrasena) {
        boolean SoyYo=false;
        if (this.nombreCompleto == nombreCompleto && this.correoEletronico == correoEletronico && this.contrasena == contrasena){
            SoyYo=true;
        }
        return SoyYo;
    }

    public Usuario(String nombreCompleto, String correoEletronico, String contrasena) {
        this.nombreCompleto= nombreCompleto;
        this.correoEletronico = correoEletronico;
        this.contrasena = contrasena;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getCorreoEletronico() {
        return correoEletronico;
    }

    public String getContrasena() {
        return contrasena;
    }
}
