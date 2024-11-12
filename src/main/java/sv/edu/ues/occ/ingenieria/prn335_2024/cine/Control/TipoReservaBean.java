package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.PeliculaCaracteristica;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoReserva;


import java.io.Serializable;

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



}
