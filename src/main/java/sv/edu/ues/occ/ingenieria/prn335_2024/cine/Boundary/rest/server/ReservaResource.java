package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.rest.server;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.ReservaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Reserva;

import java.util.List;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("reserva")
public class ReservaResource implements Serializable {

    @Inject
    ReservaBean reservaBean;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findRange(
            @QueryParam("first") @DefaultValue("0") int firstResult,
            @QueryParam("max") @DefaultValue("3") int maxResults) {
        try {
            if (firstResult >= 0 && maxResults > 0 && maxResults <= 50) {
                List<Reserva> encontrados = reservaBean.findRange(firstResult, maxResults);
                long total = reservaBean.Count();
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
    public Response findById(@PathParam("id") Long id) {
        if (id != null) {
            try {
                Reserva encontrado = reservaBean.findById(id);
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
    public Response create(Reserva reserva, @Context UriInfo uriInfo) {
        if (reserva != null && reserva.getId() == null) {
            try {
                reservaBean.Create(reserva);
                if (reserva.getId() != null) {
                    UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
                    uriBuilder.path(String.valueOf(reserva.getId()));
                    return Response.created(uriBuilder.build()).build();
                }
                return Response.status(500).header("PROCESS-ERROR", "RECORD COULDNT BE CREATED").build();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                return Response.status(500).entity(e.getMessage()).build();
            }
        }
        return Response.status(422).header("WRONG - PARAMETER", "Reserva " + reserva).build();
    }

    @PUT
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") Long id, Reserva reserva) {
        if (reserva != null && id != null) {
            try {
                reserva.setId(id);
                Reserva actualizado = reservaBean.Update(reserva);
                if (actualizado != null) {
                    return Response.ok(actualizado).build();
                }
                return Response.status(404).header("NOT-FOUND", "ID:" + id).build();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                return Response.status(500).entity(e.getMessage()).build();
            }
        }
        return Response.status(422).header("WRONG - PARAMETER", "Reserva " + reserva).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") Long id) {
        if (id != null) {
            try {
                reservaBean.Delete(id);
                return Response.noContent().build();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                return Response.status(500).entity(e.getMessage()).build();
            }
        }
        return Response.status(422).header("WRONG - PARAMETER", "ID:" + id).build();
    }
}
