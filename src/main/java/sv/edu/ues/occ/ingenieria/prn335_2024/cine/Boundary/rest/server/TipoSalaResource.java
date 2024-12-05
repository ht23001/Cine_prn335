package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.rest.server;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.TipoSalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoSala;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

@Path("tiposala")
public class TipoSalaResource implements Serializable {

    @Inject
    TipoSalaBean tsBean;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findRange(
     @QueryParam("first")
    @DefaultValue("0")
    int firstResult,

    @QueryParam("max")
    @DefaultValue("3")

    int maxResults){

        try {
            if(firstResult>=0 & maxResults>0 & maxResults<=50){
        List<TipoSala> encontrados =  tsBean.findRange(firstResult, maxResults);
        long total= tsBean.Count();
        Response.ResponseBuilder builder = Response.ok(encontrados).header("Total-Elements",total)
                .type(MediaType.APPLICATION_JSON); // Response.ok codigo 200 contiene el body
        return builder.build(); // build devuelve response
            }else{
                // 422 contenido no accesible (parametros no validos)
                return Response.status(422).header("wrong-parameter", "first "+ firstResult +"max" +maxResults).build();
            }

        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            return Response.status(500).entity(e.getMessage()).build();

        }

    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findById(@PathParam("id") Integer id){
        if(id!=null){
            try{
                TipoSala encontrado= tsBean.findById(id);
                if(encontrado!=null){
                    Response.ResponseBuilder builder= Response.ok(encontrado).type(MediaType.APPLICATION_JSON);
                    return builder.build();
                }
                // si el tipo sala es null, no se encontro

                return Response.status(404).header("NOT-FOUND", "ID:"+id).build();

            }catch (Exception e){
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                return Response.status(500).entity(e.getMessage()).build();
            }
        }
        return Response.status(422).header("WRONG - PARAMETER", "ID:"+id).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(TipoSala tipoSala, @Context UriInfo uriInfo){

        if(tipoSala!=null & tipoSala.getIdTipoSala()==null){
            try{
                tsBean.Create(tipoSala);
                if(tipoSala.getIdTipoSala()!=null){
                    UriBuilder uriBuilder=uriInfo.getAbsolutePathBuilder();
                    uriBuilder.path(String.valueOf(tipoSala.getIdTipoSala()));
                    return Response.created(uriBuilder.build()).build();

                }
                return Response.status(500).header("PROCESS-ERROR", "RECORD COULDNT BE CREATED").build();
            }catch (Exception e){
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                return Response.status(500).entity(e.getMessage()).build();
            }


        }

        return Response.status(422).header("WRONG - PARAMETER", "TipoSala"+tipoSala ).build();
    }


}

