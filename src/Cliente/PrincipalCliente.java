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
                    String estudiante = cliente.leerMensaje();
                    if (!estudiante.equals(""))
                        menuMatricula(estudiante);
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

    public void menuMatricula(String estudiante) throws IOException {
        int opcion;
        String respuesta;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Universidad del chavo \n\n"
                    + "1. Iniciar matrícula \n"
                    + "2. Consultar matrícula \n"
                    + "3. Salir"));

            switch (opcion) {
                case 1:
                    menuMaterias(estudiante);
                    break;

                case 2:
                    consultarMatricula(estudiante);
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

    public void menuMaterias(String estudiante) throws IOException {
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
        String opcionMateria = "";
        do {
            cliente.enviarMensaje("3@"+carrerasServidor[opcion - 1]+"@"+estudiante); //para que devuelva las materias
            String materias = "";
            materias += cliente.leerMensaje();
            String[] materiasServidor = materias.split("@@");
            materias = "";
            int k = 1;
            for (int i = 0; i < materiasServidor.length - 1; i++) {
                String[] materiasDetalladas = materiasServidor[i].split("@");
                materias += k + ". ";
                materias += materiasDetalladas[0] + " - ";
                materias += materiasDetalladas[1] + " créditos - ";
                materias += materiasDetalladas[2];
                materias += "\n";
                k++;
            }
            materias += "\n\n Créditos inscritos: "+materiasServidor[materiasServidor.length-1];

            opcionMateria = JOptionPane.showInputDialog("Seleccione la materia que desea inscribir \n\n" + materias);

            String codigoMateria = materiasServidor[Integer.parseInt(opcionMateria) - 1].split("@")[3];
            cliente.enviarMensaje("4@"+codigoMateria+"@"+estudiante);
            String respuestaMatricula = cliente.leerMensaje();
            if (Integer.parseInt(respuestaMatricula) > 9 && Integer.parseInt(respuestaMatricula) < 15) {
                // Preguntar si desea agregar más materias
                int opcionAgregarMas = JOptionPane.showConfirmDialog(null,
                        "¿Desea agregar más materias?", "Agregar Materias", JOptionPane.YES_NO_OPTION);

                if (opcionAgregarMas == JOptionPane.NO_OPTION) {
                    cliente.enviarMensaje("5");
                    opcionMateria = null;
                }
            }

        } while(opcionMateria != null);

    }

    private void consultarMatricula(String estudiante) throws IOException {
        cliente.enviarMensaje("6@"+estudiante);
        String respuesta = cliente.leerMensaje();
        String[] matricula = respuesta.split("@@");
        String nombre = matricula[0];
        String fecha = matricula[1];
        String valor = matricula[2];
        respuesta += "Nombre: "+nombre + "\n" + "Fecha: "+fecha + "\n" + "Valor: "+valor + "\nMaterias: ";
        String[] materias = matricula[3].split("@");
        for (int i = 0; i < materias.length; i++) {
            respuesta += materias[i] + "\n";
        }
        JOptionPane.showInputDialog(respuesta);

    }

}
