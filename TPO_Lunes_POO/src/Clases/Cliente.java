package Clases;

public final class Cliente extends Usuario {

    public Cliente(String nombreCompleto, String correoEletronico, String contrasena) {
        super(nombreCompleto, correoEletronico, contrasena);
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
