package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.ReservaDetalle;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sala;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.SalaCaracteristica;

import java.io.Serializable;
import java.util.List;


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


    public List<SalaCaracteristica> findBySala(Sala sala) {
        TypedQuery<SalaCaracteristica> query = em.createQuery("SELECT sc FROM SalaCaracteristica sc WHERE sc.idSala = :sala", SalaCaracteristica.class);
        query.setParameter("sala", sala);
        return query.getResultList();
    }


}
