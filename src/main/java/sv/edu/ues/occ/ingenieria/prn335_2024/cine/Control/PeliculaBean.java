package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;


import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Pelicula;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sucursal;

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


    public Integer obtenerMaxIdPelicula(Pelicula registro) {
        TypedQuery<Integer> query = em.createNamedQuery("Pelicula.IdMaximo", Integer.class);
        Integer maxId = query.getSingleResult();
        return maxId;
    }





}
