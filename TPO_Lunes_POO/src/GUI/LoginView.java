package GUI;

import Controlador.Controlador;
import Clases.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;

    public LoginView() {
        setTitle("Inicio de sesión");

        // Obtener el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calcular el tamaño de la ventana de login (por ejemplo, 20% del ancho y 40% de la altura de la pantalla)
        int ancho = (int) (screenSize.width * 0.2);
        int altura = (int) (screenSize.height * 0.4);

        // Establecer el tamaño del frame
        setSize(ancho, altura);

        // Centrar el JFrame en la pantalla
        setLocationRelativeTo(null);

        // Desactivar la capacidad de redimensionamiento
        setResizable(false);

        // Establecer la operación de cierre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear y configurar el panel de login
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel lblCorreo = new JLabel("Correo electrónico:");
        txtCorreo = new JTextField();
        JLabel lblContrasena = new JLabel("Contraseña:");
        txtContrasena = new JPasswordField();

        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        JButton btnRegistrar = new JButton("Registrarse");

        panel.add(lblCorreo);
        panel.add(txtCorreo);
        panel.add(lblContrasena);
        panel.add(txtContrasena);
        panel.add(btnIniciarSesion);
        panel.add(btnRegistrar);

        add(panel);

        // Acción del botón Iniciar Sesión
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = txtCorreo.getText();
                String contrasena = new String(txtContrasena.getPassword());

                // Verificar el formato del correo electrónico utilizando una expresión regular
                if (!correo.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                    JOptionPane.showMessageDialog(null, "Formato de correo electrónico inválido.");
                    return; // Salir del método si el formato es inválido
                }

                // Llamar al método login del controlador de usuarios
                Controlador controlador = new Controlador();
                Usuario usuario = controlador.login(correo, contrasena);

                if (usuario != null) {
                    // Login exitoso, abrir la ventana correspondiente
                    if ("Cliente".equals(usuario.getTipo())) {
                        InicioView clienteView = new InicioView(usuario);
                        clienteView.setVisible(true);
                    } else if ("Administrador".equals(usuario.getTipo())) {
                        AdministradorView administradorView = new AdministradorView(usuario);
                        administradorView.setVisible(true);
                    }
                    dispose(); // Cerrar la ventana de login
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas.");
                }
            }
        });

        // Acción del botón Registrarse
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la vista de registro
                RegistroView registroView = new RegistroView();
                registroView.setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        // Ejemplo de cómo iniciar la vista de inicio de sesión
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginView loginView = new LoginView();
                loginView.setVisible(true);
            }
        });
    }
}
