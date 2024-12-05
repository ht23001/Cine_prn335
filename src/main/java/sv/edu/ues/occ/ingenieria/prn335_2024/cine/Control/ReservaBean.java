package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Reserva;

import java.io.Serializable;


@Stateless
@LocalBean

public class ReservaBean extends AbscractDataPersistence<Reserva> implements Serializable {

    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public ReservaBean(){
        super(Reserva.class);
    }
    @Override
    public EntityManager getEntityManager(){return em;}
}
