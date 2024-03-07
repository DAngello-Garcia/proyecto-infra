package Servidor;

import Servidor.model.Universidad;

import java.io.IOException;

public class PrincipalServidor {

    Universidad universidad = new Universidad();
    private final EchoTCPServer server;

    public PrincipalServidor() throws Exception {
        Persistencia.cargarDatos(universidad);
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

    public String mostrarMaterias(String carrera) throws IOException {
        return universidad.mostrarMaterias(carrera);
    }

    public boolean login(String user, String pass) throws IOException {
        return universidad.login(user, pass);
    }

    public String matricular(String materia) {
        return universidad.matricular(materia);
    }
}
