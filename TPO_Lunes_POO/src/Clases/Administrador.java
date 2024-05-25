package Clases;

public class Administrador extends Usuario{
    public Administrador(String nombreCompleto, String correoElectronico, String contrasena) {
        super(nombreCompleto, correoElectronico, contrasena);
    }

    @Override
    public void AgregarProducto(Producto producto) {

    }

    @Override
    public void EliminarProducto(Producto producto) {

    }

    @Override
    protected boolean Verificador(String nombreCompleto, String correoEletronico, String contrasena) {
        return super.Verificador(nombreCompleto, correoEletronico, contrasena);
    }

    @Override
    public String getNombreCompleto() {
        return super.getNombreCompleto();
    }

    @Override
    public String getCorreoEletronico() {
        return super.getCorreoEletronico();
    }

    @Override
    public String getContrasena() {
        return super.getContrasena();
    }
}
