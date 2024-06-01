package GUI;
import Controlador.Controlador;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroView extends JFrame {
    private Controlador controlador;
    private JTextField txtNombreCompleto;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JButton btnRegistrar;

    public RegistroView(Controlador controlador) {
        this.controlador = controlador;
        setTitle("Registro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null); // Centrar ventana en la pantalla

        JPanel panel = new JPanel(new GridLayout(5, 2)); // Cambiar a GridLayout(5, 2) para añadir un nuevo botón

        JLabel lblNombreCompleto = new JLabel("Nombre completo:");
        txtNombreCompleto = new JTextField();
        JLabel lblCorreo = new JLabel("Correo electrónico:");
        txtCorreo = new JTextField();
        JLabel lblContrasena = new JLabel("Contraseña:");
        txtContrasena = new JPasswordField();

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setEnabled(false); // Deshabilitar el botón de registro inicialmente
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnVolver = new JButton("Volver al Login"); // Botón para volver al login

        panel.add(lblNombreCompleto);
        panel.add(txtNombreCompleto);
        panel.add(lblCorreo);
        panel.add(txtCorreo);
        panel.add(lblContrasena);
        panel.add(txtContrasena);
        panel.add(btnRegistrar);
        panel.add(btnCancelar);
        panel.add(btnVolver); // Añadir el botón al panel

        add(panel);

        // Añadir DocumentListener a los campos de texto para verificar la validez de los datos ingresados
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                verificarCampos();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                verificarCampos();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                verificarCampos();
            }
        };

        txtNombreCompleto.getDocument().addDocumentListener(documentListener);
        txtCorreo.getDocument().addDocumentListener(documentListener);
        txtContrasena.getDocument().addDocumentListener(documentListener);

        // Acción del botón Registrar
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreCompleto = txtNombreCompleto.getText();
                String correo = txtCorreo.getText();
                String contrasena = new String(txtContrasena.getPassword());

                // Verificar el formato del correo electrónico utilizando una expresión regular
                if (!correo.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                    JOptionPane.showMessageDialog(null, "Formato de correo electrónico inválido.");
                    return; // Salir del método si el formato es inválido
                }

                // Llamar al método registro del controlador de usuarios
                //Controlador controlador = new Controlador();
                boolean registrado = controlador.registro(nombreCompleto, correo, contrasena);

                if (registrado) {
                    JOptionPane.showMessageDialog(null, "Registro exitoso. Ahora puedes iniciar sesión.");
                    GUI.LoginView loginView = new GUI.LoginView(controlador);
                    loginView.setVisible(true);
                    dispose(); // cierra la ventana de registro.
                } else {
                    JOptionPane.showMessageDialog(null, "El correo electrónico ya está en uso.");
                }
            }
        });

        // Acción del botón Cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar la ventana de registro
            }
        });

        // Acción del botón Volver al Login
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.LoginView loginView = new GUI.LoginView(controlador);
                loginView.setVisible(true);
                dispose(); // Cerrar la ventana de registro
            }
        });
    }

    private void verificarCampos() {
        String nombreCompleto = txtNombreCompleto.getText();
        String correo = txtCorreo.getText();
        String contrasena = new String(txtContrasena.getPassword());

        // Verificar que todos los campos estén llenos y el correo tenga un formato válido
        boolean habilitarRegistro = !nombreCompleto.isEmpty() && !correo.isEmpty() &&
                correo.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}") && !contrasena.isEmpty();
        btnRegistrar.setEnabled(habilitarRegistro);
    }

    public static void main(String[] args) {
        // Ejemplo de cómo iniciar la vista de registro
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Controlador controlador1 = new Controlador();
                controlador1.cargarArchivos();
                RegistroView registroView = new RegistroView(controlador1);
                registroView.setVisible(true);
            }
        });
    }
}
