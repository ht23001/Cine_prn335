package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.ReservaDetalle;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sucursal;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoProducto;

import java.io.Serializable;
import java.util.List;

@LocalBean
@Stateless
public class SucursalBean  extends AbscractDataPersistence<Sucursal> implements Serializable {
    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public SucursalBean(){
        super(Sucursal.class);
    }
    @Override
    public EntityManager getEntityManager(){return em;}

    public Integer obtenerMaxIdSucursal(Sucursal registro) {
        TypedQuery<Integer> query = em.createNamedQuery("Sucursal.IdMaximo", Integer.class);
        Integer maxId = query.getSingleResult();
        return maxId;
    }

    /*

    public List<Sucursal> findRange(int desde, int max) { return em.createQuery("SELECT su FROM Sucursal su", Sucursal.class) .setFirstResult(desde) .setMaxResults(max) .getResultList(); }


       public void create(Sucursal registro) {
        em.persist(registro);
    }

     public Sucursal update(Sucursal registro){

        return em.merge(registro);
    }
     public long count() { return em.createQuery("SELECT COUNT(su) FROM Sucursal su", Long.class).getSingleResult(); }

     */







    public List<Sucursal> findAll() { return em.createQuery("SELECT su FROM Sucursal su", Sucursal.class).getResultList(); }



}
