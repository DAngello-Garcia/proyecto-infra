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

    public String mostrarMaterias(String carrera, String codigoEstudiane) throws IOException {
        return universidad.mostrarMaterias(carrera, codigoEstudiane);
    }

    public String login(String user, String pass) throws IOException {
        return universidad.login(user, pass);
    }

    public String matricular(String codigoMateria, String codigoEstudiante) throws IOException {
        return universidad.matricular(codigoMateria, codigoEstudiante);
    }

    public void guardarMatricula() throws IOException {
        universidad.guardarMatricula();
    }

    public String mostrarMatricula(String codigoEstudiante) throws IOException {
        return universidad.mostrarMatricula(codigoEstudiante);
    }
}
