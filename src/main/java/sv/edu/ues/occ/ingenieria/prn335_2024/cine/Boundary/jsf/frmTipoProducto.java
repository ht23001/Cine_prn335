package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.jsf;


import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.TipoProductoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoProducto;

import java.util.List;
import java.util.Optional;

@Named
@SessionScoped
public class frmTipoProducto extends AbstractPfFrm<TipoProducto> {


    @Inject
    TipoProductoBean tpBean;

    @Override
    protected String getRowKeyImpl(TipoProducto object) {
        return object != null && object.getIdTipoProducto() != null ? object.getIdTipoProducto().toString() : null;
    }

    @Override
    protected TipoProducto getRowDataImpl(String rowKey) {
        if (rowKey == null) {
            return null;
        }
        Optional<TipoProducto> result = registros.stream()
                .filter(r -> rowKey.equals(String.valueOf(r.getIdTipoProducto())))
                .findFirst();
        return result.orElse(null);
    }

    @Override
    protected List<TipoProducto> findRange(int desde, int max) {
        return tpBean.findRange(desde, max);
    }

    @Override
    protected long countImpl() {
        return tpBean.Count();
    }

    @Override
    protected Integer getId(TipoProducto entity) {
        return entity.getIdTipoProducto();
    }

    @Override
    protected TipoProducto createNewInstance() {
        TipoProducto nuevo = new TipoProducto();
        nuevo.setActivo(true);
        Integer maxId = tpBean.obtenerMaxIdTipoProducto(nuevo);
        nuevo.setIdTipoProducto(maxId != null ? maxId + 1 : 1);
        return nuevo;
    }

    @Override
    protected void save(TipoProducto entity) {
        tpBean.Create(entity);
    }

    @Override
    protected TipoProducto update(TipoProducto entity) {
        return tpBean.Update(entity);
    }

    @Override
    protected void delete(TipoProducto entity) {
        tpBean.Delete(entity.getIdTipoProducto());
    }



}
