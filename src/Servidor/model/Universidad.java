package Servidor.model;

import Servidor.Persistencia;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Universidad implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Carrera> listaCarreras = new ArrayList<>();
    private List<Estudiante> listaEstudiantes = new ArrayList<>();
    private List<Matricula> listaMatriculas = new ArrayList<>();
    private List<Materia> listaMaterias = new ArrayList<>();

    public Universidad() {
    }

    public List<Carrera> getListaCarreras() {
        return listaCarreras;
    }

    public void setListaCarreras(List<Carrera> listaCarreras) {
        this.listaCarreras = listaCarreras;
    }

    public List<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(List<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

    public List<Matricula> getListaMatriculas() {
        return listaMatriculas;
    }

    public void setListaMatriculas(List<Matricula> listaMatriculas) {
        this.listaMatriculas = listaMatriculas;
    }

    public List<Materia> getListaMaterias() {
        return listaMaterias;
    }

    public void setListaMaterias(List<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    public String login(String user, String pass) throws IOException {
        String estudianteSesion = "";
        for (Estudiante estudiante : getListaEstudiantes()) {
            if (estudiante.getCodigo().equals(user) && estudiante.getClave().equals(pass)) {
                estudianteSesion = estudiante.getCodigo();
                break;
            }
        }
        return estudianteSesion;
    }

    public String matricular(String matricula) throws IOException {
        String[] materias = matricula.split("@");
        Materia materia = obtenerMateria(materias[materias.length - 2]);
        Estudiante estudiante = obtenerEstudiante(materias[materias.length - 1]);
        Matricula matricula1 = obtenerMatricula(estudiante);
        if(matricula1 == null) {
            matricula1 = new Matricula();
            matricula1.setEstudiante(estudiante);
            matricula1.getMaterias().add(materia);
            matricula1.setFecha(LocalDate.now());
            matricula1.setCosto(matricula1.calcularCosto());
        } else {
            matricula1.getMaterias().add(materia);
            matricula1.setCosto(matricula1.calcularCosto());
        }
        int creditos = matricula1.calcularCreditos();
        Persistencia.guardarMatriculas(getListaMatriculas());
        Persistencia.cargarDatos(this);
        return validarCreditos(creditos) + "@" + creditos;
    }

    private String validarCreditos(int creditos) {
        return creditos >= 10 && creditos <= 15 ? "ok" : "no";
    }

    private Matricula obtenerMatricula(Estudiante estudiante) {
        Matricula matricula = null;
        for (Matricula matricula1: getListaMatriculas()) {
            if(matricula1.getEstudiante().getCodigo().equals(estudiante.getCodigo())) {
                matricula = matricula1;
            }
        }
        return matricula;
    }

    private Estudiante obtenerEstudiante(String codigoEstudiante) {
        Estudiante estudiante = new Estudiante();
        for (Estudiante estudiante1 : getListaEstudiantes()) {
            if (estudiante1.getCodigo().equals(codigoEstudiante))
                estudiante = estudiante1;
        }
        return estudiante;
    }

    private Materia obtenerMateria(String codigoMateria) {
        Materia materia = new Materia();
        for (Materia materia1 : getListaMaterias()) {
            if (materia.getCodigo().equals(codigoMateria))
                materia = materia1;
        }
        return materia;
    }

    public String mostrarCarreras() throws IOException {
        String lista = "";
        for (Carrera carrera : getListaCarreras()) {
            lista += carrera.getNombre() + "@";
        }
        return lista;
    }

    public String mostrarMaterias(String carrera, String codigoEstudiane) throws IOException {
        String lista = "";
        for (Materia materia : getListaMaterias()) {
            if (materia.getCarrera().getNombre().equals(carrera))
            lista += materia.getNombre() + "@" + materia.getCreditos() + "@" + materia.getTipoMateria().getTipo() + "@@";
        }
        Estudiante estudiante = obtenerEstudiante(codigoEstudiane);
        Matricula matricula1 = obtenerMatricula(estudiante);
        String creditos = "";
        if(matricula1 == null) {
            creditos = "0";
        } else {
            creditos += matricula1.calcularCreditos();
        }

        return lista + "@";
    }
}
