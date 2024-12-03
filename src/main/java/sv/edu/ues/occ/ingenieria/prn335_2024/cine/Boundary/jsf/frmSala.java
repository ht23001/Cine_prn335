package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.SalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.SalaCaracteristicaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sala;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.SalaCaracteristica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class frmSala extends AbstractPfFrm<Sala> implements Serializable {

    @Inject
    SalaBean sBean;
    @Inject
    SalaCaracteristicaBean scBean;

    private List<SalaCaracteristica> salaCaracteristicas;  // Lista de características de la sala seleccionada

    @PostConstruct
    public void init() {
        super.init();
        salaCaracteristicas = new ArrayList<>();
    }

    @Override
    protected String getRowKeyImpl(Sala object) {
        return object != null && object.getIdSala() != null ? object.getIdSala().toString() : null;
    }

    @Override
    protected Sala getRowDataImpl(String rowKey) {
        return registros.stream()
                .filter(r -> rowKey.equals(r.getIdSala().toString()))
                .findFirst()
                .orElse(null);
    }

    @Override
    protected List<Sala> findRange(int desde, int max) {
        return sBean.findRange(desde, max);
    }

    @Override
    protected long countImpl() {
        return sBean.Count();
    }

    @Override
    protected Integer getId(Sala entity) {
        return entity.getIdSala();
    }

    @Override
    protected Sala createNewInstance() {
        Sala sala = new Sala();
        sala.setActivo(true);
        sala.setIdSala(sBean.obtenerMaxIdSala(sala) + 1);
        return sala;
    }

    @Override
    protected void save(Sala entity) {
        try {
            sBean.Create(entity);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro guardado con éxito", ""));
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar el registro"));
            e.printStackTrace();
        }
    }

    @Override
    protected Sala update(Sala entity) {
        return sBean.Update(entity);
    }

    @Override
    protected void delete(Sala entity) {
        try {
            sBean.Delete(entity.getIdSala());
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado con éxito", ""));
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el registro"));
            e.printStackTrace();
        }
    }

    public void btnNuevoHandler(ActionEvent actionEvent) {
        super.btnNuevoHandler(actionEvent);
    }

    public void btnCancelarHandler(ActionEvent actionEvent) {
        super.btnCancelarHandler(actionEvent);
    }

    public void btnModificarHandler(ActionEvent actionEvent) {
        super.btnModificarHandler(actionEvent);
    }

    public void btnEliminarHandler(ActionEvent actionEvent) {
        super.btnEliminarHandler(actionEvent);
    }

    // Método que se llama cuando se selecciona una sala
    public void cargarCaracteristicasSala() {
        if (registro != null) {
            salaCaracteristicas = scBean.findBySala(registro);
        }
    }

    public List<SalaCaracteristica> getSalaCaracteristicas() {
        return salaCaracteristicas;
    }

}


