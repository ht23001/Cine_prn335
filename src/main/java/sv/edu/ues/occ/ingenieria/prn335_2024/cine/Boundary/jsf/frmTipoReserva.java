package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.jsf;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.TipoReservaBean;

import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoReserva;


import java.util.List;
import java.util.Optional;

@Named
@SessionScoped
public class frmTipoReserva extends AbstractPfFrm<TipoReserva>  {
    @Inject
    TipoReservaBean dataBean;

    @Override
    protected String getRowKeyImpl(TipoReserva object) {
        return object != null && object.getIdTipoReserva() != null ? object.getIdTipoReserva().toString() : null;
    }

    @Override
    protected TipoReserva getRowDataImpl(String rowKey) {
        if (rowKey == null) {
            return null;
        }
        Optional<TipoReserva> result = registros.stream()
                .filter(r -> rowKey.equals(String.valueOf(r.getIdTipoReserva())))
                .findFirst();
        return result.orElse(null);
    }

    @Override
    protected List<TipoReserva> findRange(int desde, int max) {
        return dataBean.findRange(desde, max);
    }

    @Override
    protected long countImpl() {
        return dataBean.Count();
    }

    @Override
    protected Integer getId(TipoReserva entity) {
        return entity.getIdTipoReserva();
    }

    @Override
    protected TipoReserva createNewInstance() {
        TipoReserva nuevo = new TipoReserva();
        nuevo.setActivo(true);
        nuevo.setIdTipoReserva(dataBean.obtenerMaxIdTipoReserva(nuevo) + 1);
        return nuevo;
    }

    @Override
    protected void save(TipoReserva entity) {
        dataBean.Create(entity);
    }

    @Override
    protected TipoReserva update(TipoReserva entity) {
        return dataBean.Update(entity);
    }

    @Override
    protected void delete(TipoReserva entity) {
        dataBean.Delete(entity.getIdTipoReserva());
    }

}
