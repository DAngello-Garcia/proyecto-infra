package Servidor;

import Servidor.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {
    public static final String RUTA_ARCHIVO_ESTUDIANTES = "src/Servidor/archivos/estudiantes.txt";
    public static final String RUTA_ARCHIVO_MATRICULAS = "src/Servidor/archivos/matriculas.txt";
    public static final String RUTA_ARCHIVO_CARRERAS = "src/Servidor/archivos/carreras.txt";
    public static final String RUTA_ARCHIVO_MATERIAS = "src/Servidor/archivos/materias.txt";

    public static void guardarEstudiantes(List<Estudiante> listaEstudiantes) throws IOException {
        String contenido = "";
        for (Estudiante estudiante : listaEstudiantes) {
            contenido += estudiante.getCodigo() + "@@" + estudiante.getNombre() + "@@" + estudiante.getClave() + "\n";
        }
        guardarArchivo(RUTA_ARCHIVO_ESTUDIANTES, contenido);
    }

    public static void guardarCarreras(List<Carrera> listaCarreras) throws IOException {
        String contenido = "";
        for (Carrera carrera : listaCarreras) {
            contenido += carrera.getCodigo() + "@@" + carrera.getNombre() + "\n";
        }
        guardarArchivo(RUTA_ARCHIVO_CARRERAS, contenido);
    }

    public static void guardarMaterias(List<Materia> listaMaterias) throws IOException {
        String contenido = "";
        for (Materia materia : listaMaterias) {
            contenido += materia.getCodigo() + "@@" + materia.getNombre() + "@@" + materia.getCreditos() + "@@" + materia.getTipoMateria().getTipo() + "@@" + materia.getTipoMateria().getCosto() + "@@" + materia.getCarrera().getCodigo() + "\n";
        }
        guardarArchivo(RUTA_ARCHIVO_MATERIAS, contenido);
    }

    public static void guardarMatriculas(List<Matricula> listaMatriculas) throws IOException {
        String contenido = "";
        for (Matricula matricula : listaMatriculas) {
            contenido += matricula.getId() + "@@" + matricula.getFecha().toString() + "@@" + matricula.getCosto() + "@@" + matricula.getEstadoMatricula().toString() + "@@" + matricula.getEstudiante().getCodigo();
            for (Materia materia : matricula.getMaterias()) {
                contenido += "@@" + materia.getNombre();
            }
            contenido += "\n";
        }
        guardarArchivo(RUTA_ARCHIVO_MATRICULAS, contenido);
    }

    public static void guardarArchivo(String ruta, String contenido) throws IOException {
        FileWriter fw = new FileWriter(ruta, false); //false para no append
        BufferedWriter bfw = new BufferedWriter(fw);
        bfw.write(contenido);
        bfw.close();
        fw.close();
    }

    public static List<Estudiante> cargarEstudiantes() throws IOException {
        List<Estudiante> estudiantes = new ArrayList<>();
        ArrayList<String> contenido = leerArchivo(RUTA_ARCHIVO_ESTUDIANTES);

        for (String linea : contenido) {
            if (!linea.isEmpty()) {
                String[] partes = linea.split("@@");
                Estudiante estudiante = new Estudiante();
                estudiante.setCodigo(partes[0]);
                estudiante.setNombre(partes[1]);
                estudiante.setClave(partes[2]);
                estudiantes.add(estudiante);
            }
        }
        return estudiantes;
    }

    public static List<Materia> cargarMaterias() throws IOException {
        List<Materia> materias = new ArrayList<>();
        ArrayList<String> contenido = leerArchivo(RUTA_ARCHIVO_MATERIAS);
        String linea = "";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);
            String[] partes = linea.split("@@");
            Materia materia = new Materia();
            TipoMateria tipoMateria = new TipoMateria();
            Carrera carrera = new Carrera();
            materia.setCodigo(partes[0]);
            materia.setNombre(partes[1]);
            materia.setCreditos(Integer.parseInt(partes[2]));
            tipoMateria.setTipo(partes[3]);
            tipoMateria.setCosto(Double.parseDouble(partes[4]));
            carrera.setCodigo(partes[5]);
            materia.setTipoMateria(tipoMateria);
            materia.setCarrera(carrera);
            materias.add(materia);
        }
        return materias;
    }

    public static List<Carrera> cargarCarreras() throws IOException {
        List<Carrera> carreras = new ArrayList<>();
        ArrayList<String> contenido = leerArchivo(RUTA_ARCHIVO_CARRERAS);
        String linea = "";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);
            String[] partes = linea.split("@@");
            Carrera carrera = new Carrera();
            carrera.setCodigo(partes[0]);
            carrera.setNombre(partes[1]);
            carreras.add(carrera);
        }
        return carreras;
    }

    public static List<Matricula> cargarMatriculas() throws IOException {
        List<Matricula> matriculas = new ArrayList<>();
        ArrayList<String> contenido = leerArchivo(RUTA_ARCHIVO_MATRICULAS);
        String linea = "";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);
            String[] partes = linea.split("@@");
            Matricula matricula = new Matricula();
            Estudiante estudiante = new Estudiante();
            ArrayList<Materia> materias = new ArrayList<>();
            matricula.setId(partes[0]);
            DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            matricula.setFecha(LocalDate.parse(partes[1], parser));
            matricula.setCosto(Double.parseDouble(partes[2]));
            matricula.setEstadoMatricula(EstadoMatricula.valueOf(partes[3]));
            estudiante.setCodigo(partes[4]);
            for (int j = 5; j < partes.length; j++) {
                Materia materia = new Materia();
                materia.setNombre(partes[j]);
                materias.add(materia);
            }
            matricula.setEstudiante(estudiante);
            matricula.setMaterias(materias);
            matriculas.add(matricula);
        }
        return matriculas;
    }

    public static ArrayList<String> leerArchivo(String ruta) throws IOException {
        ArrayList<String> contenido = new ArrayList<String>();
        FileReader fr = new FileReader(ruta);
        BufferedReader bfr = new BufferedReader(fr);
        String linea = "";

        while ((linea = bfr.readLine()) != null)
            contenido.add(linea);

        bfr.close();
        fr.close();
        return contenido;
    }

    public static void cargarDatos(Universidad universidad) throws IOException {
        List<Estudiante> listaEstudiantes = cargarEstudiantes();
        List<Carrera> listaCarreras = cargarCarreras();
        List<Materia> listaMaterias = cargarMaterias();
        List<Matricula> listaMatriculas = cargarMatriculas();
        listaMaterias = cargarCarrerasMaterias(listaCarreras, listaMaterias);
        listaMatriculas = cargarEstudianteMateriasMatriculas(listaEstudiantes, listaMaterias, listaMatriculas);

        universidad.setListaEstudiantes(listaEstudiantes);
        universidad.setListaCarreras(listaCarreras);
        universidad.setListaMaterias(listaMaterias);
        universidad.setListaMatriculas(listaMatriculas);
    }

    private static List<Matricula> cargarEstudianteMateriasMatriculas(List<Estudiante> listaEstudiantes, List<Materia> listaMaterias, List<Matricula> listaMatriculas) {
        List<Matricula> matriculas = new ArrayList<>();
        for (Matricula matricula: listaMatriculas) {
            for (Estudiante estudiante: listaEstudiantes) {
                if(estudiante.getCodigo().equals(matricula.getEstudiante().getCodigo())) {
                    matricula.setEstudiante(estudiante);
                }
                for (Materia materiaMatricula: matricula.getMaterias()) {
                    for (Materia materia: listaMaterias) {
                        if(materiaMatricula.getNombre().equals(materia.getNombre())) {
                            int index = matricula.getMaterias().indexOf(materiaMatricula);
                            matricula.getMaterias().set(index, materia);
                        }
                    }
                }
            }
            matriculas.add(matricula);
        }
        return matriculas;
    }

    private static List<Materia> cargarCarrerasMaterias(List<Carrera> listaCarreras, List<Materia> listaMaterias) {
        List<Materia> materias = new ArrayList<>();
        for (Materia materia: listaMaterias) {
            for (Carrera carrera: listaCarreras) {
                if(materia.getCarrera().getCodigo().equals(carrera.getCodigo()))
                    materia.setCarrera(carrera);
            }
            materias.add(materia);
        }
        return materias;
    }
}