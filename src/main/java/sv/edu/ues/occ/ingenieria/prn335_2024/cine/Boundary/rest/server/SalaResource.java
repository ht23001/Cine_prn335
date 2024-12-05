package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.rest.server;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.SalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sala;


import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("sala")
public class SalaResource implements Serializable {

    @Inject
    SalaBean salaBean;



    // Otros métodos de la clase...

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findRange(
            @QueryParam("first") @DefaultValue("0") int firstResult,
            @QueryParam("max") @DefaultValue("3") int maxResults) {
        try {
            if (firstResult >= 0 && maxResults > 0 && maxResults <= 50) {
                List<Sala> encontrados = salaBean.findRange(firstResult, maxResults);
                long total = salaBean.Count();
                Response.ResponseBuilder builder = Response.ok(encontrados)
                        .header("Total-Elements", total)
                        .type(MediaType.APPLICATION_JSON);
                return builder.build();
            } else {
                return Response.status(422)
                        .header("wrong-parameter", "first " + firstResult + " max " + maxResults)
                        .build();
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findById(@PathParam("id") Integer id) {
        if (id != null) {
            try {
                Sala encontrado = salaBean.findById(id);
                if (encontrado != null) {
                    Response.ResponseBuilder builder = Response.ok(encontrado)
                            .type(MediaType.APPLICATION_JSON);
                    return builder.build();
                }
                return Response.status(404).header("NOT-FOUND", "ID:" + id).build();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                return Response.status(500).entity(e.getMessage()).build();
            }
        }
        return Response.status(422).header("WRONG - PARAMETER", "ID:" + id).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(Sala sala, @Context UriInfo uriInfo) {
        if (sala != null && sala.getIdSala() == null) {
            try {
                salaBean.Create(sala);
                if (sala.getIdSala() != null) {

                    UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
                    uriBuilder.path(String.valueOf(sala.getIdSala()));
                    return Response.created(uriBuilder.build()).build();
                }
                return Response.status(500).header("PROCESS-ERROR", "RECORD COULDNT BE CREATED").build();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                return Response.status(500).entity(e.getMessage()).build();
            }
        }
        return Response.status(422).header("WRONG - PARAMETER", "Sala " + sala).build();
    }

    @PUT
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") Integer id, Sala sala) {
        if (sala != null && id != null) {
            try {
                sala.setIdSala(id);
                Sala actualizado = salaBean.Update(sala);
                if (actualizado != null) {
                    return Response.ok(actualizado).build();
                }
                return Response.status(404).header("NOT-FOUND", "ID:" + id).build();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                return Response.status(500).entity(e.getMessage()).build();
            }
        }
        return Response.status(422).header("WRONG - PARAMETER", "Sala " + sala).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") Integer id) {
        if (id != null) {
            try {
                salaBean.Delete(id);
                return Response.noContent().build();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                return Response.status(500).entity(e.getMessage()).build();
            }
        }
        return Response.status(422).header("WRONG - PARAMETER", "ID:" + id).build();
    }
}

