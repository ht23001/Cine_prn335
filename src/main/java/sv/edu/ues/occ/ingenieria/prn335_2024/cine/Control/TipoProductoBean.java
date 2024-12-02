package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.Local;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoPago;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoProducto;

import java.io.Serializable;
import java.util.List;

import static java.util.Collections.max;

@Stateless
@LocalBean
public class TipoProductoBean extends AbscractDataPersistence<TipoProducto> implements Serializable {

    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public TipoProductoBean(){
        super(TipoProducto.class);
    }

    @Override
    public EntityManager getEntityManager(){return em;}

    public Integer obtenerMaxIdTipoProducto(TipoProducto registro) {
        TypedQuery<Integer> query = em.createNamedQuery("TipoProducto.IdMaximo", Integer.class);
        Integer maxId = query.getSingleResult();
        return maxId;
    }

    // TODO ESTO ES DE REFACTORIZARLO, ESTOS METODOS DEBERIAN SER GENERALES EN LA CLASE ABSTRACTPERSITENCE


    /*
       public void create(TipoProducto registro) {
        em.persist(registro);
    }

    public TipoProducto update(TipoProducto registro){

        return em.merge(registro);
    }

    public long count() { return em.createQuery("SELECT COUNT(t) FROM TipoProducto t", Long.class).getSingleResult(); }

    public void delete(int idTipoProducto) {
        em.remove(em.find(TipoProducto.class, idTipoProducto));
    }

      public List<TipoProducto> findRange(int desde, int max) { return em.createQuery("SELECT t FROM TipoProducto t", TipoProducto.class) .setFirstResult(desde) .setMaxResults(max) .getResultList(); }

    public List<TipoProducto> findAll() { return em.createQuery("SELECT t FROM TipoProducto t", TipoProducto.class).getResultList(); }

     */




}
