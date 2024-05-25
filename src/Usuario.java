public abstract class Usuario {
    protected String nombreCompleto; // Nombre de usuario de la pagina
    protected String correoEletronico;
    protected String contrasena;

    public abstract void agregraProducto(Producto producto);
    public  abstract  void eleminarProducto(Producto producto);

    protected  boolean verificador(String nombreCompleto , String correoEletronico , String contrasena) {
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
