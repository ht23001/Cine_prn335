package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Asiento;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.FacturaDetalleProducto;

import java.io.Serializable;

@LocalBean
@Stateless

public class FacturaDetalleProductoBean extends AbscractDataPersistence<FacturaDetalleProducto> implements Serializable {

    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public FacturaDetalleProductoBean(){
        super(FacturaDetalleProducto.class);
    }
    @Override
    public EntityManager getEntityManager(){return em;}
}
