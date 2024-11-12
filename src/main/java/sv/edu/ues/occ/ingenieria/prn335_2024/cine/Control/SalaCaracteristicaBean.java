package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.ReservaDetalle;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.SalaCaracteristica;

import java.io.Serializable;


@LocalBean
@Stateless

public class SalaCaracteristicaBean extends AbscractDataPersistence<SalaCaracteristica> implements Serializable {

    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public SalaCaracteristicaBean(){
        super(SalaCaracteristica.class);
    }
    @Override
    public EntityManager getEntityManager(){return em;}
}
