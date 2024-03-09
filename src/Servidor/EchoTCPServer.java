package Servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoTCPServer {
    public static final int PORT = 3400;
    private final PrincipalServidor serv;
    private ServerSocket listener;
    private Socket serverSideSocket;
    private PrintWriter toNetwork;
    private BufferedReader fromNetwork;

    public EchoTCPServer(PrincipalServidor ps) {
        serv = ps;
        System.out.println("Servidor ejecutándose en el puerto: " + PORT);
    }

    public void init() throws Exception {
        listener = new ServerSocket(PORT);
        serverSideSocket = listener.accept();//espera a que el cliente se comunique
        createStreams(serverSideSocket);
        protocol(serverSideSocket);
        while (true) {
            String res = leerMensaje();
            responder(res);
        }
    }

    public String leerMensaje() throws Exception {
        createStreams(serverSideSocket);
        String cadena = fromNetwork.readLine();
        String[] resul = cadena.split("@");
        String respuesta = "";

        switch (resul[0]) {
            case "1":
                respuesta = serv.login(resul[1], resul[2]);
                break;

            case "2":
                respuesta = serv.mostrarCarreras();
                break;

            case "3":
                respuesta = serv.mostrarMaterias(resul[1], resul[2]);
                break;

            case "4":
                respuesta = serv.matricular(resul[1]);
                break;

        }
        return respuesta;
    }

    public void protocol(Socket socket) throws Exception {
        String message = fromNetwork.readLine();
        System.out.println("[Cliente]: " + message);
        String answer = "Conexión establecida";
        toNetwork.println(answer);
    }

    /*
    Método que me permite recibir(inputStreamReader) mensajes del cliente
     */
    private void createStreams(Socket socket) throws Exception {
        toNetwork = new PrintWriter(socket.getOutputStream(), true);
        fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    private void responder(String res) {
        toNetwork.println(res);
    }

}
