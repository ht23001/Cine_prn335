package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.rest.server;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.SalaCaracteristicaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.SalaCaracteristica;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("salacaracteristica")
@Stateless
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class SalaCaracteristicaResource {

    @Inject
    private SalaCaracteristicaBean salaCaracteristicaBean;

    @GET
    public Response findRange(
            @QueryParam("first") @DefaultValue("0") int firstResult,
            @QueryParam("max") @DefaultValue("10") int maxResults) {
        try {
            if (firstResult >= 0 && maxResults > 0 && maxResults <= 50) {
                List<SalaCaracteristica> salaCaracteristicas = salaCaracteristicaBean.findRange(firstResult, maxResults);
                long total = salaCaracteristicaBean.Count();
                return Response.ok(salaCaracteristicas)
                        .header("Total-Elements", total)
                        .build();
            } else {
                return Response.status(422)
                        .header("WRONG-PARAMETER", "first=" + firstResult + ", max=" + maxResults)
                        .build();
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        if (id != null) {
            try {
                SalaCaracteristica salaCaracteristica = salaCaracteristicaBean.findById(id);
                if (salaCaracteristica != null) {
                    return Response.ok(salaCaracteristica).build();
                }
                return Response.status(404).header("NOT-FOUND", "ID:" + id).build();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                return Response.status(500).entity(e.getMessage()).build();
            }
        }
        return Response.status(422).header("WRONG-PARAMETER", "ID:" + id).build();
    }

    @POST
    public Response create(SalaCaracteristica salaCaracteristica, @Context UriInfo uriInfo) {
        if (salaCaracteristica != null && salaCaracteristica.getIdSalaCaracteristica() == null) {
            try {
                salaCaracteristicaBean.Create(salaCaracteristica);
                if (salaCaracteristica.getIdSalaCaracteristica() != null) {
                    UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
                    uriBuilder.path(String.valueOf(salaCaracteristica.getIdSalaCaracteristica()));
                    return Response.created(uriBuilder.build()).build();
                }
                return Response.status(500).header("PROCESS-ERROR", "RECORD COULD NOT BE CREATED").build();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                return Response.status(500).entity(e.getMessage()).build();
            }
        }
        return Response.status(422).header("WRONG-PARAMETER", "SalaCaracteristica: " + salaCaracteristica).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, SalaCaracteristica salaCaracteristica) {
        if (id != null && salaCaracteristica != null && salaCaracteristica.getIdSalaCaracteristica().equals(id)) {
            try {
                salaCaracteristicaBean.Update(salaCaracteristica);
                return Response.ok(salaCaracteristica).build();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                return Response.status(500).entity(e.getMessage()).build();
            }
        }
        return Response.status(422).header("WRONG-PARAMETER", "ID:" + id + ", SalaCaracteristica: " + salaCaracteristica).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        if (id != null) {
            try {
                salaCaracteristicaBean.Delete(id);
                return Response.noContent().build();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                return Response.status(500).entity(e.getMessage()).build();
            }
        }
        return Response.status(422).header("WRONG-PARAMETER", "ID:" + id).build();
    }
}
