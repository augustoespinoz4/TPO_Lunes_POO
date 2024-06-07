package Mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EnvioCorreo {

    public static boolean enviarCorreo(String destinatario, String nombreUsuario, String tipoMensaje) {
        final String remitente = "equipochascomus@gmail.com";
        final String claveAplicacion = "itei nght dvvt aljj"; // Reemplaza esto con la clave de aplicación generada
        boolean resultado = false;

        // Configuración de las propiedades
        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.port", "587");
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Crear una sesión de correo
        Session session = Session.getInstance(propiedades, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, claveAplicacion);
            }
        });

        try {
            // Crear un mensaje MIME
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));

            // Asignar el asunto y cuerpo del mensaje basado en el tipo de mensaje
            String asunto = "";
            String cuerpoMensaje = "";

            if (tipoMensaje.equals("Confirmacion cuenta")) {
                asunto = "Confirmación de cuenta";
                cuerpoMensaje = "<h1 style=\"font-size:24px;\">Hola " + nombreUsuario + ",</h1>"
                        + "<p style=\"font-size:16px;\">Tu cuenta ha sido confirmada correctamente.</p>";
            } else if (tipoMensaje.equals("Confirmacion compra")) {
                asunto = "Confirmación de compra";
                cuerpoMensaje = "<h1 style=\"font-size:24px;\">Hola " + nombreUsuario + ",</h1>"
                        + "<p style=\"font-size:16px;\">Tu compra ha sido confirmada. ¡Gracias por tu compra!</p>";
            } else {
                asunto = "Mensaje sin especificar";
                cuerpoMensaje = "<p style=\"font-size:16px;\">Mensaje sin especificar.</p>";
            }

            mensaje.setSubject(asunto);
            mensaje.setContent(cuerpoMensaje, "text/html; charset=utf-8");

            // Enviar el mensaje
            Transport.send(mensaje);

            System.out.println("Correo enviado exitosamente.");
            resultado = true;

        } catch (MessagingException e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
        return resultado;
    }
}
