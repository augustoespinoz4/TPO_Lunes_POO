import java.util.ArrayList;

public class Controlador {
    private ArrayList<Cliente> clientes;
    private ArrayList<Admistrador> admistradores;
    private  CatalogoProducto catalogo;

    public Controlador(ArrayList<Cliente> clientes, ArrayList<Admistrador> admistradores, CatalogoProducto catalogo) {
        this.clientes = clientes;
        this.admistradores = admistradores;
        this.catalogo = catalogo;
    }
    public boolean realizarLogin(String nombreCompleto , String email , String contrasena){ // de volver true o false para comprobar la existecia del usuario
        int i =0;
        boolean seEncontro=false;
        while (i < admistradores.size() && !seEncontro){
            if ( admistradores.get(i).getNombreCompleto() == nombreCompleto && admistradores.get(i).getCorreoEletronico() == email && admistradores.get(i).getContrasena()==contrasena){
                seEncontro=true;
                // invocar el tipo de usuario con mesaje
            }
            else {
                i++;
            }
        }
        int j=0;
        while (j < clientes.size() && !seEncontro){
            if ( clientes.get(j).getNombreCompleto() == nombreCompleto && clientes.get(j).getCorreoEletronico() == email && clientes.get(j).getContrasena()==contrasena){
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
        if (seEncontro){
            clientes.add(cliente);
        }
    }
}
