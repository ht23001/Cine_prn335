package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.jsf;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.TipoPagoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoPago;

import java.util.List;
import java.util.Optional;

@Named
@SessionScoped
public class frmTipoPago extends AbstractPfFrm<TipoPago> {

    @Inject
    TipoPagoBean tpBean;

    @Override
    protected String getRowKeyImpl(TipoPago object) {
        return object != null && object.getIdTipoPago() != null ? object.getIdTipoPago().toString() : null;
    }

    @Override
    protected TipoPago getRowDataImpl(String rowKey) {
        if (rowKey == null) {
            return null;
        }
        Optional<TipoPago> result = registros.stream()
                .filter(r -> rowKey.equals(String.valueOf(r.getIdTipoPago())))
                .findFirst();
        return result.orElse(null);
    }

    @Override
    protected List<TipoPago> findRange(int desde, int max) {
        return tpBean.findRange(desde, max);
    }

    @Override
    protected long countImpl() {
        return tpBean.Count();
    }

    @Override
    protected Integer getId(TipoPago entity) {
        return entity.getIdTipoPago();
    }

    @Override
    protected TipoPago createNewInstance() {
        TipoPago nuevo = new TipoPago();
        nuevo.setActivo(true);
        Integer maxId = tpBean.obtenerMaxIdTipoPago(nuevo);
        nuevo.setIdTipoPago(maxId != null ? maxId + 1 : 1);
        return nuevo;
    }

    @Override
    protected void save(TipoPago entity) {
        tpBean.Create(entity);
    }

    @Override
    protected TipoPago update(TipoPago entity) {
        return tpBean.Update(entity);
    }

    @Override
    protected void delete(TipoPago entity) {
        tpBean.Delete(entity.getIdTipoPago());
    }
}