package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.TipoAsientoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.TipoPagoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoAsiento;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoReserva;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoSala;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Named
@SessionScoped
public class frmTipoAsiento implements Serializable {

    @Inject
    TipoAsientoBean taBean;

    @Inject
    FacesContext facesContext;

    ESTADO_CRUD estado;

    List<TipoAsiento> registros;

    String expresionRegular; // Agregada propiedad faltante

    LazyDataModel<TipoAsiento> modelo;

    @PostConstruct
    public void init() {
        estado = ESTADO_CRUD.MODIFICAR;
        registros = taBean.findRange(0, 1000000);
        modelo = new LazyDataModel<TipoAsiento>() {

            @Override
            public String getRowKey(TipoAsiento object) {
                if (object != null && object.getIdTipoAsiento() != null) {
                    return object.getIdTipoAsiento().toString();
                }
                return null;
            }

            @Override
            public TipoAsiento getRowData(String rowKey) {
                if (rowKey != null && getWrappedData() != null) {
                    return getWrappedData().stream().filter(r -> rowKey.equals(r.getIdTipoAsiento().toString())).findFirst().orElse(null);
                }
                return null;
            }


            @Override
            public List<TipoAsiento> getWrappedData() {
                return (List<TipoAsiento>) super.getWrappedData();
            }


            @Override
            public int count(Map<String, FilterMeta> map) {
                try {

                    return (int) taBean.count();
                } catch (Exception e) {
                    e.printStackTrace();
                    ///TODO: Enviar mensaje de error de acceso
                }
                return 0;
            }

            @Override
            public List<TipoAsiento> load(int desde, int max, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {


                try {
                    if (desde >= 0 && max > 0) {
                        return taBean.findRange(desde, max);

                    }

                } catch (Exception e) {

                    e.printStackTrace();
                    ///TODO: enviar mensaje de error en el repositorio
                }

                return List.of();


            }
        };
    }


    TipoAsiento registro;

    public LazyDataModel<TipoAsiento> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<TipoAsiento> modelo) {
        this.modelo = modelo;
    }

    public FacesContext getFacesContext() {
        return facesContext;
    }

    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }

    public void setEstado(ESTADO_CRUD estado) {
        this.estado = estado;
    }

    public List<TipoAsiento> getRegistros() {
        return registros;
    }

    public void setRegistros(List<TipoAsiento> registros) {
        this.registros = registros;
    }

    public TipoAsiento getRegistro() {
        return registro;
    }

    public void setRegistro(TipoAsiento registro) {
        this.registro = registro;

    }

    public void onRowSelect(SelectEvent<TipoAsiento> event) {
        registro = event.getObject();
        estado = ESTADO_CRUD.MODIFICAR;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fila Seleccionada", "ID: " + registro.getIdTipoAsiento())); }


    public String getExpresionRegular() { // Getter para expresionRegular
        return expresionRegular;
    }

    public void setExpresionRegular(String expresionRegular) { // Setter para expresionRegular
        this.expresionRegular = expresionRegular;
    }

    public void btnCancelarHandler(ActionEvent actionEvent){

        this.registro = null;
        this.estado=ESTADO_CRUD.NINGUNO;
    }

    public void btnNuevoHandler(ActionEvent actionEvent){
        this.registro = new TipoAsiento();
        if(taBean.obtenerMaxIdTipoAsiento(registro)==null){
            this.registro.setIdTipoAsiento(1);
            this.registro.setActivo(true);
            this.estado=ESTADO_CRUD.CREAR;
        }else{
            this.registro.setIdTipoAsiento(taBean.obtenerMaxIdTipoAsiento(registro)+1);
            this.registro.setActivo(true);
            this.estado=ESTADO_CRUD.CREAR;
        }


    }


    public void btnGuardarHandler(ActionEvent actionEvent) {
        this.taBean.create(registro);
        this.registro=null;
        this.registros= taBean.findRange(0,1000000);
        this.estado=ESTADO_CRUD.NINGUNO;
    }

    public ESTADO_CRUD getEstado() {
        return estado;
    }

    public void btnModificarHandler(ActionEvent actionEvent) {
        TipoAsiento actualizado= taBean.update(registro);
        FacesMessage mensaje= new FacesMessage();
        if(actualizado!=null){
            this.registro=null;
            this.estado=ESTADO_CRUD.NINGUNO;
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            mensaje.setSummary("Registro modificado con exito");

        }else {
            mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            mensaje.setSummary("NO se pudo guardar el registro, revise datos");

        }
        facesContext.addMessage(null, mensaje);
    }

    public void btnEliminarHandler(ActionEvent actionEvent){
        FacesMessage mensaje = new FacesMessage();
        taBean.delete(registro.getIdTipoAsiento());
        mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
        mensaje.setSummary("Registro eliminado con exito");
        facesContext.addMessage(null, mensaje);
        this.registro=null;
        this.estado=ESTADO_CRUD.NINGUNO;
    }
    
    



}
