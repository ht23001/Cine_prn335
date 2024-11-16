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
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.TipoPeliculaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoPelicula;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoSala;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@SessionScoped
public class frmTipoPelicula implements Serializable {

    @Inject
    TipoPeliculaBean tpBean;

    @Inject
    FacesContext facesContext;

    ESTADO_CRUD estado;

    List<TipoPelicula> registros;

    String expresionRegular; // Agregada propiedad faltante

    LazyDataModel<TipoPelicula> modelo;

    @PostConstruct
    public void init() {
        estado = ESTADO_CRUD.NINGUNO;
        modelo = new LazyDataModel<TipoPelicula>() {

            @Override
            public String getRowKey(TipoPelicula object) {
                if (object != null && object.getIdTipoPelicula() != null) {
                    return object.getIdTipoPelicula().toString();
                }
                return null;
            }

            @Override
            public TipoPelicula getRowData(String rowKey) {
                if (rowKey != null && getWrappedData() != null) {
                    return getWrappedData().stream().filter(r -> rowKey.equals(r.getIdTipoPelicula().toString())).findFirst().orElse(null);
                }
                return null;
            }

            @Override
            public List<TipoPelicula> getWrappedData() {
                return (List<TipoPelicula>) super.getWrappedData();
            }


            @Override
            public int count(Map<String, FilterMeta> map) {
                try {

                    return (int) tpBean.count();
                } catch (Exception e) {
                    e.printStackTrace();
                    ///TODO: Enviar mensaje de error de acceso
                }
                return 0;
            }

            @Override
            public List<TipoPelicula> load(int desde, int max, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {


                try {
                    if (desde >= 0 && max > 0) {
                        return tpBean.findRange(desde, max);

                    }

                } catch (Exception e) {

                    e.printStackTrace();
                    ///TODO: enviar mensaje de error en el repositorio
                }

                return List.of();


            }
        };
    }


    TipoPelicula registro;

    public LazyDataModel<TipoPelicula> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<TipoPelicula> modelo) {
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

    public void btnSeleccionarRegistroHandler(final Integer idTipoPelicula){
        if(idTipoPelicula !=null){
            this.registro=this.registros.stream().filter(r->idTipoPelicula.equals(r.getIdTipoPelicula())).findFirst().orElse(null);
            this.estado=ESTADO_CRUD.MODIFICAR;
            return;
        }
        this.registro=null;

    }

    public void btnCancelarHandler(ActionEvent actionEvent){

        this.registro = null;
        this.estado=ESTADO_CRUD.NINGUNO;
    }

    public void btnNuevoHandler(ActionEvent actionEvent){
        this.registro = new TipoPelicula();
        this.registro.setActivo(true);
        this.registro.setExpresionRegular(".");
        this.registro.setIdTipoPelicula(tpBean.obtenerMaxIdTipoPelicula(registro)+1);
        this.estado=ESTADO_CRUD.CREAR;
    }


    // Getters y Setters
    public List<TipoPelicula> getRegistros() {
        return registros;
    }

    public void setRegistros(List<TipoPelicula> registros) {
        this.registros = registros;
    }

    public Integer getSelecionado() {
        if (registro == null) {
            return null;
        }
        return registro.getIdTipoPelicula();
    }

    public void setSelecionado(Integer selecionado){
        this.registro = this.registros.stream()
                .filter(r -> r.getIdTipoPelicula() == selecionado).collect(Collectors.toList()).getFirst();
        if(this.registro!=null){
            this.estado=ESTADO_CRUD.MODIFICAR;
        }
    }

    public TipoPelicula getRegistro() {
        return registro;
    }

    public void setRegistro(TipoPelicula registro) {
        this.registro = registro;
    }

    public String getExpresionRegular() { // Getter para expresionRegular
        return expresionRegular;
    }

    public void setExpresionRegular(String expresionRegular) { // Setter para expresionRegular
        this.expresionRegular = expresionRegular;
    }


    public void btnGuardarHandler(ActionEvent actionEvent) {
        this.tpBean.create(registro);
        this.registro=null;
        this.registros= tpBean.findRange(0,1000000);
        this.estado=ESTADO_CRUD.NINGUNO;
        FacesMessage mensaje = new FacesMessage();
        mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
        mensaje.setSummary("Registro guardado con exito");
        facesContext.addMessage(null, mensaje);
    }

    public ESTADO_CRUD getEstado() {
        return estado;
    }

    public void btnModificarHandler(ActionEvent actionEvent) {
        TipoPelicula actualizado= tpBean.update(registro);
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
        tpBean.delete(registro.getIdTipoPelicula());
        mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
        mensaje.setSummary("Registro eliminado con exito");
        facesContext.addMessage(null, mensaje);
        this.registro=null;
        this.estado=ESTADO_CRUD.NINGUNO;
    }




}
