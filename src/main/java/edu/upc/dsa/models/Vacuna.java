package edu.upc.dsa.models;

import java.util.Date;

public class Vacuna implements  Comparable<Vacuna>{

    String idUsuario;
    String idVacuna;//Pfizer ..moderna
    Date fechaVacunación;

    public Vacuna() {
    }

    public Vacuna(String idUsuario, String idVacuna, Date fechaVacunación) {
        this.idUsuario = idUsuario;
        this.idVacuna = idVacuna;
        this.fechaVacunación = fechaVacunación;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdVacuna() {
        return idVacuna;
    }

    public void setIdVacuna(String idVacuna) {
        this.idVacuna = idVacuna;
    }

    public Date getFechaVacunación() {
        return fechaVacunación;
    }

    public void setFechaVacunación(Date fechaVacunación) {
        this.fechaVacunación = fechaVacunación;
    }

    @Override
    public int compareTo(Vacuna o) {
        return 0;
    }
}
