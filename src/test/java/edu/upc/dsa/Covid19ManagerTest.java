package edu.upc.dsa;

import edu.upc.dsa.models.Seguimiento;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;


public class Covid19ManagerTest {

    Covid19ManageImpl cm;
    Seguimiento se;
    @Before
    public void setUp() throws Exception {
        cm = Covid19ManageImpl.getInstance();
        se = new Seguimiento("556k",new Date(2020,7,21),"Parece que esta bien");
    }
    @After
    public void tearDown() throws Exception {
        cm.clear();
    }

    @Test
    public void aplicarVacuna() throws Exception {
        //Aplicamos una vacunacion
        cm.aplicarVacuna("556k","Pfizer",new Date(2020,7,20));
        //Probamos que la cantidad de vacunados sea 1
        Assert.assertEquals(1,cm.getVacunaciones().size());
    }

    @Test
    public void añadirSeguimiento() throws Exception {

        cm.seguimientoVacuna(se);
        Assert.assertEquals(se,cm.getListaSeguimiento().get(0));


    }

}