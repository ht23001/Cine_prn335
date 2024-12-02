package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoAsiento;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoReserva;

import java.io.Serializable;
import java.util.List;


@Stateless
@LocalBean

public class TipoAsientoBean extends AbscractDataPersistence<TipoAsiento> implements Serializable {

    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public TipoAsientoBean(){
        super(TipoAsiento.class);
    }

    @Override
    public EntityManager getEntityManager(){return em;}

    // TODO ESTO ES DE REFACTORIZARLO, ESTOS METODOS DEBERIAN SER GENERALES EN LA CLASE ABSTRACTPERSITENCE




    /*




    public long count() { return em.createQuery("SELECT COUNT(ta) FROM TipoAsiento ta", Long.class).getSingleResult(); }

    public void create(TipoAsiento registro) {
        em.persist(registro);
    }
    public TipoAsiento update(TipoAsiento registro){

        return em.merge(registro);
    }

    public List<TipoAsiento> findAll() { return em.createQuery("SELECT ta FROM TipoAsiento ta", TipoAsiento.class).getResultList(); }

    public void delete(int id) {
        em.remove(em.find(TipoAsiento.class, id));
    }

     */

    public Integer obtenerMaxIdTipoAsiento(TipoAsiento registro) {
        TypedQuery<Integer> query = em.createNamedQuery("TipoAsiento.IdMaximo", Integer.class);
        return query.getSingleResult();
    }

}
