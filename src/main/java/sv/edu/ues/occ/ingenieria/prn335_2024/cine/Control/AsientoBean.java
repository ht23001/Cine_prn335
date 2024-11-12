package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Asiento;

import java.io.Serializable;

@Stateless
@LocalBean

public class AsientoBean extends AbscractDataPersistence<Asiento> implements Serializable {


    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public AsientoBean(){
        super(Asiento.class);
    }
    @Override
    public EntityManager getEntityManager(){return em;}
    

}
