package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Pelicula;

import java.io.Serializable;

@LocalBean
@Stateless

public class PeliculaBean extends AbscractDataPersistence<Pelicula> implements Serializable {

    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public PeliculaBean(){
        super(Pelicula.class);
    }
    @Override
    public EntityManager getEntityManager(){return em;}

    public void create(Pelicula registro) {
        em.persist(registro);
    }
    public Pelicula update(Pelicula registro){

        return em.merge(registro);
    }

    public void delete(Pelicula registro) {
        em.remove(registro);
    }
}
