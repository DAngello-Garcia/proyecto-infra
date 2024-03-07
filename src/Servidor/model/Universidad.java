package Servidor.model;

import Servidor.Persistencia;

import java.io.IOException;
import java.io.Serializable;
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

    public boolean login(String user, String pass) throws IOException {
        boolean valido = false;
        for (Estudiante estudiante : getListaEstudiantes()) {
            if (estudiante.getCodigo().equals(user) && estudiante.getClave().equals(pass)) {
                valido = true;
                break;
            }
        }
        return valido;
    }

    public String matricular(String matricula) {
        return "Matriculado";
    }

    public String mostrarCarreras() throws IOException {
        String lista = "";
        for (Carrera carrera : getListaCarreras()) {
            lista += carrera.getNombre() + "@";
        }
        return lista;
    }

    public String mostrarMaterias(String carrera) throws IOException {
        String lista = "";
        for (Materia materia : getListaMaterias()) {
            if (materia.getCarrera().getNombre().equals(carrera))
            lista += materia.getNombre() + "@" + materia.getCreditos() + "@" + materia.getTipoMateria().getTipo() + "@@";
        }
        return lista;
    }
}
