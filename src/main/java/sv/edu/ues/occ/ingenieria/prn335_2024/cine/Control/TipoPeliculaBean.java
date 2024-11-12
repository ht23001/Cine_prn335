package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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


}
