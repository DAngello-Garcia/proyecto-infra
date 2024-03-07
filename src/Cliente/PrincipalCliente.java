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
        String user = "";
        String pass = "";
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Universidad del chavo \n\n"
                    + "1. Ingresar a la universidad \n"
                    + "2. Salir"));

            switch (opcion) {
                case 1:
                    user = JOptionPane.showInputDialog("Ingrese su código: ");
                    pass = JOptionPane.showInputDialog("Ingrese su clave: ");
                    cliente.enviarMensaje(opcion + "@" + user + "@" + pass);
                    if (cliente.leerMensaje().equals("ok"))
                        menuMatricula();
                    else
                        JOptionPane.showMessageDialog(null, "Login o clave incorrecta. Vuelva a intentarlo");
                    break;

                case 2:
                    if (JOptionPane.showConfirmDialog(null,
                            "¿Seguro desea cerrar la aplicación?", "Salir", JOptionPane.YES_NO_OPTION) == 0) {
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

    public void menuMatricula() throws IOException {
        int opcion;
        String respuesta;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Universidad del chavo \n\n"
                    + "1. Iniciar matrícula \n"
                    + "2. Consultar matrícula \n"
                    + "3. Salir"));

            switch (opcion) {
                case 1:
                    menuMaterias();
                    break;

                case 2:
                    consultarMatricula();
                    break;

                case 3:
                    if (JOptionPane.showConfirmDialog(null,
                            "¿Seguro desea cerrar la aplicación?", "Salir", JOptionPane.YES_NO_OPTION) == 0) {
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

    public void menuMaterias() throws IOException {
        int opcion;
        String respuesta;
        cliente.enviarMensaje("2"); //para que devuelva las carreras
        String carreras = "";
        respuesta = cliente.leerMensaje();
        String[] carrerasServidor = respuesta.split("@");
        int j = 1;
        for (int i = 0; i < carrerasServidor.length; i++) {
            carreras += j + ". " + carrerasServidor[i] + "\n";
            j++;
        }

        opcion = Integer.parseInt(JOptionPane.showInputDialog("Seleccione la carrera \n\n" + carreras));
        do {
            int opcionMateria = 0;
            String materias = "";
            cliente.enviarMensaje("3@"+carrerasServidor[opcion - 1]); //para que devuelva las materias
            materias += cliente.leerMensaje();
            String[] materiasServidor = materias.split("@@");
            materias = "";
            int k = 1;
            for (int i = 0; i < materiasServidor.length; i++) {
                String[] materiasDetalladas = materiasServidor[i].split("@");
                materias += k + ". ";
                materias += materiasDetalladas[0] + "   ";
                materias += materiasDetalladas[1] + " créditos   ";
                materias += materiasDetalladas[2] + "   ";
                materias += "\n";
                k++;
            }

            opcionMateria =
                    Integer.parseInt(JOptionPane.showInputDialog("Seleccione la materia que desea inscribir \n\n" + materias));

        } while(opcion != -1);

    }

    private void consultarMatricula() {
    }

}
