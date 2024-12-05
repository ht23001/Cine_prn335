package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.rest.server;



import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.AsientoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.ProductoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Asiento;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Producto;


import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



@Path("producto")
public class ProductoResource implements Serializable {

    @Inject
    ProductoBean productoBean;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findRange(
            @QueryParam("first")
            @DefaultValue("0") int firstResult,

            @QueryParam("max")
            @DefaultValue("10") int maxResults){

        try {
            if(firstResult >= 0 && maxResults > 0 && maxResults <= 50){
                List<Producto> encontrados = productoBean.findRange(firstResult, maxResults);
                long total = productoBean.Count();
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
                Producto encontrado = productoBean.findById(id);
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
    public Response create(Producto producto, @Context UriInfo uriInfo){
        if(producto != null && producto.getIdProducto() == null){
            try {
                productoBean.Create(producto);
                if(producto.getIdProducto() != null){
                    UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
                    uriBuilder.path(String.valueOf(producto.getIdProducto()));
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
                .header("WRONG - PARAMETER", "Producto " + producto)
                .build();
    }
}
