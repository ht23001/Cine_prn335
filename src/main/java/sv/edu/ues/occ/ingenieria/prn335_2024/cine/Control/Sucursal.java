package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Pelicula;

import java.io.Serializable;
import java.util.List;


@Stateless
@LocalBean
public class Sucursal extends AbscractDataPersistence<Pelicula> implements Serializable {


    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public Sucursal(){
        super(Pelicula.class);
    }
    @Override
    public EntityManager getEntityManager(){return em;}

    public long count() { return em.createQuery("SELECT COUNT(p) FROM Pelicula p", Long.class).getSingleResult(); }

    public void create(Pelicula registro) {
        em.persist(registro);
    }
    public Pelicula update(Pelicula registro){

        return em.merge(registro);
    }

    public List<Pelicula> findAll() { return em.createQuery("SELECT p FROM Pelicula p", Pelicula.class).getResultList(); }

    public Integer obtenerMaxIdPelicula(Pelicula registro) { TypedQuery<Integer> query = em.createNamedQuery("Pelicula.IdMaximo", Integer.class); Integer maxId = query.getSingleResult(); return maxId; }

    public void delete(long idPelicula) {
        em.remove(em.find(Pelicula.class, idPelicula));
    }

}
