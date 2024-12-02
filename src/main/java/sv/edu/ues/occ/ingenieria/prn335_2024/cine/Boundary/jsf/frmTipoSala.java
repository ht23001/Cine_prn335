package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.TipoSalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoSala;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Named
@SessionScoped
public class frmTipoSala  extends AbstractPfFrm<TipoSala> {


    @Inject
    TipoSalaBean tsBean;

    @Override
    protected String getRowKeyImpl(TipoSala object) {
        return object != null && object.getIdTipoSala() != null ? object.getIdTipoSala().toString() : null;
    }

    @Override
    protected TipoSala getRowDataImpl(String rowKey) {
        if (rowKey == null) {
            return null;
        }
        Optional<TipoSala> result = registros.stream()
                .filter(r -> rowKey.equals(String.valueOf(r.getIdTipoSala())))
                .findFirst();
        return result.orElse(null);
    }

    @Override
    protected List<TipoSala> findRange(int desde, int max) {
        return tsBean.findRange(desde, max);
    }

    @Override
    protected long countImpl() {
        return tsBean.Count();
    }

    @Override
    protected Integer getId(TipoSala entity) {
        return entity.getIdTipoSala();
    }

    @Override
    protected TipoSala createNewInstance() {
        TipoSala nuevo = new TipoSala();
        nuevo.setActivoTipoSala(true);
        nuevo.setExpresionRegular(".");
        nuevo.setIdTipoSala(tsBean.obtenerMaxIdTipoSala(nuevo) + 1);
        return nuevo;
    }

    @Override
    protected void save(TipoSala entity) {
        tsBean.Create(entity);
    }

    @Override
    protected TipoSala update(TipoSala entity) {
        return tsBean.Update(entity);
    }

    @Override
    protected void delete(TipoSala entity) {
        tsBean.Delete(entity.getIdTipoSala());
    }





}
