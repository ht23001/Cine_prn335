package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoPelicula;

import java.io.Serializable;

@LocalBean
@Stateless
public class TipoPeliculaBean extends AbscractDataPersistence<TipoPelicula> implements Serializable {

    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public TipoPeliculaBean(){
        super(TipoPelicula.class);
    }

    @Override
    public EntityManager getEntityManager(){return em;}

    public Integer obtenerMaxIdTipoPelicula(TipoPelicula registro) {
        TypedQuery<Integer> query = em.createNamedQuery("TipoPelicula.IdMaximo", Integer.class);
        Integer maxId = query.getSingleResult(); return maxId;
    }



    // TODO ESTO ES DE REFACTORIZARLO, ESTOS METODOS DEBERIAN SER GENERALES EN LA CLASE ABSTRACTPERSITENCE


    /*
      public void create(TipoPelicula registro) {
        em.persist(registro);
    }

    public long count() { return em.createQuery("SELECT COUNT(t) FROM TipoPelicula t", Long.class).getSingleResult(); }

     public TipoPelicula update(TipoPelicula registro){

        return em.merge(registro);
    }

    public List<TipoPelicula> findAll() { return em.createQuery("SELECT t FROM TipoPelicula t", TipoPelicula.class).getResultList(); }

    public void delete(int idTipoPelicula) {
        em.remove(em.find(TipoPelicula.class, idTipoPelicula));
    }

    public List<TipoPelicula> findRange(int desde, int max) { return em.createQuery("SELECT tp FROM TipoPelicula tp", TipoPelicula.class) .setFirstResult(desde) .setMaxResults(max) .getResultList(); }


     */





}
