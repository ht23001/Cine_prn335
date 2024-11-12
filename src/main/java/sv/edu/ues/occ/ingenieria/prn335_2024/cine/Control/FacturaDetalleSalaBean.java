package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.FacturaDetalleProducto;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.FacturaDetalleSala;

import java.io.Serializable;

@LocalBean
@Stateless
public class FacturaDetalleSalaBean extends AbscractDataPersistence<FacturaDetalleSala> implements Serializable {

    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public FacturaDetalleSalaBean(){
        super(FacturaDetalleProducto.class);
    }
    @Override
    public EntityManager getEntityManager(){return em;}
}
