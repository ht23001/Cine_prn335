package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.ReservaDetalle;

import java.io.Serializable;

public class ReservaDetalleBean extends AbscractDataPersistence<ReservaDetalle> implements Serializable {


    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public ReservaDetalleBean(){
        super(ReservaDetalle.class);
    }
    @Override
    public EntityManager getEntityManager(){return em;}
}


