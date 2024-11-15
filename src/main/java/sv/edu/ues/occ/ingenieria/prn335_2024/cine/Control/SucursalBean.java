package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sucursal;

import java.io.Serializable;

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


    public void create(Sucursal registro) {
        em.persist(registro);
    }
    public Sucursal update(Sucursal registro){

        return em.merge(registro);
    }

    public void delete(Sucursal registro) {
        em.remove(registro);
    }
}
