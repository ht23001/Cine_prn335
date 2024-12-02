package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.jsf;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.TipoAsientoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoAsiento;

import java.util.List;
import java.util.Optional;

@Named
@SessionScoped
public class frmTipoAsiento extends AbstractPfFrm<TipoAsiento> {

    @Inject
    TipoAsientoBean taBean;

    @Override
    protected String getRowKeyImpl(TipoAsiento object) {
        return object != null && object.getIdTipoAsiento() != null ? object.getIdTipoAsiento().toString() : null;
    }

    @Override
    protected TipoAsiento getRowDataImpl(String rowKey) {
        if (rowKey == null) {
            return null;
        }
        Optional<TipoAsiento> result = registros.stream()
                .filter(r -> rowKey.equals(String.valueOf(r.getIdTipoAsiento())))
                .findFirst();
        return result.orElse(null);
    }

    @Override
    protected List<TipoAsiento> findRange(int desde, int max) {
        return taBean.findRange(desde, max);
    }

    @Override
    protected long countImpl() {
        return taBean.Count();
    }

    @Override
    protected Integer getId(TipoAsiento entity) {
        return entity.getIdTipoAsiento();
    }

    @Override
    protected TipoAsiento createNewInstance() {
        TipoAsiento nuevo = new TipoAsiento();
        nuevo.setActivo(true);
        Integer maxId = taBean.obtenerMaxIdTipoAsiento(nuevo);
        nuevo.setIdTipoAsiento(maxId != null ? maxId + 1 : 1);
        return nuevo;
    }

    @Override
    protected void save(TipoAsiento entity) {
        taBean.Create(entity);
    }

    @Override
    protected TipoAsiento update(TipoAsiento entity) {
        return taBean.Update(entity);
    }

    @Override
    protected void delete(TipoAsiento entity) {
        taBean.Delete(entity.getIdTipoAsiento());
    }
}