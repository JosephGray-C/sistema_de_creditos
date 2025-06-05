package comm.backend.model;

import java.io.Serializable;
import java.util.Date;

public class Pago implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int idCredito;
    private Date fechaPago;
    private double montoPagado;
    private String estado;

    public Pago() {
        this.estado = "Pagado";
    }

    public Pago(int id, int idCredito, Date fechaPago, double montoPagado, String estado) {
        this.id = id;
        this.idCredito = idCredito;
        this.fechaPago = fechaPago;
        this.montoPagado = montoPagado;
        this.estado = estado;
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

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
