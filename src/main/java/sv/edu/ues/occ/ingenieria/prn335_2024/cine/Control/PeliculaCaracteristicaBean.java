package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.PeliculaCaracteristica;

import java.io.Serializable;

@LocalBean
@Stateless
public class PeliculaCaracteristicaBean extends AbscractDataPersistence<PeliculaCaracteristica> implements Serializable {

    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public PeliculaCaracteristicaBean(){
        super(PeliculaCaracteristica.class);
    }
    @Override
    public EntityManager getEntityManager(){return em;}
}
