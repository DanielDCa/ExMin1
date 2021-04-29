package edu.upc.dsa.models;

public class TipoVacuna implements Comparable<TipoVacuna> {

    String idVacuna;
    int vacunacionesTotales;

    public TipoVacuna() {
    }

    public TipoVacuna(String idVacuna, int vacunacionesTotales) {
        this.idVacuna = idVacuna;
        this.vacunacionesTotales = vacunacionesTotales;
    }

    public String getIdVacuna() {
        return idVacuna;
    }

    public void setIdVacuna(String idVacuna) {
        this.idVacuna = idVacuna;
    }

    public int getVacunacionesTotales() {
        return vacunacionesTotales;
    }

    public void setVacunacionesTotales(int vacunacionesTotales) {
        this.vacunacionesTotales = vacunacionesTotales;
    }

    public void sumarVacuna(){
        vacunacionesTotales += 1;
    }

    @Override
    public int compareTo(TipoVacuna o) {

        if (vacunacionesTotales < o.vacunacionesTotales) {
            return 1;
        }
        if (vacunacionesTotales > o.vacunacionesTotales) {
            return -1;
        }
        return 0;

    }
}
