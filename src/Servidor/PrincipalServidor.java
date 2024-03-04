package Servidor;

import Servidor.model.*;
import Servidor.model.Estudiante;
import Servidor.persistencia.Persistencia;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PrincipalServidor {

    private EchoTCPServer server;
    private List<Carrera> listaCarreras;
    private List<Estudiante> listaEstudiantes;

    private List<Matricula> listaMatriculas;
    private List<Materia> listaMaterias;
    Universidad universidad = new Universidad();
    Persistencia persistencia = new Persistencia();
    public static void main(String args[]) throws Exception
    {
        PrincipalServidor ps = new PrincipalServidor();
    }

    public PrincipalServidor() throws Exception
    {
        server = new EchoTCPServer(this);
        datosQuemados();
        menu();
    }


    public void datosQuemados() throws IOException {
        this.listaEstudiantes = new ArrayList();
        this.listaMaterias = new ArrayList<>();
        this.listaMatriculas = new ArrayList<>();
        this.listaCarreras = new ArrayList<>();

        Estudiante est1 = new Estudiante();
        est1.setClave("123");
        est1.setCodigo("789");
        est1.setNombre("Luisa");
        listaEstudiantes.add(est1);
        universidad.getListaEstudiantes().add(est1);

        Estudiante est2 = new Estudiante();
        est2.setClave("456");
        est2.setCodigo("456");
        est2.setNombre("Yan");
        listaEstudiantes.add(est2);
        universidad.getListaEstudiantes().add(est2);

        Estudiante est3 = new Estudiante();
        est3.setClave("789");
        est3.setCodigo("123");
        est3.setNombre("D'angelo");
        listaEstudiantes.add(est3);
        universidad.getListaEstudiantes().add(est3);

        persistencia.guardarEstudiantes(listaEstudiantes);
//*****************************************************************************************
        Carrera ingenieria = new Carrera();
        ingenieria.setNombre("Ingeniería");
        ingenieria.setCodigo("123");
        listaCarreras.add(ingenieria);
        universidad.getListaCarreras().add(ingenieria);

        Carrera matematicas = new Carrera();
        matematicas.setNombre("Matemáticas");
        matematicas.setCodigo("123");
        listaCarreras.add(matematicas);
        universidad.getListaCarreras().add(matematicas);

        Carrera fisica = new Carrera();
        fisica.setNombre("Física");
        fisica.setCodigo("123");
        listaCarreras.add(fisica);
        universidad.getListaCarreras().add(fisica);

        persistencia.guardarCarreras(listaCarreras);//persitencia para guardar carreras
//**************************************Materias ingeniería*******************************************
        Materia sistemasOperativos = new Materia();
        sistemasOperativos.setCodigo("123");
        sistemasOperativos.setNombre("sistemasOperativos");
        sistemasOperativos.setCreditos(4);
        TipoMateria tipoMateria1 = new TipoMateria();
        tipoMateria1.setTipo("teorica");
        tipoMateria1.setCosto(100);
        sistemasOperativos.setTipoMateria(tipoMateria1);
        sistemasOperativos.setCarrera(ingenieria);
        listaMaterias.add(sistemasOperativos);
        universidad.getListaMaterias().add(sistemasOperativos);

        Materia sietmasInformacion = new Materia();
        sietmasInformacion.setCodigo("123");
        sietmasInformacion.setNombre("sietmasInformacion");
        sietmasInformacion.setCreditos(4);
        TipoMateria tipoMateria2 = new TipoMateria();
        tipoMateria2.setTipo("practica");
        tipoMateria2.setCosto(115);
        sietmasInformacion.setTipoMateria(tipoMateria2);
        sietmasInformacion.setCarrera(ingenieria);
        listaMaterias.add(sietmasInformacion);
        universidad.getListaMaterias().add(sietmasInformacion);

        Materia TDO = new Materia();
        TDO.setCodigo("123");
        TDO.setNombre("TDO");
        TDO.setCreditos(4);
        TipoMateria tipoMateria3 = new TipoMateria();
        tipoMateria3.setTipo("teorica");
        tipoMateria3.setCosto(100);
        TDO.setTipoMateria(tipoMateria3);
        TDO.setCarrera(ingenieria);
        listaMaterias.add(TDO);
        universidad.getListaMaterias().add(TDO);

        Materia POO = new Materia();
        POO.setCodigo("123");
        POO.setNombre("POO");
        POO.setCreditos(3);
        TipoMateria tipoMateria4 = new TipoMateria();
        tipoMateria4.setTipo("practica");
        tipoMateria4.setCosto(115);
        POO.setTipoMateria(tipoMateria4);
        POO.setCarrera(ingenieria);
        listaMaterias.add(POO);
        universidad.getListaMaterias().add(POO);

        Materia logica = new Materia();
        logica.setCodigo("123");
        logica.setNombre("logica");
        logica.setCreditos(3);
        TipoMateria tipoMateria5 = new TipoMateria();
        tipoMateria5.setTipo("practica");
        tipoMateria5.setCosto(115);
        logica.setTipoMateria(tipoMateria5);
        logica.setCarrera(ingenieria);
        listaMaterias.add(logica);
        universidad.getListaMaterias().add(logica);

//**************************************Materias matemáticas*******************************************
        Materia aritmetica = new Materia();
        aritmetica.setCodigo("123");
        aritmetica.setNombre("aritmetica");
        aritmetica.setCreditos(4);
        TipoMateria tipoMateria6 = new TipoMateria();
        tipoMateria6.setTipo("teorica");
        tipoMateria6.setCosto(100);
        aritmetica.setTipoMateria(tipoMateria6);
        aritmetica.setCarrera(matematicas);
        listaMaterias.add(aritmetica);
        universidad.getListaMaterias().add(aritmetica);

        Materia algebra = new Materia();
        algebra.setCodigo("123");
        algebra.setNombre("algebra");
        algebra.setCreditos(4);
        TipoMateria tipoMateria7 = new TipoMateria();
        tipoMateria7.setTipo("teorica y practica");
        tipoMateria7.setCosto(130);
        algebra.setTipoMateria(tipoMateria7);
        algebra.setCarrera(matematicas);
        listaMaterias.add(algebra);
        universidad.getListaMaterias().add(algebra);

        Materia numerosNaturales = new Materia();
        numerosNaturales.setCodigo("123");
        numerosNaturales.setNombre("numerosNaturales");
        numerosNaturales.setCreditos(3);
        TipoMateria tipoMateria8 = new TipoMateria();
        tipoMateria8.setTipo("teorica y practica");
        tipoMateria8.setCosto(130);
        numerosNaturales.setTipoMateria(tipoMateria8);
        numerosNaturales.setCarrera(matematicas);
        listaMaterias.add(numerosNaturales);
        universidad.getListaMaterias().add(numerosNaturales);

        Materia numerosEnteros = new Materia();
        numerosEnteros.setCodigo("123");
        numerosEnteros.setNombre("numerosNaturales");
        numerosEnteros.setCreditos(3);
        TipoMateria tipoMateria9 = new TipoMateria();
        tipoMateria9.setTipo("teorica y practica");
        tipoMateria9.setCosto(130);
        numerosEnteros.setTipoMateria(tipoMateria9);
        numerosEnteros.setCarrera(matematicas);
        listaMaterias.add(numerosEnteros);
        universidad.getListaMaterias().add(numerosEnteros);
//**************************************Materias física*******************************************
        Materia leyesFisicas = new Materia();
        leyesFisicas.setCodigo("123");
        leyesFisicas.setNombre("leyesFisicas");
        leyesFisicas.setCreditos(3);
        TipoMateria tipoMateria10 = new TipoMateria();
        tipoMateria10.setTipo("teorica");
        tipoMateria10.setCosto(100);
        leyesFisicas.setTipoMateria(tipoMateria10);
        leyesFisicas.setCarrera(fisica);
        listaMaterias.add(leyesFisicas);
        universidad.getListaMaterias().add(leyesFisicas);

        Materia leyesNewton = new Materia();
        leyesNewton.setCodigo("123");
        leyesNewton.setNombre("leyesNewton");
        leyesNewton.setCreditos(3);
        TipoMateria tipoMateria11 = new TipoMateria();
        tipoMateria11.setTipo("teorica");
        tipoMateria11.setCosto(100);
        leyesNewton.setTipoMateria(tipoMateria11);
        leyesNewton.setCarrera(fisica);
        listaMaterias.add(leyesNewton);
        universidad.getListaMaterias().add(leyesNewton);

        Materia leyesMaswel = new Materia();
        leyesMaswel.setCodigo("123");
        leyesMaswel.setNombre("leyesMaswel");
        leyesMaswel.setCreditos(3);
        TipoMateria tipoMateria12 = new TipoMateria();
        tipoMateria12.setTipo("teorica");
        tipoMateria12.setCosto(100);
        leyesMaswel.setTipoMateria(tipoMateria12);
        leyesMaswel.setCarrera(fisica);
        listaMaterias.add(leyesMaswel);
        universidad.getListaMaterias().add(leyesMaswel);

        Materia electricidad = new Materia();
        electricidad.setCodigo("123");
        electricidad.setNombre("electricidad");
        electricidad.setCreditos(3);
        TipoMateria tipoMateria13 = new TipoMateria();
        tipoMateria13.setTipo("teorica");
        tipoMateria13.setCosto(100);
        electricidad.setTipoMateria(tipoMateria13);
        electricidad.setCarrera(fisica);
        listaMaterias.add(electricidad);
        universidad.getListaMaterias().add(electricidad);

        Materia vectores = new Materia();
        vectores.setCodigo("123");
        vectores.setNombre("vectores");
        vectores.setCreditos(3);
        TipoMateria  tipoMateria14 = new TipoMateria();
        tipoMateria14.setTipo("teorica");
        tipoMateria14.setCosto(100);
        vectores.setTipoMateria( tipoMateria14);
        vectores.setCarrera(fisica);
        listaMaterias.add(vectores);
        universidad.getListaMaterias().add(vectores);

        persistencia.guardarMaterias(listaMaterias);//persitencia para guardar materias
    }

    public void menu() throws Exception
    {
        JOptionPane.showMessageDialog(null, "En construcción. No acose!!!");
        
        server.init();
    }

    public Universidad getUniversidad() {
        return universidad;
    }

   public boolean buscarUsuario(String login, String clave)
    {
        boolean encontrado=false;

        for(int i = 0; i< listaEstudiantes.size() && encontrado==false; i++)
        {
            if (listaEstudiantes.get(i).getNombre().equals(login) && listaEstudiantes.get(i).getClave().equals(clave))
            {
                encontrado =true;
            }
        }
        return encontrado;
    }

   /* public String buscarCuenta(String numero)
    {
        String cuenta="";
        boolean encontrado=false;
        for(int i=0;i<ListaCuentas.size() && encontrado==false;i++)
        {
            if (ListaCuentas.get(i).getNumero().equals(numero))
            {
                cuenta = ListaCuentas.get(i).toString();
                encontrado = true;
            }
        }
        return cuenta;
    }*/

    /*public String actualizarSaldoCuenta(String numero, double s) {
        String res="";

        boolean encontrado=false;
        for(int i=0;i<ListaCuentas.size() && encontrado==false;i++)
        {
            if (ListaCuentas.get(i).getNumero().equals(numero))
            {
                ListaCuentas.get(i).setSaldo(s);
                res = ListaCuentas.get(i).toString();
                encontrado = true;
            }
        }
        return res;
    }*/
}
