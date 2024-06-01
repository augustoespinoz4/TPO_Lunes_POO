package GUI;

import Clases.Usuario;
import Controlador.Controlador;

import javax.swing.*;
import java.awt.*;

public class CarritoView extends JFrame {
    private Controlador controlador;

    public CarritoView(Controlador controlador) {
        this.controlador = controlador;
        setTitle("Inicio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Tamaño inicial
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Color azul definido por su código hexadecimal
        Color azulPersonalizado = new Color(0x004987);

        // Configuración del header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(azulPersonalizado); // Azul personalizado
        headerPanel.setOpaque(true);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        // Imagen del ícono de la aplicación
        ImageIcon iconoApp = new ImageIcon("Imagenes/Iconos/Logo_Chascomus.png"); // Ruta de tu imagen
        Image imagenIconoApp = iconoApp.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Redimensionar imagen
        ImageIcon iconoAppRedimensionado = new ImageIcon(imagenIconoApp);
        JLabel lblIconoApp = new JLabel(iconoAppRedimensionado);
        lblIconoApp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Abrir la vista de inicio nuevamente
                dispose(); // Cerrar la ventana actual
                InicioView inicioView = new InicioView(controlador);
                inicioView.setVisible(true);
            }
        });
        headerPanel.add(lblIconoApp, BorderLayout.WEST); // Alineado a la izquierda

        // Imagen del logo de perfil
        ImageIcon iconoPerfil = new ImageIcon("Imagenes/Iconos/Cliente_User.png"); // Ruta de tu imagen de perfil
        Image imagenIconoPerfil = iconoPerfil.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Redimensionar imagen
        ImageIcon iconoPerfilRedimensionado = new ImageIcon(imagenIconoPerfil);
        JLabel lblIconoPerfil = new JLabel(iconoPerfilRedimensionado);
        JLabel lblNombreCompleto = new JLabel("CARRITO!: ");
        //lblNombreCompleto.setForeground(Color.WHITE); // Texto blanco
        lblNombreCompleto.setHorizontalAlignment(SwingConstants.RIGHT);

        // Panel para contener el nombre del usuario y su foto de perfil
        JPanel usuarioPanel = new JPanel(new BorderLayout());
        usuarioPanel.add(lblNombreCompleto, BorderLayout.CENTER);
        usuarioPanel.add(lblIconoPerfil, BorderLayout.EAST);

        // Botón de herramientas
        JButton btnHerramientas = new JButton(new ImageIcon("Imagenes/Iconos/Settings.png"));
        btnHerramientas.setPreferredSize(new Dimension(25, 25)); // Tamaño del icono
        btnHerramientas.addActionListener(e -> {
            // Mostrar el menú de opciones cuando se hace clic en el botón de herramientas
            JPopupMenu menu = new JPopupMenu();

            // Opciones típicas del menú
            JMenuItem opcion1 = new JMenuItem("Agregar Producto");
            JMenuItem opcion2 = new JMenuItem("Modificar Producto");
            JMenuItem opcion3 = new JMenuItem("Eliminar Producto");

            // Opción para cerrar sesión
            JMenuItem cerrarSesion = new JMenuItem("Cerrar Sesión");
            cerrarSesion.addActionListener(e1 -> {
                // Preguntar al usuario si desea cerrar sesión
                int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea cerrar sesión?",
                        "Cerrar Sesión", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    // Si el usuario confirma, cerrar la sesión y volver a la vista de login
                    dispose(); // Cerrar la ventana actual
                    LoginView loginView = new LoginView(controlador);
                    loginView.setVisible(true);
                }
            });

            // Agregar opciones al menú
            menu.add(opcion1);
            menu.add(opcion2);
            menu.add(opcion3);
            menu.addSeparator(); // Separador entre las opciones típicas y cerrar sesión
            menu.add(cerrarSesion);

            // Mostrar el menú en la posición del botón de herramientas
            menu.show(btnHerramientas, 0, btnHerramientas.getHeight());
        });

        // Panel para contener el botón de herramientas
        JPanel herramientasPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        herramientasPanel.add(btnHerramientas);

        // Añadir componentes al headerPanel
        headerPanel.add(usuarioPanel, BorderLayout.CENTER);
        headerPanel.add(herramientasPanel, BorderLayout.EAST);

        // Añadir el header al frame
        add(headerPanel, BorderLayout.NORTH);

        // Hacer la ventana redimensionable
        setMinimumSize(new Dimension(800, 600)); // Tamaño mínimo para que no se deforme

        // Configuración adicional de la interfaz puede ir aquí...

        // Mostrar la ventana
        setVisible(true);
    }
}
