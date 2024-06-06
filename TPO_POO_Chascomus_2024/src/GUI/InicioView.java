package GUI;

import Clases.*;
import Controlador.Controlador;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class InicioView extends JFrame {

    private final Controlador controlador;
    private final CardLayout cardLayout;
    private final JPanel mainPanel;
    private final JPanel carritoProductosPanel;
    private final JLabel totalCompraLabel;
    private final JLabel cantidadProductosLabel;


    public InicioView(Usuario usuario, Controlador controlador, Carrito carrito) {
        setTitle("Inicio");
        // Establecer el icono de la ventana
        try {
            Image icono = ImageIO.read(new File("TPO_POO_Chascomus_2024/Imagenes/Iconos/Logo_Chascomus.png"));
            setIconImage(icono);
        } catch (IOException e) {
            // Manejar cualquier error de lectura de archivo aquí
            e.printStackTrace();
        }
        this.controlador = controlador;

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

        // Aquí se ajusta el tamaño del headerPanel
        headerPanel.setPreferredSize(new Dimension(this.getWidth(), 125)); // Por ejemplo, 125 píxeles de alto
        headerPanel.setMinimumSize(new Dimension(this.getWidth(), 125));
        headerPanel.setMaximumSize(new Dimension(this.getWidth(), 125));

        // Imagen del ícono de la aplicación
        ImageIcon iconoApp = new ImageIcon("TPO_POO_Chascomus_2024/Imagenes/Iconos/Logo_Chascomus.png"); // Ruta de tu imagen
        Image imagenIconoApp = iconoApp.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Redimensionar imagen
        ImageIcon iconoAppRedimensionado = new ImageIcon(imagenIconoApp);
        JLabel lblIconoApp = new JLabel(iconoAppRedimensionado);
        lblIconoApp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Cambiar a la vista de inicio en lugar de abrir una nueva ventana
                cardLayout.show(mainPanel, "inicio");
            }
        });
        headerPanel.add(lblIconoApp, BorderLayout.WEST); // Alineado a la izquierda

        // Imagen del logo de perfil
        ImageIcon iconoPerfil = new ImageIcon("TPO_POO_Chascomus_2024/Imagenes/Iconos/Cliente_User.png"); // Ruta de tu imagen de perfil
        Image imagenIconoPerfil = iconoPerfil.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Redimensionar imagen
        ImageIcon iconoPerfilRedimensionado = new ImageIcon(imagenIconoPerfil);
        JLabel lblIconoPerfil = new JLabel(iconoPerfilRedimensionado);
        JLabel lblNombreCompleto = new JLabel(usuario.getNombreCompleto());
        lblNombreCompleto.setForeground(Color.white); // cambiar el color del texto.
        lblNombreCompleto.setHorizontalAlignment(SwingConstants.RIGHT);

        // Panel para contener el nombre del usuario y su foto de perfil
        JPanel usuarioPanel = new JPanel(new BorderLayout());
        usuarioPanel.setOpaque(false);
        usuarioPanel.add(lblNombreCompleto, BorderLayout.CENTER);
        usuarioPanel.add(lblIconoPerfil, BorderLayout.EAST);

        // Botón de herramientas (Configuración)
        JLabel lblSettings = new JLabel();
        Image imgSettingsScaled = null;
        try {
            // Cargar la imagen desde el archivo
            Image imgSettings = ImageIO.read(new File("TPO_POO_Chascomus_2024/Imagenes/Iconos/Settings.png"));
            // Escalar la imagen al tamaño deseado
            imgSettingsScaled = imgSettings.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            // Establecer la imagen escalada como icono del JLabel
            lblSettings.setIcon(new ImageIcon(imgSettingsScaled));
        } catch (IOException e) {
            // Manejar cualquier error de lectura de archivo aquí
            e.printStackTrace();
        }
        lblSettings.setPreferredSize(new Dimension(25, 25)); // Tamaño del icono
        // ACCIONES DE LAS OPCIONES
        Image finalImgSettingsScaled = imgSettingsScaled;
        lblSettings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Mostrar el menú de opciones cuando se hace clic en el botón de herramientas
                JPopupMenu menu = new JPopupMenu();

                // Opciones típicas del menú
                JMenuItem opcion1 = new JMenuItem("Ver Electrónicos");
                opcion1.addActionListener(e1 -> {
                    // Cambiar a la vista de electrónicos
                    cardLayout.show(mainPanel, "electronicos");
                });

                JMenuItem opcion2 = new JMenuItem("Ver Comida");
                opcion2.addActionListener(e1 -> {
                    // Cambiar a la vista de comida
                    cardLayout.show(mainPanel, "comida");
                });

                // Opción para cerrar sesión
                JMenuItem cerrarSesion = new JMenuItem("Cerrar Sesión");
                cerrarSesion.addActionListener(e1 -> {
                    // Configurar los botones del JOptionPane en español
                    UIManager.put("OptionPane.yesButtonText", "Sí");
                    UIManager.put("OptionPane.noButtonText", "No");

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
                menu.addSeparator(); // Separador entre las opciones típicas y cerrar sesión
                menu.add(cerrarSesion);

                // Mostrar el menú en la posición del botón de herramientas
                menu.show(lblSettings, 0, lblSettings.getHeight());
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                // Cambiar el tamaño de la imagen al pasar el ratón por encima
                int newWidth = (int) (finalImgSettingsScaled.getWidth(null) * 1.1); // Aumentar el ancho un 10%
                int newHeight = (int) (finalImgSettingsScaled.getHeight(null) * 1.1); // Aumentar la altura un 10%
                Image resizedImage = finalImgSettingsScaled.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                lblSettings.setIcon(new ImageIcon(resizedImage));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restaurar el tamaño original de la imagen al salir el ratón
                lblSettings.setIcon(new ImageIcon(finalImgSettingsScaled));
            }
        });

        // Botón del carrito
        JButton btnCarrito = new JButton();
        btnCarrito.setOpaque(false); // Hace que el fondo del botón sea transparente
        btnCarrito.setContentAreaFilled(false); // Elimina el fondo del botón
        btnCarrito.setBorderPainted(false); // Elimina el borde del botón
        btnCarrito.setFocusable(false);
        try {
            // Cargar la imagen desde el archivo
            Image imgCarrito = ImageIO.read(new File("TPO_POO_Chascomus_2024/Imagenes/Iconos/Shopping_Cart.png"));
            // Escalar la imagen al tamaño deseado
            Image imgCarritoScaled = imgCarrito.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            // Establecer la imagen escalada como icono del JButton
            btnCarrito.setIcon(new ImageIcon(imgCarritoScaled));
        } catch (IOException e) {
            // Manejar cualquier error de lectura de archivo aquí
            e.printStackTrace();
        }

        // Configurar el borde del botón cuando el ratón entra y sale
        btnCarrito.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Cambiar el borde del botón al pasar el ratón por encima
                btnCarrito.setBorderPainted(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restaurar el borde del botón al salir el ratón
                btnCarrito.setBorderPainted(false);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Mostrar la opción "Ver carrito" cuando se hace clic en el botón del carrito
                JPopupMenu menu = new JPopupMenu();
                JMenuItem verCarrito = new JMenuItem("Ver Carrito");
                verCarrito.addActionListener(e1 -> {
                    // Cambiar a la vista del carrito
                    cardLayout.show(mainPanel, "carrito");
                });
                menu.add(verCarrito);
                menu.show(btnCarrito, 0, btnCarrito.getHeight());
            }
        });

        btnCarrito.setPreferredSize(new Dimension(35, 35)); // Tamaño del icono

        // Panel para contener los botones de herramientas
        JPanel herramientasPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        herramientasPanel.setOpaque(false);
        herramientasPanel.add(btnCarrito);
        herramientasPanel.add(lblSettings);

        // Añadir componentes al headerPanel
        headerPanel.add(usuarioPanel, BorderLayout.CENTER);
        headerPanel.add(herramientasPanel, BorderLayout.EAST);

        // Añadir el header al frame
        add(headerPanel, BorderLayout.NORTH);

        // Crear el panel principal con CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        //Imagenes/Iconos/Logo_Chascomus.png
// --------------------------------------------------------------------------------------------------------------------------
// ##############################################  PANELES DEL INICIO  ######################################################
// --------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------- PANEL DE INICIO ----------------------------------------------------------
        JPanel inicioPanel = new JPanel(new GridBagLayout()); // Usamos GridBagLayout para una mejor gestión del diseño

        // GridBagConstraints para configurar la ubicación y el tamaño de los componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Centramos el contenido horizontal y verticalmente
        gbc.insets = new Insets(10, 10, 10, 10); // Añadimos un pequeño espacio entre los componentes

        // Título del grupo centrado
        JLabel tituloGrupoLbl = new JLabel("<html><div style='text-align: center;'><b>Grupo Chascomus 2024</b></div></html>");
        tituloGrupoLbl.setFont(new Font("Arial", Font.BOLD, 30)); // Ajustar el tamaño de la fuente
        inicioPanel.add(tituloGrupoLbl, gbc);

        // Etiqueta "Integrantes:" centrada
        gbc.gridy++; // Incrementamos la fila para colocar el siguiente componente debajo
        JLabel integrantesLbl = new JLabel("<html><div style='text-align: center;'>Integrantes:</div></html>");
        integrantesLbl.setFont(new Font("Arial", Font.PLAIN, 20)); // Ajustar el tamaño de la fuente
        inicioPanel.add(integrantesLbl, gbc);

        // Lista de integrantes centrada
        String[] integrantes = {
                "Alfonsín, Nicolás. LU: 1170060",
                "Espinoza, Augusto. LU: 1174378",
                "García, Ramiro. LU: 1173464",
                "Orlando, Alan Germán. LU: 1147619",
                "Gillone, Marco Ariel. LU: 1165715"
        };

        for (String integrante : integrantes) {
            gbc.gridy++; // Incrementamos la fila para colocar el siguiente integrante debajo
            JLabel integranteLbl = new JLabel("<html><div style='text-align: center;'>" + integrante + "</div></html>");
            integranteLbl.setFont(new Font("Arial", Font.PLAIN, 14)); // Ajustar el tamaño de la fuente
            inicioPanel.add(integranteLbl, gbc);
        }

        // Añadir el panel de inicio al frame
        add(inicioPanel, BorderLayout.CENTER);


// ----------------------------------------------- PANEL DE ELECTRÓNICOS ----------------------------------------------------
        JPanel electronicosPanel = new JPanel(new BorderLayout());
        // Titulo
        JLabel lblTituloElectronicos = new JLabel("<html><b>Electrónicos</b></html>"); // Etiqueta en negrita
        lblTituloElectronicos.setFont(lblTituloElectronicos.getFont().deriveFont(Font.BOLD, 20)); // Establecer el tamaño de la fuente
        lblTituloElectronicos.setHorizontalAlignment(SwingConstants.CENTER); // Alinear el texto al centro
        electronicosPanel.add(lblTituloElectronicos, BorderLayout.NORTH);

        JPanel productoEPanel = new JPanel(); // Inicializa productoPanel
        productoEPanel.setLayout(new BoxLayout(productoEPanel, BoxLayout.Y_AXIS));

        // Catalogo
        cargarProductos("Electronico", productoEPanel);

        JScrollPane scrollPaneE = new JScrollPane(productoEPanel);
        scrollPaneE.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Añadir barra de desplazamiento vertical siempre visible
        scrollPaneE.getVerticalScrollBar().setUnitIncrement(20); // Ajustar la velocidad del scroll
        electronicosPanel.add(scrollPaneE, BorderLayout.CENTER);

// ----------------------------------------------- PANEL DE COMIDA ----------------------------------------------------------
        JPanel comidaPanel = new JPanel(new BorderLayout());
        // Titulo
        JLabel lblTituloComida = new JLabel("<html><b>Comida</b></html>"); // Etiqueta en negrita
        lblTituloComida.setFont(lblTituloComida.getFont().deriveFont(Font.BOLD, 20)); // Establecer el tamaño de la fuente
        lblTituloComida.setHorizontalAlignment(SwingConstants.CENTER); // Alinear el texto al centro
        comidaPanel.add(lblTituloComida, BorderLayout.NORTH);

        JPanel productoCPanel = new JPanel(); // Inicializa productoPanel
        productoCPanel.setLayout(new BoxLayout(productoCPanel, BoxLayout.Y_AXIS));

        // Catalogo
        cargarProductos("Comida", productoCPanel);

        JScrollPane scrollPaneC = new JScrollPane(productoCPanel);
        scrollPaneC.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Añadir barra de desplazamiento vertical siempre visible
        scrollPaneC.getVerticalScrollBar().setUnitIncrement(20); // Ajustar la velocidad del scroll
        comidaPanel.add(scrollPaneC, BorderLayout.CENTER);

// ----------------------------------------------- PANEL DEL CARRITO --------------------------------------------------------
        JPanel carritoPanel = new JPanel(new BorderLayout());
        // Titulo
        JLabel lblCarrito = new JLabel("<html><b>Mi Carrito</b></html>"); // Etiqueta en negrita
        lblCarrito.setFont(lblCarrito.getFont().deriveFont(Font.BOLD, 20)); // Establecer el tamaño de la fuente
        lblCarrito.setHorizontalAlignment(SwingConstants.CENTER); // Alinear el texto al centro
        carritoPanel.add(lblCarrito, BorderLayout.NORTH);

        carritoProductosPanel = new JPanel();
        carritoProductosPanel.setLayout(new BoxLayout(carritoProductosPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPaneCarrito = new JScrollPane(carritoProductosPanel);
        scrollPaneCarrito.getVerticalScrollBar().setUnitIncrement(20); // Ajustar la velocidad del scroll

        JPanel panelTotalYBotones = new JPanel();
        panelTotalYBotones.setLayout(new BoxLayout(panelTotalYBotones, BoxLayout.Y_AXIS));

        totalCompraLabel = new JLabel("Total: $0.00");
        cantidadProductosLabel = new JLabel("Productos en el carrito: 0");

// --------------------------------------------------- BOTONES DEL CARRITO -------------------------------------------------------

        JButton btnFinalizarCompra = new JButton("Finalizar Compra");
        btnFinalizarCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el total y la cantidad de productos
                double total = controlador.getCarrito().getPrecioTotal();
                int cantidadProductos = controlador.getCarrito().getCantidad();

                // Formatear el mensaje de confirmación incluyendo el total y la cantidad de productos
                String mensaje = "¿Está seguro de finalizar la compra?\n" +
                        "Total: $" + total + "\n" +
                        "Cantidad de productos: " + cantidadProductos;

                // Configurar los botones del JOptionPane en español
                UIManager.put("OptionPane.yesButtonText", "Sí");
                UIManager.put("OptionPane.noButtonText", "No");

                // Mostrar un cuadro de diálogo de confirmación con el mensaje formateado
                int confirmacion = JOptionPane.showConfirmDialog(null, mensaje, "Finalizar Compra", JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    // Lógica para finalizar la compra
                    JOptionPane.showMessageDialog(null, "Compra finalizada. Total: $" + total);
                    controlador.limpiarCarrito();
                    actualizarVistaCarrito(carritoProductosPanel, totalCompraLabel, cantidadProductosLabel);
                }
            }
        });

        JButton btnLimpiarCarrito = new JButton("Limpiar Carrito");
        btnLimpiarCarrito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Configurar los botones del JOptionPane en español
                UIManager.put("OptionPane.yesButtonText", "Sí");
                UIManager.put("OptionPane.noButtonText", "No");

                // Mostrar un cuadro de diálogo de confirmación antes de limpiar el carrito
                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de limpiar el carrito?", "Limpiar Carrito", JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    // Lógica para limpiar el carrito
                    controlador.limpiarCarrito();
                    actualizarVistaCarrito(carritoProductosPanel, totalCompraLabel, cantidadProductosLabel);
                }
            }
        });

        panelTotalYBotones.add(totalCompraLabel);
        panelTotalYBotones.add(cantidadProductosLabel);
        panelTotalYBotones.add(btnFinalizarCompra);
        panelTotalYBotones.add(btnLimpiarCarrito);

        carritoPanel.add(scrollPaneCarrito, BorderLayout.CENTER);
        carritoPanel.add(panelTotalYBotones, BorderLayout.SOUTH);

// --------------------------------------------------------------------------------------------------------------------------
// ##############################################    FIN DE PANELES    ######################################################
// --------------------------------------------------------------------------------------------------------------------------

        // Añadir los paneles al mainPanel con un identificador
        mainPanel.add(inicioPanel, "inicio");
        mainPanel.add(electronicosPanel, "electronicos");
        mainPanel.add(comidaPanel, "comida");
        mainPanel.add(carritoPanel, "carrito");

        // Añadir el mainPanel al frame
        add(mainPanel, BorderLayout.CENTER);

        // Hacer la ventana redimensionable
        setMinimumSize(new Dimension(900, 600)); // Tamaño mínimo para que no se deforme

        // Mostrar la ventana
        setVisible(true);
    }

    // Modifica cargarProductos para recibir el tipo de producto como parámetro
    private void cargarProductos(String tipoProducto, JPanel panel) {
        panel.removeAll();
        ArrayList<Producto> productos = controlador.obtenerProductos();

        if (productos.isEmpty()) {
            JLabel noProductsLabel = new JLabel("No hay productos para ver");
            noProductsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(noProductsLabel);
        } else {
            for (Producto producto : productos) {
                // Verifica si el tipo del producto coincide con el tipo especificado
                if (producto.getTipo().equals(tipoProducto)) {
                    JPanel productBox = crearCajaProducto(producto);
                    panel.add(productBox);
                }
            }
        }

        panel.revalidate();
        panel.repaint();
    }

    private JPanel crearCajaProducto(Producto producto) {
        JPanel cajaProducto = new JPanel(new GridLayout(1, 1)); // GridLayout con 1 fila y 1 columna

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY), // Borde arriba y abajo
                BorderFactory.createEmptyBorder(10, 10, 10, 10) // Margen interno
        ));

        JLabel nombreLbl = new JLabel("Nombre: " + producto.getNombre());
        nombreLbl.setFont(new Font("Arial", Font.BOLD, 18));
        infoPanel.add(nombreLbl);

        JLabel DescripcionLbl = new JLabel("Descripción: " + producto.getDescripcion());
        DescripcionLbl.setFont(new Font("Arial", Font.PLAIN, 14));
        infoPanel.add(DescripcionLbl);

        JLabel precioLbl = new JLabel("Precio: " + producto.getPrecio());
        precioLbl.setFont(new Font("Arial", Font.PLAIN, 14));
        infoPanel.add(precioLbl);

        if (producto.getTipo().equals("Electronico")) {
            Electronico electronico = (Electronico) producto;
            infoPanel.add(new JLabel("Garantía: " + electronico.getGarantia() + " meses"));
        } else if (producto.getTipo().equals("Comida")) {
            Comida comida = (Comida) producto;
            infoPanel.add(new JLabel("Fecha de Vencimiento: " + comida.getFechaVencimiento()));
            infoPanel.add(new JLabel("Peso: " + comida.getPeso() + " g"));
        }

        // Botón "Agregar al carrito"
        JButton btnAgregarCarrito = new JButton("Agregar al carrito");
        btnAgregarCarrito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.agregarProdCarrito(producto);
                actualizarVistaCarrito(carritoProductosPanel, totalCompraLabel, cantidadProductosLabel);
            }
        });
        infoPanel.add(btnAgregarCarrito); // Agregar el botón al panel de información

        cajaProducto.add(infoPanel); // Añadir el panel de información a la primera columna

        return cajaProducto;
    }

    private void mostrarProductosCarrito(JPanel carritoProductosPanel, JLabel totalCompraLabel, JLabel cantidadProductosLabel) {
        carritoProductosPanel.removeAll(); // Limpiamos el panel antes de añadir los productos del carrito
        carritoProductosPanel.setLayout(new BoxLayout(carritoProductosPanel, BoxLayout.Y_AXIS));

        ArrayList<Producto> productosEnCarrito = controlador.obtenerProdCarrito();
        for (Producto producto : productosEnCarrito) {
            JPanel productoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            productoPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            productoPanel.setBackground(Color.WHITE);

            JLabel lblNombre = new JLabel(producto.getNombre());
            lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
            lblNombre.setPreferredSize(new Dimension(200, 30));

            JLabel lblPrecio = new JLabel("$" + producto.getPrecio());
            lblPrecio.setFont(new Font("Arial", Font.PLAIN, 12));
            lblPrecio.setPreferredSize(new Dimension(100, 30));

            JButton btnEliminar = new JButton("Eliminar");
            btnEliminar.addActionListener(e -> {
                controlador.eliminarProdCarrito(producto);
                actualizarVistaCarrito(carritoProductosPanel, totalCompraLabel, cantidadProductosLabel);
            });

            productoPanel.add(lblNombre);
            productoPanel.add(lblPrecio);
            productoPanel.add(btnEliminar);

            carritoProductosPanel.add(productoPanel);
        }

        carritoProductosPanel.revalidate();
        carritoProductosPanel.repaint();
    }

    private void actualizarVistaCarrito(JPanel carritoProductosPanel, JLabel totalCompraLabel, JLabel cantidadProductosLabel) {
        mostrarProductosCarrito(carritoProductosPanel, totalCompraLabel, cantidadProductosLabel);
        totalCompraLabel.setText("Total de la compra: $" + controlador.getCarrito().getPrecioTotal());
        cantidadProductosLabel.setText("Cantidad de productos: " + controlador.getCarrito().getCantidad());
    }

}
