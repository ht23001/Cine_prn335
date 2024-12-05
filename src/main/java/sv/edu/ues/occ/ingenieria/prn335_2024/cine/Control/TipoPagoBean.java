package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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



    public Integer obtenerMaxIdTipoPago(TipoPago registro) {
        TypedQuery<Integer> query = em.createNamedQuery("TipoPago.IdMaximo", Integer.class);
        Integer maxId = query.getSingleResult(); return maxId;
    }

    // TODO ESTO ES DE REFACTORIZARLO, ESTOS METODOS DEBERIAN SER GENERALES EN LA CLASE ABSTRACTPERSITENCE

    /*

    public void create(TipoPago registro) {
        em.persist(registro);
    }

    public long count() { return em.createQuery("SELECT COUNT(t) FROM TipoPago t", Long.class).getSingleResult(); }

      public void delete(int idTipoPago) {
        em.remove(em.find(TipoPago.class, idTipoPago));
    }

      public TipoPago update(TipoPago registro){

        return em.merge(registro);
    }



    public List<TipoPago> findRange(int desde, int max) { return em.createQuery("SELECT t FROM TipoPago t", TipoPago.class) .setFirstResult(desde) .setMaxResults(max) .getResultList(); }

    public List<TipoPago> findRangeSorted(int desde, int max, String sortField, boolean ascending) {
        String order = ascending ? "ASC" : "DESC";
        return em.createQuery("SELECT t FROM TipoPago t ORDER BY t." + sortField + " " + order, TipoPago.class) .setFirstResult(desde) .setMaxResults(max) .getResultList();
    }

    public List<TipoPago> findAll() { return em.createQuery("SELECT t FROM TipoPago t", TipoPago.class).getResultList(); }



     */




}

