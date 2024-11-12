package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.Local;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoPago;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoProducto;

import java.io.Serializable;

@LocalBean
@Stateless
public class TipoProductoBean extends AbscractDataPersistence<TipoProducto> implements Serializable {

    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public TipoProductoBean(){
        super(TipoProducto.class);
    }

    @Override
    public EntityManager getEntityManager(){return em;}
}
