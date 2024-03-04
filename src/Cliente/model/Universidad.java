package Cliente.model;

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

    public boolean login(Estudiante estudiante, List<Estudiante> estudiantes) {
        boolean valido = false;
        for(Estudiante user: estudiantes) {
            if(user.getCodigo().equals(estudiante.getCodigo()) && user.getClave().equals(estudiante.getClave()))
                valido = true;
        }
        return valido;
    }

    public Matricula matricular(Matricula matricula) {
        System.out.println("Matriculado");
        return null;
    }

    public Estudiante agregarEstudiante(Estudiante est) {
        Estudiante estudiante = new Estudiante();//nuevo
        estudiante.setNombre(est.getNombre());
        estudiante.setClave(est.getClave());
        estudiante.setCodigo(est.getCodigo());
        getListaEstudiantes().add(estudiante);//acá se guarda el producto, es el final de la comunicación entre clases
        //ahora lo retorno ya con los datos settiados y hago lo inverso, devolverme!!
        return estudiante;
    }
}
