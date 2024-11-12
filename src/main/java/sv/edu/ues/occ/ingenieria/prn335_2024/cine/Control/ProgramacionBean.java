package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Programacion;

import java.io.Serializable;

@LocalBean
@Stateless

public class ProgramacionBean extends AbscractDataPersistence<Programacion> implements Serializable {

    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public ProgramacionBean(){
        super(Programacion.class);
    }
    @Override
    public EntityManager getEntityManager(){return em;}
}

