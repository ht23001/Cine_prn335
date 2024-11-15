package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Factura;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoReserva;


import java.io.Serializable;

@Stateless
@LocalBean

public class facturaBean extends AbscractDataPersistence<Factura> implements Serializable{

    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public facturaBean(){
        super(Factura.class);
    }
    @Override
    public EntityManager getEntityManager(){return em;}

    public void create(Factura registro) {
        em.persist(registro);
    }

    public Factura update(Factura registro){

        return em.merge(registro);
    }

    public void delete(Factura registro) {
        em.remove(registro);
    }

}
