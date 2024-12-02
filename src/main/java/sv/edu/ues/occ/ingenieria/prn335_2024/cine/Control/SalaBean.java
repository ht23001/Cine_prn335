package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sala;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sucursal;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
@Stateless
@LocalBean

public class SalaBean extends AbscractDataPersistence<Sala> implements Serializable {

    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public SalaBean(){
        super(Sala.class);
    }


    @Override
    public EntityManager getEntityManager() {
        return em;
    }


   /*


       public List<Sala> findByTipoSala(Integer idTipoSala, int first, int max){

        if(idTipoSala!=null && first>=0 && max>0){
            try{
                if(em!=null){
                    Query q = em.createNamedQuery("Sala.findByIdTipoSala");
                    q.setParameter("idTipoSala",idTipoSala);
                    q.setFirstResult(first);
                    q.setMaxResults(max);
                    return q.getResultList();
                }
            }catch (Exception e){
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        }
        return Collections.emptyList();
    }
    public long count() {
        return em.createQuery("SELECT COUNT(t) FROM TipoSala t", Long.class).getSingleResult();
    }
    public void create(Sala registro) {
        em.persist(registro);
    }
    public Sala update(Sala registro){

        return em.merge(registro);
    }

     public void delete(int idSala) {
        em.remove(em.find(Sala.class, idSala));
    }

    public List<Sala> findRange(int desde, int max) {
        return em.createQuery("SELECT t FROM Sala t", Sala.class) .setFirstResult(desde) .setMaxResults(max) .getResultList();
    }

    */

    public Integer obtenerMaxIdSala(Sala registro) { TypedQuery<Integer> query = em.createNamedQuery("Sala.IdMaximo", Integer.class);
        Integer maxId = query.getSingleResult(); return maxId;
    }






}
