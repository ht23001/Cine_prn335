package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Inject;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class AbstractPfFrm<T> implements Serializable {

    @Inject
    protected FacesContext facesContext;

    protected LazyDataModel<T> modelo;
    protected List<T> registros;
    protected T registro;
    protected ESTADO_CRUD estado;

    @PostConstruct
    public void init() {
        estado = ESTADO_CRUD.NINGUNO;
        registros = findRange(0, 1000000);
        modelo = new LazyDataModel<T>() {

            @Override
            public String getRowKey(T object) {
                return getRowKeyImpl(object);
            }

            @Override
            public T getRowData(String rowKey) {
                return getRowDataImpl(rowKey);
            }

            @Override
            public List<T> getWrappedData() {
                return (List<T>) super.getWrappedData();
            }

            @Override
            public int count(Map<String, FilterMeta> map) {
                return (int) countImpl();
            }

            @Override
            public List<T> load(int desde, int max, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {
                return findRange(desde, max);
            }
        };
    }

    protected abstract String getRowKeyImpl(T object);
    protected abstract T getRowDataImpl(String rowKey);
    protected abstract List<T> findRange(int desde, int max);
    protected abstract long countImpl();

    public LazyDataModel<T> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<T> modelo) {
        this.modelo = modelo;
    }

    public FacesContext getFacesContext() {
        return facesContext;
    }

    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }

    public List<T> getRegistros() {
        return registros;
    }

    public void setRegistros(List<T> registros) {
        this.registros = registros;
    }

    public T getRegistro() {
        return registro;
    }

    public void setRegistro(T registro) {
        this.registro = registro;
    }

    public ESTADO_CRUD getEstado() {
        return estado;
    }

    public void setEstado(ESTADO_CRUD estado) {
        this.estado = estado;
    }

    public void btnSeleccionarRegistroHandler(final Integer id) {
        registro = registros.stream().filter(r -> getId(r).equals(id)).findFirst().orElse(null);
        estado = (registro != null) ? ESTADO_CRUD.MODIFICAR : ESTADO_CRUD.NINGUNO;
    }

    protected abstract Integer getId(T entity);

    public void btnCancelarHandler(ActionEvent actionEvent) {
        this.registro = null;
        this.estado = ESTADO_CRUD.NINGUNO;
    }

    public void btnNuevoHandler(ActionEvent actionEvent) {
        this.registro = createNewInstance();
        this.estado = ESTADO_CRUD.CREAR;
    }

    protected abstract T createNewInstance();

    public void btnGuardarHandler(ActionEvent actionEvent) {
        save(registro);
        registros = findRange(0, 1000000);
        this.registro = null;
        this.estado = ESTADO_CRUD.NINGUNO;
        facesContext.addMessage(null, new FacesMessage("Registro guardado con éxito"));
    }

    protected abstract void save(T entity);

    public void btnModificarHandler(ActionEvent actionEvent) {
        T actualizado = update(registro);
        FacesMessage mensaje = new FacesMessage();
        if (actualizado != null) {
            this.registro = null;
            this.estado = ESTADO_CRUD.NINGUNO;
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            mensaje.setSummary("Registro modificado con éxito");
        } else {
            mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            mensaje.setSummary("No se pudo guardar el registro, revise datos");
        }
        facesContext.addMessage(null, mensaje);
    }

    protected abstract T update(T entity);

    public void btnEliminarHandler(ActionEvent actionEvent) {
        delete(registro);
        registros = findRange(0, 1000000);
        FacesMessage mensaje = new FacesMessage();
        mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
        mensaje.setSummary("Registro eliminado con éxito");
        facesContext.addMessage(null, mensaje);
        this.registro = null;
        this.estado = ESTADO_CRUD.NINGUNO;
    }

    protected abstract void delete(T entity);

}
