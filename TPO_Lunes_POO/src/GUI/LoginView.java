package GUI;

import Controlador.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class LoginView extends JFrame {
    private Controlador controlador;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;

    public LoginView(Controlador controlador) {
        this.controlador = controlador;
        setTitle("Inicio de sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null); // Centrar ventana en la pantalla

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
                String usuario = controlador.realizarLogin(correo,contrasena);

                if (Objects.equals(usuario, "Cliente")) {
                    // Login exitoso, abrir la ventana de inicio
                    GUI.InicioView inicioView = new GUI.InicioView(controlador);
                    dispose(); // Cerrar la ventana de login
                }
                else if (Objects.equals(usuario, "Administrador")) {
                    // Login exitoso, abrir la ventana de inicio



                }

                else {
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas.");
                }
            }
        });

        // Acción del botón Registrarse
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la vista de registro
                GUI.RegistroView registroView = new GUI.RegistroView(controlador);
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
                Controlador controlador1 = new Controlador();
                controlador1.cargarArchivos();
                LoginView loginView = new LoginView(controlador1);
                loginView.setVisible(true);
            }
        });
    }
}
