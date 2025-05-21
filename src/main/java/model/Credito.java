
package model;

import java.sql.Date;


public class Credito {
    private int id;
    private int idCliente;
    private double monto;
    private double tasaInteres;
    private int plazoMeses;
    private Date fechaInicio;
    private String estado;

    public Credito(int id, int idCliente, double monto, double tasaInteres, int plazoMeses, Date fechaInicio, String estado) {
        this.id = id;
        this.idCliente = idCliente;
        this.monto = monto;
        this.tasaInteres = tasaInteres;
        this.plazoMeses = plazoMeses;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
    }

    public Credito() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public int getPlazoMeses() {
        return plazoMeses;
    }

    public void setPlazoMeses(int plazoMeses) {
        this.plazoMeses = plazoMeses;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
