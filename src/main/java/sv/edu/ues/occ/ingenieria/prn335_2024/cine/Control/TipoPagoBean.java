package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoPago;
import java.io.Serializable;

@LocalBean
@Stateless

public class TipoPagoBean extends AbscractDataPersistence<TipoPago> implements Serializable {

    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public TipoPagoBean(){
        super(TipoPago.class);
    }

    @Override
    public EntityManager getEntityManager(){return em;}

}
