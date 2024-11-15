package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sala;

import java.io.Serializable;


@LocalBean
@Stateless
public class SalaBean extends AbscractDataPersistence<Sala> implements Serializable {

    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public SalaBean(){
        super(Sala.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return null;
    }

    public void create(Sala registro) {
        em.persist(registro);
    }
    public Sala update(Sala registro){

        return em.merge(registro);
    }

    public void delete(Sala registro) {
        em.remove(registro);
    }


}
