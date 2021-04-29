package edu.upc.dsa.models;

import java.util.Date;

public class Seguimiento {

    String idUsuario;
    Date fechaSeguimiento;
    String descripcionEstado;

    public Seguimiento() {
    }

    public Seguimiento(String idUsuario, Date fechaSeguimiento, String descripcionEstado) {
        this.idUsuario = idUsuario;
        this.fechaSeguimiento = fechaSeguimiento;
        this.descripcionEstado = descripcionEstado;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaSeguimiento() {
        return fechaSeguimiento;
    }

    public void setFechaSeguimiento(Date fechaSeguimiento) {
        this.fechaSeguimiento = fechaSeguimiento;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }
}
