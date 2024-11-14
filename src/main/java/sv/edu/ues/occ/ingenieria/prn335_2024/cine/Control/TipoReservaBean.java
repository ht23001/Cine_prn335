package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.PeliculaCaracteristica;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoReserva;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoSala;


import java.io.Serializable;
import java.util.List;

@Stateless
@LocalBean

public class TipoReservaBean extends AbscractDataPersistence<TipoReserva> implements Serializable{


    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public TipoReservaBean(){
        super(TipoReserva.class);
    }
    @Override
    public EntityManager getEntityManager(){return em;}



    public long count() { return em.createQuery("SELECT COUNT(t) FROM TipoReserva t", Long.class).getSingleResult(); }

    public void create(TipoReserva registro) {
        em.persist(registro);
    }
    public TipoReserva update(TipoReserva registro){

        return em.merge(registro);
    }

    public List<TipoReserva> findAll() { return em.createQuery("SELECT t FROM TipoReserva t", TipoReserva.class).getResultList(); }

    public Integer obtenerMaxIdTipoReserva() { TypedQuery<Integer> query = em.createNamedQuery("TipoReserva.IdMaximo", Integer.class); Integer maxId = query.getSingleResult(); return maxId; }

    public void delete(int idTipoReserva) {
        em.remove(em.find(TipoSala.class, idTipoReserva));
    }
}


