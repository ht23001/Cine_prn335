package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.jsf;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.TipoPeliculaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoPelicula;

import java.util.List;
import java.util.Optional;

@Named
@SessionScoped
public class frmTipoPelicula extends AbstractPfFrm<TipoPelicula> {

    @Inject
    TipoPeliculaBean tpBean;

    @Override
    protected String getRowKeyImpl(TipoPelicula object) {
        return object != null && object.getIdTipoPelicula() != null ? object.getIdTipoPelicula().toString() : null;
    }

    @Override
    protected TipoPelicula getRowDataImpl(String rowKey) {
        if (rowKey == null) {
            return null;
        }
        Optional<TipoPelicula> result = registros.stream()
                .filter(r -> rowKey.equals(String.valueOf(r.getIdTipoPelicula())))
                .findFirst();
        return result.orElse(null);
    }

    @Override
    protected List<TipoPelicula> findRange(int desde, int max) {
        return tpBean.findRange(desde, max);
    }

    @Override
    protected long countImpl() {
        return tpBean.count();
    }

    @Override
    protected Integer getId(TipoPelicula entity) {
        return entity.getIdTipoPelicula();
    }

    @Override
    protected TipoPelicula createNewInstance() {
        TipoPelicula nuevo = new TipoPelicula();
        nuevo.setActivo(true);
        nuevo.setExpresionRegular(".");
        nuevo.setIdTipoPelicula(tpBean.obtenerMaxIdTipoPelicula(nuevo) + 1);
        return nuevo;
    }

    @Override
    protected void save(TipoPelicula entity) {
        tpBean.create(entity);
    }

    @Override
    protected TipoPelicula update(TipoPelicula entity) {
        return tpBean.update(entity);
    }

    @Override
    protected void delete(TipoPelicula entity) {
        tpBean.delete(entity.getIdTipoPelicula());
    }
}
