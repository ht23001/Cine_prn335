package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Pago;

import java.io.Serializable;


@LocalBean
@Stateless
public class PagoBean extends AbscractDataPersistence<Pago> implements Serializable {

    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public PagoBean(){
        super(Pago.class);
    }
    @Override
    public EntityManager getEntityManager(){return em;}
}
