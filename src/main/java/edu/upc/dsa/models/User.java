package edu.upc.dsa.models;

import java.util.Date;

public class User {

    String idUsuario;
    String nom;
    //Date fechaSeguimiento;
    //String comentario;

    public User() {
    }

    public User(String idUsuario, String nom) {
        this.idUsuario = idUsuario;
        this.nom = nom;

    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
