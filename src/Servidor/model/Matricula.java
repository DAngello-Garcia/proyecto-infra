package Servidor.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Matricula implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private ArrayList<Materia> materias;
    private double costo;
    private LocalDate fecha;
    private EstadoMatricula estadoMatricula;
    private Estudiante estudiante;

    public Matricula() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(ArrayList<Materia> materias) {
        this.materias = materias;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public EstadoMatricula getEstadoMatricula() {
        return estadoMatricula;
    }

    public void setEstadoMatricula(EstadoMatricula estadoMatricula) {
        this.estadoMatricula = estadoMatricula;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public double calcularCosto() {
        double suma = 0;
        for (Materia materia : getMaterias()) {
            suma += materia.getTipoMateria().getCosto();
        }
        return suma;
    }

    public int calcularCreditos() {
        int creditos = 0;
        for (Materia materia : getMaterias()) {
            creditos += materia.getCreditos();
        }
        return creditos;
    }
}
