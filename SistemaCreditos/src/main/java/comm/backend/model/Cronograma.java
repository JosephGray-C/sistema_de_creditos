
package comm.backend.model;

import java.sql.Date;


public class Cronograma {
    private int id;
    private int idCredito;
    private int numeroCuota;
    private Date fechaVencimiento;
    private double montoCuota;
    private String estado;

    public Cronograma(int id, int idCredito, int numeroCuota, Date fechaVencimiento, double montoCuota, String estado) {
        this.id = id;
        this.idCredito = idCredito;
        this.numeroCuota = numeroCuota;
        this.fechaVencimiento = fechaVencimiento;
        this.montoCuota = montoCuota;
        this.estado = estado;
    }

    public Cronograma() {
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

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getMontoCuota() {
        return montoCuota;
    }

    public void setMontoCuota(double montoCuota) {
        this.montoCuota = montoCuota;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
