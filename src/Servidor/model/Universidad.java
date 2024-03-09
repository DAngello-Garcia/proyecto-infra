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

    public String matricular(String codigoMateria, String codigoEstudiante) throws IOException {
        Materia materia = obtenerMateria(codigoMateria);
        Estudiante estudiante = obtenerEstudiante(codigoEstudiante);
        Matricula matricula1 = obtenerMatricula(estudiante);
        if (matricula1 == null) {
            matricula1 = new Matricula();
            matricula1.setEstudiante(estudiante);
            matricula1.getMaterias().add(materia);
            matricula1.setFecha(LocalDate.now());
            matricula1.setCosto(matricula1.calcularCosto());
            matricula1.setEstadoMatricula(EstadoMatricula.MATRICULADO);
            matricula1.setId(estudiante.getCodigo());
            getListaMatriculas().add(matricula1);
        } else {
            matricula1.getMaterias().add(materia);
            matricula1.setCosto(matricula1.calcularCosto());
        }
        int creditos = matricula1.calcularCreditos();
        return "" + creditos;
    }

    private Matricula obtenerMatricula(Estudiante estudiante) {
        Matricula matricula = null;
        for (Matricula matricula1 : getListaMatriculas()) {
            if (matricula1.getEstudiante().getCodigo().equals(estudiante.getCodigo())) {
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
            if (materia1.getCodigo().equals(codigoMateria))
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

    public String mostrarMaterias(String carrera, String codigoEstudiante) throws IOException {
        String lista = "";
        for (Materia materia : getListaMaterias()) {
            if (materia.getCarrera().getNombre().equals(carrera))
                lista += materia.getNombre() + "@" + materia.getCreditos() + "@" + materia.getTipoMateria().getTipo() + "@" + materia.getCodigo() + "@@";
        }
        Estudiante estudiante = obtenerEstudiante(codigoEstudiante);
        Matricula matricula1 = obtenerMatricula(estudiante);
        String creditos = "";
        if (matricula1 == null) {
            creditos = "0";
        } else {
            creditos += matricula1.calcularCreditos();
        }

        return lista + creditos;
    }

    public void guardarMatricula() throws IOException {
        Persistencia.guardarMatriculas(getListaMatriculas());
    }

    public String mostrarMatricula(String codigoEstudiante) throws IOException {
        String materias = "";
        Estudiante estudiante = obtenerEstudiante(codigoEstudiante);
        List<Matricula> matriculas = Persistencia.cargarMatriculas();
        String valor = null, fecha = null;
        for (Matricula matricula1 : matriculas) {
            if (matricula1.getId().equals(codigoEstudiante)) {
                valor = String.valueOf(matricula1.getCosto());
                fecha = String.valueOf(matricula1.getFecha());
                for (Materia materia : matricula1.getMaterias()) {
                    materias += materia.getNombre() + "@";
                }
            }
        }
        StringBuilder sb = new StringBuilder(materias);
        sb.deleteCharAt(materias.length() - 1);
        materias = sb.toString();

        String resultado = estudiante.getNombre() + "@@" + fecha + "@@" + valor + "@@" + materias;
        return resultado;
    }
}
