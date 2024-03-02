package Cliente.model;

import java.io.Serializable;

public class TipoMateria implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tipo;
    private double costo;

    public TipoMateria() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}
