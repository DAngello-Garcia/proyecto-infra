package Servidor;

import Servidor.model.Universidad;

import java.io.IOException;

public class PrincipalServidor {

    Universidad universidad = new Universidad();
    private final EchoTCPServer server;

    public PrincipalServidor() throws Exception {
        server = new EchoTCPServer(this);
        server.init();
    }

    public static void main(String[] args) throws Exception {
        PrincipalServidor ps = new PrincipalServidor();
    }

    public Universidad getUniversidad() {
        return universidad;
    }

    public String mostrarCarreras() throws IOException {
        return universidad.mostrarCarreras();
    }

    public String mostrarMaterias() {
        return null;
    }

}
