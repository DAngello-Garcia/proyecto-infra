package Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.stream.Collectors;


public class EchoTCPClient {
    public static final String SERVER = "localhost";
    public static final int PORT = 3400;
    private static final Scanner SCANNER = new Scanner(System.in);
    private PrintWriter toNetwork;
    private BufferedReader fromNetwork;

    private Socket clientSideSocket;

    public EchoTCPClient() {
        System.out.println("Cliente ejecutándose...");
    }

    public Socket getClientSideSocket() {
        return clientSideSocket;
    }

    public void init() throws Exception {
        clientSideSocket = new Socket(SERVER, PORT);
        createStreams(clientSideSocket);
        protocol(clientSideSocket);
    }

    public void enviarMensaje(String cadena) throws IOException {
        toNetwork.println(cadena);
    }

    public String leerMensaje() throws IOException {
        return fromNetwork.readLine();
    }

    public void protocol(Socket socket) throws Exception {
        toNetwork.println("Estableciendo conexión");
        String fromServer = fromNetwork.readLine();
        System.out.println("[Servidor] " + fromServer);
    }

    private void createStreams(Socket socket) throws Exception {
        toNetwork = new PrintWriter(socket.getOutputStream(), true);
        fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
}
