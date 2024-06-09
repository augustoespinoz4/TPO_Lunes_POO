import Controlador.Controlador;
import GUI.LoginView;

import javax.naming.ldap.Control;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        // SwingUtilities.invokeLater asegura que el código que manipula la GUI
        // se ejecute en el Event Dispatch Thread (EDT).
        // Esto es crucial porque Swing no es seguro para hilos y todas las actualizaciones
        // de la interfaz de usuario deben hacerse en el EDT para evitar problemas de concurrencia.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Controlador controlador = new Controlador();

                LoginView loginView = new LoginView(controlador);
                loginView.setVisible(true);
            }
        });
    }
}