package edu.upc.dsa.services;


import edu.upc.dsa.Covid19ManageImpl;
import edu.upc.dsa.TracksManager;
import edu.upc.dsa.TracksManagerImpl;
import edu.upc.dsa.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Api(value = "/covid", description = "Endpoint to Track Service")
@Path("/covid")
public class Covid19Service {

    private Covid19ManageImpl cm;

    public Covid19Service() {
        this.cm = Covid19ManageImpl.getInstance();
        if (cm.getVacunaciones().size()==0) {

            //Creamos usuario
            User u = new User("123","Carlos Alberto Duran");
            this.cm.addUser(u,"123");


            //Aplicamos vacuna
            this.cm.aplicarVacuna("123","Pfizer",new Date(2020,7,20));
            this.cm.aplicarVacuna("124","Moderna",new Date(2020,7,20));


            //Seguimiento
            Seguimiento se = new Seguimiento("123",new Date(2020,7,21),"Se escuentra bien");
            Seguimiento se2 = new Seguimiento("123",new Date(2020,7,23),"Se escuentra con dolor de cabeza");
            this.cm.seguimientoVacuna(se);
            this.cm.seguimientoVacuna(se2);


        }


    }

    @POST
    @ApiOperation(value = "Aplicar vacuna", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Vacuna.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/aplicarVacuna")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newVacuna(Vacuna vacuna) {

        if (vacuna.getIdVacuna()==null || vacuna.getIdUsuario()==null)  return Response.status(500).entity(vacuna).build();
        this.cm.aplicarVacuna(vacuna.getIdUsuario(), vacuna.getIdVacuna(),vacuna.getFechaVacunación());
        return Response.status(201).entity(vacuna).build();
    }

    @GET
    @ApiOperation(value = "Ordenar vacunas mas aplicadas", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = TipoVacuna.class, responseContainer="List"),
    })
    @Path("/vacunasMas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks() {

        TipoVacuna[] tip = this.cm.ordenVacunasMasAplicadas();
        List<TipoVacuna> myList = new LinkedList<>(Arrays.asList(tip));
        GenericEntity<List<TipoVacuna>> entity = new GenericEntity<List<TipoVacuna>>(myList) {};
        return Response.status(201).entity(entity).build()  ;

    }
    @POST
    @ApiOperation(value = "Añadir seguimiento de una vacuna", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Seguimiento.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/anadirSeguimiento")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newSeguimiento(Seguimiento seguimiento) {

        if (seguimiento.getIdUsuario()==null || seguimiento.getDescripcionEstado()==null)  return Response.status(500).entity(seguimiento).build();
        //this.cm.aplicarVacuna(vacuna.getIdUsuario(), vacuna.getIdVacuna(),vacuna.getFechaVacunación());
        this.cm.seguimientoVacuna(seguimiento);
        return Response.status(201).entity(seguimiento).build();
    }

    @GET
    @ApiOperation(value = "get lista de seguimiento by user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Seguimiento.class, responseContainer="List"),
    })
    @Path("prueba/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSeguimientosbyUserId(@PathParam("id") String id) {

        List<Seguimiento> se = this.cm.listadoSeguimientoPersona(id);

        GenericEntity<List<Seguimiento>> entity = new GenericEntity<List<Seguimiento>>(se) {};
        return Response.status(201).entity(entity).build()  ;

    }

    /*@GET
    @ApiOperation(value = "get all Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Track.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks() {

        List<Track> tracks = this.tm.findAll();

        GenericEntity<List<Track>> entity = new GenericEntity<List<Track>>(tracks) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "get a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Track.class),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrack(@PathParam("id") String id) {
        Track t = this.tm.getTrack(id);
        if (t == null) return Response.status(404).build();
        else  return Response.status(201).entity(t).build();
    }

    @DELETE
    @ApiOperation(value = "delete a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}")
    public Response deleteTrack(@PathParam("id") String id) {
        Track t = this.tm.getTrack(id);
        if (t == null) return Response.status(404).build();
        else this.tm.deleteTrack(id);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "update a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/")
    public Response updateTrack(Track track) {

        Track t = this.tm.updateTrack(track);

        if (t == null) return Response.status(404).build();

        return Response.status(201).build();
    }



    @POST
    @ApiOperation(value = "create a new Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Track.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newTrack(Track track) {

        if (track.getSinger()==null || track.getTitle()==null)  return Response.status(500).entity(track).build();
        this.tm.addTrack(track);
        return Response.status(201).entity(track).build();
    }*/

}