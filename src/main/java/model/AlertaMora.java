
package model;

import java.sql.Timestamp;

public class AlertaMora {
    private int id;
    private int idCredito;
    private String mensaje;
    private Timestamp fechaAlerta;
    private boolean leido;

    public AlertaMora() {}

    public AlertaMora(int id, int idCredito, String mensaje, Timestamp fechaAlerta, boolean leido) {
        this.id = id;
        this.idCredito = idCredito;
        this.mensaje = mensaje;
        this.fechaAlerta = fechaAlerta;
        this.leido = leido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(int idCredito) {
        this.idCredito = idCredito;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Timestamp getFechaAlerta() {
        return fechaAlerta;
    }

    public void setFechaAlerta(Timestamp fechaAlerta) {
        this.fechaAlerta = fechaAlerta;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }
    
    
}
