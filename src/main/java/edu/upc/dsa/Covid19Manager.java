package edu.upc.dsa;

import edu.upc.dsa.models.Seguimiento;
import edu.upc.dsa.models.TipoVacuna;
import edu.upc.dsa.models.Vacuna;

import java.util.Date;
import java.util.List;

public interface Covid19Manager {

    public void aplicarVacuna(String idUsuario, String idVacuna, Date fecha);
    public List<Vacuna> obtenerVacunasOrdenadas();//Listar las vacunaciones por orden de vacuna y dentro de las vacunas por orden de vacunación
    public TipoVacuna[] ordenVacunasMasAplicadas();
    public void seguimientoVacuna(Seguimiento se);//Seguimiento debe de ser una clase
    public List<Seguimiento> listadoSeguimientoPersona(String idPersona);



}
