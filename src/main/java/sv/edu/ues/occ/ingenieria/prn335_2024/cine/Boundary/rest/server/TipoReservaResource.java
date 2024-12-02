package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.rest.server;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.TipoReservaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoReserva;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("tiporeserva")
public class TipoReservaResource implements Serializable {

    @Inject
    TipoReservaBean trBean;

    // METODO GET PARA OBTENER UN RANGO DE ELEMENTOS
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findRange(@QueryParam("first ") @DefaultValue("0") int firstResult,
                              @QueryParam("max") @DefaultValue("10") int maxResults){

        try{
            if(firstResult>=0 & maxResults>0 & maxResults<=50){
                List<TipoReserva> encontrados= trBean.findRange(firstResult, maxResults);
                long total= trBean.count();
                Response.ResponseBuilder builder = Response.ok(encontrados).
                        header("TOTAL-ELEMENTS",total).type(MediaType.APPLICATION_JSON);
                return builder.build();
            }
            else{
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
                TipoReserva encontrado= trBean.findById(id);
                if(encontrado!=null){
                    Response.ResponseBuilder builder= Response.ok(encontrado).type(MediaType.APPLICATION_JSON);
                    return builder.build();
                }
                // si el tipo reserva es null, no se encontro

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
    public Response create(TipoReserva tipoReserva, @Context UriInfo uriInfo){

        if(tipoReserva!=null & tipoReserva.getIdTipoReserva()==null){
            try{
                trBean.create(tipoReserva);
                if(tipoReserva.getIdTipoReserva()!=null){
                    UriBuilder uriBuilder=uriInfo.getAbsolutePathBuilder();
                    uriBuilder.path(String.valueOf(tipoReserva.getIdTipoReserva()));
                    return Response.created(uriBuilder.build()).build();

                }
                return Response.status(500).header("PROCESS-ERROR", "RECORD COULDNT BE CREATED").build();
            }catch (Exception e){
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                return Response.status(500).entity(e.getMessage()).build();
            }

        }
        return Response.status(422).header("WRONG - PARAMETER", "TipoReserva"+tipoReserva).build();
    }



}
