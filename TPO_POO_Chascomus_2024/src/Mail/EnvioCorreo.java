package Mail;

public class EnvioCorreo {

    public static void enviarCorreoConfirmacionCuenta(String destinatario, String nombreUsuario) {
        final String remitente = "equipochascomus@gmail.com";
        //final String contrasena = "itei nght dvvt aljj"; // Usa la contraseña de aplicación generada
        final String contrasena = "iteinghtdvvtaljj"; // Usa la contraseña de aplicación generada

    }

    public static void enviarCorreoConfirmacionCompra(String destinatario, String nombreUsuario) {

    }

    public static void main(String[] args) {
        // Establecer los protocolos TLS
        System.setProperty("https.protocols", "TLSv1.2,TLSv1.3");
        enviarCorreoConfirmacionCuenta("augustoespinoz4@gmail.com", "Augusto Espinoza");
    }
}
