package GUI;

import Clases.Comida;
import Clases.Electronico;
import Clases.Producto;
import Clases.Usuario;
import Controlador.Controlador;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AdministradorView extends JFrame {

    private final Controlador controlador; // Referencia al controlador de la aplicación
    private final JPanel productoPanel;

    public AdministradorView(Usuario usuario, Controlador controlador) {
        setTitle("Inicio Administrador"); // Establecer el título de la ventana como "Inicio Administrador"
        // Establecer el ícono de la ventana
        try {
            Image icono = ImageIO.read(new File("Imagenes/Iconos/Logo_Chascomus.png"));
            setIconImage(icono);
        } catch (IOException e) {
            // Manejar cualquier error de lectura de archivo aquí
            e.printStackTrace();
        }
        this.controlador = controlador; // Asignar el controlador recibido como parámetro a la variable local

        // Configurar el tamaño de la ventana como el 70% del ancho y el 80% del alto de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) (screenSize.width * 0.7), (int) (screenSize.height * 0.8));

        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Establecer la operación predeterminada al cerrar la ventana como salir de la aplicación

        // Color azul personalizado definido por su código hexadecimal
        Color azulPersonalizado = new Color(0x004987);

        // Configuración del headerPanel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(azulPersonalizado); // Establecer el color de fondo del headerPanel como el azul personalizado
        headerPanel.setOpaque(true);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Establecer un borde vacío con relleno de 10 píxeles en cada lado

        // Aquí se ajusta el tamaño del headerPanel
        headerPanel.setPreferredSize(new Dimension(this.getWidth(), 125)); // Por ejemplo, 125 píxeles de alto
        headerPanel.setMinimumSize(new Dimension(this.getWidth(), 125));
        headerPanel.setMaximumSize(new Dimension(this.getWidth(), 125));

        // Imagen del ícono de la aplicación
        ImageIcon iconoApp = new ImageIcon("Imagenes/Iconos/Logo_Chascomus.png"); // Crear un ImageIcon con la ruta de la imagen del ícono de la aplicación
        Image imagenIconoApp = iconoApp.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Redimensionar la imagen del ícono de la aplicación
        ImageIcon iconoAppRedimensionado = new ImageIcon(imagenIconoApp);
        JLabel lblIconoApp = new JLabel(iconoAppRedimensionado); // Crear un JLabel con el ícono de la aplicación redimensionado
        headerPanel.add(lblIconoApp, BorderLayout.WEST); // Alinear el JLabel del ícono de la aplicación a la izquierda del headerPanel

        // Imagen del logo de perfil
        ImageIcon iconoPerfil = new ImageIcon("Imagenes/Iconos/Admin_User.png"); // Crear un ImageIcon con la ruta de la imagen del logo de perfil
        Image imagenIconoPerfil = iconoPerfil.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Redimensionar la imagen del logo de perfil
        ImageIcon iconoPerfilRedimensionado = new ImageIcon(imagenIconoPerfil);
        JLabel lblIconoPerfil = new JLabel(iconoPerfilRedimensionado); // Crear un JLabel con el logo de perfil redimensionado
        lblIconoPerfil.setOpaque(false); // Establecer el fondo del JLabel del logo de perfil como transparente
        JLabel lblNombreCompleto = new JLabel("Admin: " + usuario.getNombreCompleto()); // Crear un JLabel con el nombre completo del usuario administrador
        lblNombreCompleto.setForeground(Color.white); // Establecer el color del texto del JLabel como blanco
        lblNombreCompleto.setHorizontalAlignment(SwingConstants.RIGHT); // Alinear el texto del JLabel a la derecha

        // Panel para contener el nombre del usuario y su foto de perfil
        JPanel usuarioPanel = new JPanel(new BorderLayout());
        usuarioPanel.setOpaque(false); // Hacer que el panel que contiene al usuario y el icono no tenga color de fondo
        usuarioPanel.add(lblNombreCompleto, BorderLayout.CENTER); // Agregar el JLabel del nombre completo del usuario al centro del usuarioPanel
        usuarioPanel.add(lblIconoPerfil, BorderLayout.EAST); // Alinear el JLabel del logo de perfil a la derecha del usuarioPanel

        // Botón de herramientas (Configuración)
        JLabel lblSettings = new JLabel();
        Image imgSettingsScaled = null;
        try {
            // Cargar la imagen desde el archivo
            Image imgSettings = ImageIO.read(new File("Imagenes/Iconos/Admin_Settings.png"));
            // Escalar la imagen al tamaño deseado
            imgSettingsScaled = imgSettings.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            // Establecer la imagen escalada como icono del JLabel
            lblSettings.setIcon(new ImageIcon(imgSettingsScaled));
        } catch (IOException e) {
            // Manejar cualquier error de lectura de archivo aquí
            e.printStackTrace();
        }
        lblSettings.setPreferredSize(new Dimension(25, 25)); // Tamaño del icono

        Image finalImgSettingsScaled = imgSettingsScaled;
        lblSettings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Mostrar el menú de opciones cuando se hace clic en el botón de herramientas
                JPopupMenu menu = new JPopupMenu();

                // Opciones del menú
                // ----------------------------------------- AGREGAR PRODUCTO ----------------------------------------------
                JMenuItem opcion1 = new JMenuItem("Agregar Producto");
                opcion1.addActionListener(e1 -> {
                    // Crear un diálogo para seleccionar el tipo de producto
                    String[] tiposProductos = {"Electrónico", "Comida"};
                    String tipoSeleccionado = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de producto:",
                            "Seleccionar Tipo de Producto", JOptionPane.PLAIN_MESSAGE, null, tiposProductos, tiposProductos[0]);

                    if (tipoSeleccionado != null) {
                        // Dependiendo del tipo seleccionado, mostrar el formulario correspondiente
                        if (tipoSeleccionado.equals("Electrónico")) {
                            // Mostrar formulario para agregar producto electrónico
                            mostrarFormularioAgregarElectronico();
                        } else if (tipoSeleccionado.equals("Comida")) {
                            // Mostrar formulario para agregar producto de comida
                            mostrarFormularioAgregarComida();
                        }
                    }
                    cargarProductos();
                });

                // ----------------------------------------- MODIFICAR PRODUCTO ----------------------------------------------
                JMenuItem opcion2 = new JMenuItem("Modificar Producto");
                opcion2.addActionListener(e1 -> {
                    String codigoStr = JOptionPane.showInputDialog(null, "Ingrese el código del producto a modificar:");
                    if (codigoStr != null && !codigoStr.isEmpty()) {
                        try {
                            int codigo = Integer.parseInt(codigoStr);
                            Producto producto = controlador.obtenerProductoPorCodigo(codigo);
                            if (producto != null) {
                                // Mostrar formulario para modificar producto
                                mostrarFormularioModificarProducto(producto);
                            } else {
                                JOptionPane.showMessageDialog(null, "No se encontró ningún producto con el código proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Por favor, ingrese un código válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    cargarProductos();
                });

                // ----------------------------------------- ELIMINAR PRODUCTO ----------------------------------------------
                JMenuItem opcion3 = new JMenuItem("Eliminar Producto");
                opcion3.addActionListener(e1 -> {
                    String codigoStr = JOptionPane.showInputDialog(null, "Ingrese el código del producto a eliminar:");
                    if (codigoStr != null && !codigoStr.isEmpty()) {
                        try {
                            int codigo = Integer.parseInt(codigoStr);
                            Producto producto = controlador.obtenerProductoPorCodigo(codigo);
                            if (producto != null) {
                                // Mostrar formulario para eliminar producto
                                mostrarFormularioEliminarProducto(producto);
                            } else {
                                JOptionPane.showMessageDialog(null, "No se encontró ningún producto con el código proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Por favor, ingrese un código válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    cargarProductos();
                });

                // ------------------------------------------ CERRAR SESIÓN ---------------------------------------------------
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
                menu.add(opcion3);
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

        // Panel para contener el botón de herramientas
        JPanel herramientasPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        herramientasPanel.setOpaque(false);
        herramientasPanel.add(lblSettings);

        // Añadir componentes al headerPanel
        headerPanel.add(usuarioPanel, BorderLayout.CENTER);
        headerPanel.add(herramientasPanel, BorderLayout.EAST);

        // Añadir el header al frame
        add(headerPanel, BorderLayout.NORTH);

        // Crear el panel principal sin CardLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

// --------------------------------------------------------------------------------------------------------------------------
// ##############################################   PANEL DEL INICIO   ######################################################
// --------------------------------------------------------------------------------------------------------------------------
        JPanel inicioPanel = new JPanel(new BorderLayout());
        JLabel lblTituloInicio = new JLabel("<html><b>Catálogo de productos</b></html>"); // Etiqueta en negrita
        lblTituloInicio.setFont(lblTituloInicio.getFont().deriveFont(Font.BOLD, 20)); // Establecer el tamaño de la fuente
        lblTituloInicio.setHorizontalAlignment(SwingConstants.CENTER); // Alinear el texto al centro
        inicioPanel.add(lblTituloInicio, BorderLayout.NORTH);
        productoPanel = new JPanel(); // Inicializa productoPanel
        productoPanel.setLayout(new BoxLayout(productoPanel, BoxLayout.Y_AXIS));
        cargarProductos();
        JScrollPane scrollPane = new JScrollPane(productoPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Añadir barra de desplazamiento vertical siempre visible
        scrollPane.getVerticalScrollBar().setUnitIncrement(20); // Ajustar la velocidad del scroll
        inicioPanel.add(scrollPane, BorderLayout.CENTER);

        // Añadir el panel de inicio al mainPanel
        mainPanel.add(inicioPanel, BorderLayout.CENTER);

        // Añadir el mainPanel al frame
        add(mainPanel, BorderLayout.CENTER);

        // Hacer la ventana redimensionable
        setMinimumSize(new Dimension(800, 600)); // Tamaño mínimo para que no se deforme

        // Mostrar la ventana
        setVisible(true);
    }





    // -------------------------------------------------------------------------------------------------------------------
    // -------------------------------------------------- FORMULARIOS ----------------------------------------------------
    // -------------------------------------------     (METODOS PRIVADOS)     --------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------

    private void mostrarFormularioAgregarElectronico() {
        boolean datosValidos = false;

        do {
            JPanel formularioElectronico = new JPanel(new GridLayout(5, 2));

            JTextField txtNombre = new JTextField();
            JTextField txtDescripcion = new JTextField();
            JTextField txtPrecio = new JTextField();
            JTextField txtGarantia = new JTextField();

            formularioElectronico.add(new JLabel("Nombre:"));
            formularioElectronico.add(txtNombre);
            formularioElectronico.add(new JLabel("Descripción:"));
            formularioElectronico.add(txtDescripcion);
            formularioElectronico.add(new JLabel("Precio:"));
            formularioElectronico.add(txtPrecio);
            formularioElectronico.add(new JLabel("Garantía:"));
            formularioElectronico.add(txtGarantia);

            int resultado = JOptionPane.showConfirmDialog(null, formularioElectronico,
                    "Agregar Producto Electrónico", JOptionPane.OK_CANCEL_OPTION);
            if (resultado == JOptionPane.OK_OPTION) {
                String nombre = txtNombre.getText().trim();
                String descripcion = txtDescripcion.getText().trim();
                String precioStr = txtPrecio.getText().trim();
                String garantiaStr = txtGarantia.getText().trim();

                if (nombre.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty() || garantiaStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben ser rellenados.", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                float precio;
                int garantia;
                try {
                    precio = Float.parseFloat(precioStr);
                    garantia = Integer.parseInt(garantiaStr);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Precio y Garantía deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                datosValidos = true;

                // Usar el controlador para generar un código único
                int codigo = controlador.generarCodigoProductoUnico();

                // Crear el objeto Electronico con los valores obtenidos y el código generado
                Producto electronico = new Electronico(codigo, nombre, descripcion, precio, garantia);

                // Mostrar popup de confirmación
                int confirmacion = JOptionPane.showConfirmDialog(null,
                        "Dar alta del siguiente producto:\n\n" +
                                "Nombre: " + nombre + "\n" +
                                "Descripción: " + descripcion + "\n" +
                                "Precio: " + precio + "\n" +
                                "Garantía: " + garantia,
                        "Confirmar Alta de Producto", JOptionPane.OK_CANCEL_OPTION);

                if (confirmacion == JOptionPane.OK_OPTION) {
                    // Agregar al archivo y a la lista con el controlador
                    controlador.darAltaProducto(electronico);
                    JOptionPane.showMessageDialog(null, "¡Producto agregado al catálogo!");
                }
            } else {
                datosValidos = true; // Salir del bucle si el usuario cancela el formulario
            }
        } while (!datosValidos);
    }

    private void mostrarFormularioAgregarComida() {
        boolean datosValidos = false;

        do {
            JPanel formularioComida = new JPanel(new GridLayout(6, 2));

            JTextField txtNombre = new JTextField();
            JTextField txtDescripcion = new JTextField();
            JTextField txtPrecio = new JTextField();
            JTextField txtFechaVencimiento = new JTextField();
            JTextField txtPeso = new JTextField();

            formularioComida.add(new JLabel("Nombre:"));
            formularioComida.add(txtNombre);
            formularioComida.add(new JLabel("Descripción:"));
            formularioComida.add(txtDescripcion);
            formularioComida.add(new JLabel("Precio:"));
            formularioComida.add(txtPrecio);
            formularioComida.add(new JLabel("Fecha de Vencimiento:"));
            formularioComida.add(txtFechaVencimiento);
            formularioComida.add(new JLabel("Peso:"));
            formularioComida.add(txtPeso);

            int resultado = JOptionPane.showConfirmDialog(null, formularioComida,
                    "Agregar Producto de Comida", JOptionPane.OK_CANCEL_OPTION);
            if (resultado == JOptionPane.OK_OPTION) {
                String nombre = txtNombre.getText().trim();
                String descripcion = txtDescripcion.getText().trim();
                String precioStr = txtPrecio.getText().trim();
                String fechaVencimiento = txtFechaVencimiento.getText().trim();
                String pesoStr = txtPeso.getText().trim();

                if (nombre.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty() || fechaVencimiento.isEmpty() || pesoStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben ser rellenados.", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                float precio;
                float peso;
                try {
                    precio = Float.parseFloat(precioStr);
                    peso = Float.parseFloat(pesoStr);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Precio y Peso deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                datosValidos = true;

                // Usar el controlador para generar un código único
                int codigo = controlador.generarCodigoProductoUnico();

                // Crear el objeto Comida con los valores obtenidos y el código generado
                Producto comida = new Comida(codigo, nombre, descripcion, precio, fechaVencimiento, peso);

                // Mostrar popup de confirmación
                int confirmacion = JOptionPane.showConfirmDialog(null,
                        "Dar alta del siguiente producto:\n\n" +
                                "Nombre: " + nombre + "\n" +
                                "Descripción: " + descripcion + "\n" +
                                "Precio: " + precio + "\n" +
                                "Fecha de Vencimiento: " + fechaVencimiento + "\n" +
                                "Peso: " + peso,
                        "Confirmar Alta de Producto", JOptionPane.OK_CANCEL_OPTION);

                if (confirmacion == JOptionPane.OK_OPTION) {
                    // Agregar al archivo y a la lista con el controlador
                    controlador.darAltaProducto(comida);
                    JOptionPane.showMessageDialog(null, "¡Producto agregado al catálogo!");
                }
            } else {
                datosValidos = true; // Salir del bucle si el usuario cancela el formulario
            }
        } while (!datosValidos);
    }

    private void mostrarFormularioModificarProducto(Producto producto) {
        JPanel formularioModificarProducto = new JPanel(new GridLayout(4, 2));

        JTextField txtNombre = new JTextField(producto.getNombre());
        JTextField txtDescripcion = new JTextField(producto.getDescripcion());
        JTextField txtPrecio = new JTextField(String.valueOf(producto.getPrecio()));

        formularioModificarProducto.add(new JLabel("Nombre:"));
        formularioModificarProducto.add(txtNombre);
        formularioModificarProducto.add(new JLabel("Descripción:"));
        formularioModificarProducto.add(txtDescripcion);
        formularioModificarProducto.add(new JLabel("Precio:"));
        formularioModificarProducto.add(txtPrecio);

        int resultado = JOptionPane.showConfirmDialog(null, formularioModificarProducto,
                "Modificar Producto", JOptionPane.OK_CANCEL_OPTION);
        if (resultado == JOptionPane.OK_OPTION) {
            String nombre = txtNombre.getText().trim();
            String descripcion = txtDescripcion.getText().trim();
            String precioStr = txtPrecio.getText().trim();

            if (nombre.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos deben ser rellenados.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                float precio;
                try {
                    precio = Float.parseFloat(precioStr);
                    producto.setNombre(nombre);
                    producto.setDescripcion(descripcion);
                    producto.setPrecio(precio);

                    if (controlador.modificarProducto(producto)) {
                        JOptionPane.showMessageDialog(null, "¡Producto modificado correctamente!");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo modificar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "El precio debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void mostrarFormularioEliminarProducto(Producto producto) {
        int confirmacion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea eliminar el producto?\n\n" +
                        "Nombre: " + producto.getNombre() + "\n" +
                        "Descripción: " + producto.getDescripcion() + "\n" +
                        "Precio: " + producto.getPrecio(),
                "Confirmar Eliminación de Producto", JOptionPane.OK_CANCEL_OPTION);

        if (confirmacion == JOptionPane.OK_OPTION) {
            // Llamar al método en el controlador para eliminar el producto
            if (controlador.darBajaProducto(producto)) {
                JOptionPane.showMessageDialog(null, "¡Producto eliminado correctamente!");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cargarProductos() {
        productoPanel.removeAll();
        ArrayList<Producto> productos = controlador.obtenerProductos();

        if (productos.isEmpty()) {
            JLabel noProductsLabel = new JLabel("No hay productos para ver");
            noProductsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            productoPanel.add(noProductsLabel);
        } else {
            for (Producto producto : productos) {
                JPanel productBox = crearCajaProducto(producto);
                productoPanel.add(productBox);
            }
        }

        productoPanel.revalidate();
        productoPanel.repaint();
    }

    private JPanel crearCajaProducto(Producto producto) {
        JPanel cajaProducto = new JPanel(new GridLayout(1, 1)); // GridLayout con 1 fila y 1 columna

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY), // Borde arriba y abajo
                BorderFactory.createEmptyBorder(10, 10, 10, 10) // Margen interno
        ));

        JLabel tipoLbl = new JLabel(producto.getTipo());
        tipoLbl.setFont(new Font("Arial", Font.BOLD, 18));
        infoPanel.add(tipoLbl);

        JLabel IDLbl = new JLabel("ID: " + producto.getCodigo());
        IDLbl.setFont(new Font("Arial", Font.BOLD, 18));
        infoPanel.add(IDLbl);

        JLabel nombreLbl = new JLabel("Nombre: " + producto.getNombre());
        nombreLbl.setFont(new Font("Arial", Font.BOLD, 16));
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

        cajaProducto.add(infoPanel); // Añadir el panel de información a la primera columna

        return cajaProducto;
    }
}
