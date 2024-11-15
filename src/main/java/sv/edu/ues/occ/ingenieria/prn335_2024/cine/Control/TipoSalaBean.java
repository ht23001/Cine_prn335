package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoSala;

import java.io.Serializable;

@Stateless
@LocalBean


/**Busca un Tiposala por su ID
 * @param idTiposala  Identificador de TipoSala
 * return nulo si no lo encuentra o el objeto encontrado
 * throws IllegalArgumentExepction si el ID pasado es nulo o menor que cero
 * throws IllegalStateException si el hay un problema con el repositorio
 */

public class TipoSalaBean extends AbscractDataPersistence<TipoSala> implements Serializable {

   @PersistenceContext(unitName = "CinePU")
   EntityManager em;

   public TipoSalaBean(){
       super(TipoSala.class);
   }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public void create(TipoSala registro) {
        em.persist(registro);
    }
    public TipoSala update(TipoSala registro){

        return em.merge(registro);
    }

    public void delete(TipoSala registro) {
        em.remove(registro);
    }
}
