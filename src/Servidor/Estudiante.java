package Servidor;

public class Estudiante {
    private String login;
    private String clave;

    public Estudiante(String login, String clave) {
        this.login = login;
        this.clave = clave;
    }

    public String getLogin() {
        return login;
    }

    public String getClave() {
        return clave;
    }
    
    public String toString()
    {
        String cadena;
        cadena = login +";"+clave;
        return cadena;
    }
}
