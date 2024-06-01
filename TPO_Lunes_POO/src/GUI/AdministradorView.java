package GUI;

import Clases.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AdministradorView extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public AdministradorView(Usuario usuario) {
        setTitle("Administrador");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) (screenSize.width * 0.7), (int) (screenSize.height * 0.8));
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        headerPanel.add(lblIconoApp, BorderLayout.WEST); // Alineado a la izquierda

        // Imagen del logo de perfil
        ImageIcon iconoPerfil = new ImageIcon("Imagenes/Iconos/Admin_User.png"); // Ruta de tu imagen de perfil
        Image imagenIconoPerfil = iconoPerfil.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Redimensionar imagen
        ImageIcon iconoPerfilRedimensionado = new ImageIcon(imagenIconoPerfil);
        JLabel lblIconoPerfil = new JLabel(iconoPerfilRedimensionado);
        JLabel lblNombreCompleto = new JLabel("Admin: " + usuario.getNombreCompleto());
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
            opcion1.addActionListener(e1 -> {
                // Cambiar a la vista de agregar producto
                cardLayout.show(mainPanel, "agregarProducto");
            });

            JMenuItem opcion2 = new JMenuItem("Modificar Producto");
            opcion2.addActionListener(e1 -> {
                // Cambiar a la vista de modificar producto
                cardLayout.show(mainPanel, "modificarProducto");
            });

            JMenuItem opcion3 = new JMenuItem("Eliminar Producto");
            opcion3.addActionListener(e1 -> {
                // Cambiar a la vista de eliminar producto
                cardLayout.show(mainPanel, "eliminarProducto");
            });

            // Opción para cerrar sesión
            JMenuItem cerrarSesion = new JMenuItem("Cerrar Sesión");
            cerrarSesion.addActionListener(e1 -> {
                // Preguntar al usuario si desea cerrar sesión
                int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea cerrar sesión?",
                        "Cerrar Sesión", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    // Si el usuario confirma, cerrar la sesión y volver a la vista de login
                    dispose(); // Cerrar la ventana actual
                    LoginView loginView = new LoginView();
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

        // Crear el panel principal con CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Crear los diferentes paneles de contenido
        JPanel inicioPanel = new JPanel();
        inicioPanel.add(new JLabel("Bienvenido a la vista de inicio"));

        JPanel agregarProductoPanel = new JPanel();
        agregarProductoPanel.add(new JLabel("Aquí puedes agregar productos"));

        JPanel modificarProductoPanel = new JPanel();
        modificarProductoPanel.add(new JLabel("Aquí puedes modificar productos"));

        JPanel eliminarProductoPanel = new JPanel();
        eliminarProductoPanel.add(new JLabel("Aquí puedes eliminar productos"));

        // Añadir los paneles al mainPanel con un identificador
        mainPanel.add(inicioPanel, "inicio");
        mainPanel.add(agregarProductoPanel, "agregarProducto");
        mainPanel.add(modificarProductoPanel, "modificarProducto");
        mainPanel.add(eliminarProductoPanel, "eliminarProducto");

        // Añadir el mainPanel al frame
        add(mainPanel, BorderLayout.CENTER);

        // Hacer la ventana redimensionable
        setMinimumSize(new Dimension(800, 600)); // Tamaño mínimo para que no se deforme

        // Mostrar la ventana
        setVisible(true);
    }
}
