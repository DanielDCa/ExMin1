package edu.upc.dsa;

import edu.upc.dsa.models.Seguimiento;
import edu.upc.dsa.models.TipoVacuna;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.Vacuna;
import org.apache.log4j.Logger;

import java.util.*;

public class Covid19ManageImpl  implements Covid19Manager {

    HashMap<String, User> users;
    //Queue<Sample> samples;
    List<Vacuna> vacunaciones;
    TipoVacuna[] marcasVacunas;
    //int[] numVacunaciones ={0,0,0};
    List<Seguimiento> listaSeguimiento;

    private static Covid19ManageImpl singleton;
    final static Logger logger = Logger.getLogger(TracksManagerImpl.class);


    public Covid19ManageImpl() {
        this.users = new HashMap<>();
        this.vacunaciones = new LinkedList<>();
        this.listaSeguimiento = new LinkedList<>();
        this.marcasVacunas = new TipoVacuna[3];

        marcasVacunas[0] = new TipoVacuna("Pfizer",0);
        marcasVacunas[1] = new TipoVacuna("Moderna",0);
        marcasVacunas[2] = new TipoVacuna("Astraseneca",0);

        
        //marcasVacunas = new String[3];
        //marcasVacunas ={"Pfizer","Moderna"};

    }
    public static Covid19ManageImpl getInstance(){
        if(singleton == null){//Si la instancia no existe, la creamos
            singleton = new Covid19ManageImpl();
        }

        return singleton;
    }
    @Override
    public void aplicarVacuna(String idUsuario, String idVacuna, Date fecha) {
        logger.info("Aplicando vaacuna a usuario: " + idUsuario);
        for (int i = 0; i < marcasVacunas.length; i++) {
            if(idVacuna == marcasVacunas[i].getIdVacuna()){
                marcasVacunas[i].sumarVacuna();
            }
        }
        vacunaciones.add(new Vacuna(idUsuario,idVacuna,fecha));

        logger.info("Usuario " + idUsuario + ", vacunado");
    }

    @Override
    public List<Vacuna> obtenerVacunasOrdenadas() {



        return null;
    }

    @Override
    public TipoVacuna[] ordenVacunasMasAplicadas() {

        logger.info("Organizamos vacunas mas aplicadas ");
        Arrays.sort(marcasVacunas);

        return marcasVacunas;
    }

    @Override
    public void seguimientoVacuna(Seguimiento se) {

        logger.info("Seguimiento a usuario: " + se.getIdUsuario());
        listaSeguimiento.add(se);

        logger.info("Seguimiento añadido correctamente");

    }

    @Override
    public List<Seguimiento> listadoSeguimientoPersona(String idPersona) {

        User u= users.get(idPersona);
        List<Seguimiento> seg= new LinkedList<>();

        if(u == null){
            logger.info("The user with id" + idPersona + "does not exits");
            return null;
        }
        else{

            for (Seguimiento s: listaSeguimiento) {
                if(s.getIdUsuario() == idPersona){
                    seg.add(s);
                }
            }
            //System.out.println(userSamples);
            logger.info("Samples for user:"+ idPersona +", found");

            return seg;
        }

    }
    public void clear(){//Limpiamos toda la estructura de datos

        this.users.clear();
        this.listaSeguimiento.clear();
        this.vacunaciones.clear();
        this.marcasVacunas[0].setVacunacionesTotales(0);
        this.marcasVacunas[1].setVacunacionesTotales(0);
        this.marcasVacunas[2].setVacunacionesTotales(0);
    }
    public void addUser(User u,String id){

        users.put(id,u);
    }

    public List<Vacuna> getVacunaciones() {
        return vacunaciones;
    }

    public void setVacunaciones(List<Vacuna> vacunaciones) {
        this.vacunaciones = vacunaciones;
    }

    public TipoVacuna[] getMarcasVacunas() {
        return marcasVacunas;
    }

    public void setMarcasVacunas(TipoVacuna[] marcasVacunas) {
        this.marcasVacunas = marcasVacunas;
    }

    public List<Seguimiento> getListaSeguimiento() {
        return listaSeguimiento;
    }

    public void setListaSeguimiento(List<Seguimiento> listaSeguimiento) {
        this.listaSeguimiento = listaSeguimiento;
    }
}
