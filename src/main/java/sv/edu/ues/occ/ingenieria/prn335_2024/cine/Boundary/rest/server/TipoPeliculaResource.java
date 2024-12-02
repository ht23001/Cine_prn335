package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.rest.server;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.TipoPeliculaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoPelicula;


import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("tipopelicula")
public class TipoPeliculaResource implements Serializable{

    @Inject
    TipoPeliculaBean tpBean;

    //   METODO (RESPONSE) PARA OBTENER UN RANGO DE ELEMENTOS
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findRange(@QueryParam("first") @DefaultValue("0") int firstResult,
                              @QueryParam("max") @DefaultValue("10") int maxResults){
        try {

            if(firstResult>=0 & maxResults>0 & maxResults<=50){
                List<TipoPelicula> encontrados= tpBean.findRange(firstResult, maxResults);
                long total= tpBean.count();
                Response.ResponseBuilder builder= Response.ok(encontrados).
                        header("TOTAL-ELEMENTS",total)
                        .type(MediaType.APPLICATION_JSON);
                return builder.build();
            }else{
                // 422 contenido no accesible (parametros no validos)
                return Response.status(422).header("wrong-parameter", "first "+ firstResult +"max" +maxResults).build();
            }

        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            return Response.status(500).entity(e.getMessage()).build();
        }

    }
    // METODO (RESPONSE) PARA OBTENER UN ELEMENTO POR ID

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findByid(@PathParam("id") Integer id){

        if(id!=null){
            try{
                TipoPelicula encontrado= tpBean.findById(id);
                 if(encontrado!=null){
                     Response.ResponseBuilder builder= Response.ok(encontrado).type(MediaType.APPLICATION_JSON);
                     return builder.build();
                 }
                return Response.status(404).header("NOT-FOUND", "ID:"+id).build();

            }catch (Exception e){
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                return Response.status(500).entity(e.getMessage()).build();
            }

        }
        return Response.status(422).header("WRONG - PARAMETER", "ID:"+id).build();
    }


    // METODO (RESPONSE) PARA "CREAR" UN ELEMENTO

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(TipoPelicula tipoPelicula, @Context UriInfo uriInfo){

         // si el objeto a crear esta god y no han pasado ningun ID (en la bd esta autoimcrement)
        if(tipoPelicula!=null & tipoPelicula.getIdTipoPelicula()==null){
            try {
                tpBean.create(tipoPelicula);
                if(tipoPelicula.getIdTipoPelicula()!=null){
                    UriBuilder uriBuilder=uriInfo.getAbsolutePathBuilder();
                    uriBuilder.path(String.valueOf(tipoPelicula.getIdTipoPelicula()));
                    return Response.created(uriBuilder.build()).build();

                }
                return Response.status(500).header("PROCESS-ERROR", "RECORD COULDNT BE CREATED").build();
            }catch (Exception e){

                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                return Response.status(500).entity(e.getMessage()).build();
            }


        }
        return Response.status(422).header("WRONG - PARAMETER", "TipoPelicula"+tipoPelicula).build();

    }

}
