package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.rest.server;

import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.FacturaDetalleProductoBean;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.PagoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.FacturaDetalleProducto;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Pago;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("facturadetalleproducto")
public class FacturaDetalleProductoResource implements Serializable {

    @Inject
    FacturaDetalleProductoBean facturaDetalleProductoBean;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findRange(
            @QueryParam("first")
            @DefaultValue("0") int firstResult,

            @QueryParam("max")
            @DefaultValue("10") int maxResults){

        try {
            if(firstResult >= 0 && maxResults > 0 && maxResults <= 50){
                List<FacturaDetalleProducto> encontrados = facturaDetalleProductoBean.findRange(firstResult, maxResults);
                long total = facturaDetalleProductoBean.Count();
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
    public Response findById(@PathParam("id") Long id){
        if(id != null){
            try {
                FacturaDetalleProducto encontrado = facturaDetalleProductoBean.findById(id);
                if(encontrado != null){
                    Response.ResponseBuilder builder = Response.ok(encontrado)
                            .type(MediaType.APPLICATION_JSON);
                    return builder.build();
                } else {
                    return Response.status(404)
                            .header("NOT-FOUND", "ID: " + id)
                            .build();
                }
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                return Response.status(500).entity(e.getMessage()).build();
            }
        }
        return Response.status(422)
                .header("WRONG - PARAMETER", "ID: " + id)
                .build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(FacturaDetalleProducto facturaDetalleProducto, @Context UriInfo uriInfo){
        if(facturaDetalleProducto != null && facturaDetalleProducto.getIdFacturaDetalleProducto() == null){
            try {
                facturaDetalleProductoBean.Create(facturaDetalleProducto);
                if(facturaDetalleProducto.getIdFacturaDetalleProducto() != null){
                    UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
                    uriBuilder.path(String.valueOf(facturaDetalleProducto.getIdFacturaDetalleProducto()));
                    return Response.created(uriBuilder.build()).build();
                } else {
                    return Response.status(500)
                            .header("PROCESS-ERROR", "RECORD COULDNT BE CREATED")
                            .build();
                }
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                return Response.status(500).entity(e.getMessage()).build();
            }
        }
        return Response.status(422)
                .header("WRONG - PARAMETER", "FacturaDetalleProducto " + facturaDetalleProducto)
                .build();
    }
}
