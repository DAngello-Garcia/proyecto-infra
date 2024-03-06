package Cliente;

import javax.swing.*;
import java.io.IOException;

public class PrincipalCliente {
    private final EchoTCPClient cliente;

    public PrincipalCliente() throws Exception {
        cliente = new EchoTCPClient();
        cliente.init();

        menu();
    }

    public static void main(String[] args) throws Exception {
        PrincipalCliente pc = new PrincipalCliente();
    }

    public void menu() throws IOException {
        int opcion;
        String respuesta;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Universidad del chavo \n\n"
                    + "1. Ingresar a la universidad \n"
                    + "2. Salir"));

            switch (opcion) {
                case 1:
                    cliente.enviarMensaje("" + opcion);
                    String carreras = "";
                    respuesta = cliente.leerMensaje();
                    String[] resul = respuesta.split("@");
                    for (int i = 0; i < resul.length; i++) {
                        carreras += resul[i] + "\n";
                    }
                    JOptionPane.showInputDialog(null, carreras);
                    break;

                case 2:
                    if ( JOptionPane.showConfirmDialog(null,
                        "¿Seguro desea cerrar la aplicación?", "Salir", JOptionPane.YES_NO_OPTION) == 0){
                    cliente.getClientSideSocket().close();
                    JOptionPane.showMessageDialog(null, "Cerrando aplicación");
                    }
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción incorrecta");
                    break;
            }
        }
        while (opcion != 2);
    }

}
