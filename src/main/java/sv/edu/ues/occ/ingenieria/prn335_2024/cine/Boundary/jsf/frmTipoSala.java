package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.TipoSalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoReserva;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoSala;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@SessionScoped
public class frmTipoSala implements Serializable {

    @Inject
    TipoSalaBean tsBean;

    @Inject
    FacesContext facesContext;

    ESTADO_CRUD estado;

    List<TipoSala> registros;

     String expresionRegular; // Agregada propiedad faltante

    LazyDataModel<TipoSala> modelo;

     @PostConstruct
    public void init() {
         estado = ESTADO_CRUD.NINGUNO;
         registros = tsBean.findRange(0, 1000000);
         modelo = new LazyDataModel<TipoSala>() {

             @Override
             public String getRowKey(TipoSala object) {
                 if (object != null && object.getIdTipoSala() != null) {
                     return object.getIdTipoSala().toString();
                 }
                 return null;
             }

             @Override
             public TipoSala getRowData(String rowKey) {
                 if (rowKey != null && getWrappedData() != null) {
                     return getWrappedData().stream().filter(r -> rowKey.equals(r.getIdTipoSala().toString())).findFirst().orElse(null);
                 }
                 return null;
             }

             @Override
             public List<TipoSala> getWrappedData() {
                 return (List<TipoSala>) super.getWrappedData();
             }


             @Override
             public int count(Map<String, FilterMeta> map) {
                 try {

                     return (int) tsBean.count();
                 } catch (Exception e) {
                     e.printStackTrace();
                     ///TODO: Enviar mensaje de error de acceso
                 }
                 return 0;
             }

             @Override
             public List<TipoSala> load(int desde, int max, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {


                 try {
                     if (desde >= 0 && max > 0) {
                         return tsBean.findRange(desde, max);

                     }

                 } catch (Exception e) {

                     e.printStackTrace();
                     ///TODO: enviar mensaje de error en el repositorio
                 }

                 return List.of();


             }
         };
     }


    TipoSala registro;

    public LazyDataModel<TipoSala> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<TipoSala> modelo) {
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

    public void btnSeleccionarRegistroHandler(final Integer idTipoSala){
        if(idTipoSala !=null){
            this.registro=this.registros.stream().filter(r->idTipoSala.equals(r.getIdTipoSala())).findFirst().orElse(null);
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
        this.registro = new TipoSala();
        this.registro.setActivoTipoSala(true);
        this.registro.setExpresionRegular(".");
        this.registro.setIdTipoSala(tsBean.obtenerMaxIdTipoSala(registro)+1);
        this.estado=ESTADO_CRUD.CREAR;
    }


    // Getters y Setters
    public List<TipoSala> getRegistros() {
        return registros;
    }

    public void setRegistros(List<TipoSala> registros) {
        this.registros = registros;
    }

    public Integer getSelecionado() {
        if (registro == null) {
            return null;
        }
        return registro.getIdTipoSala();
    }

    public void setSelecionado(Integer selecionado){
        this.registro = this.registros.stream()
                .filter(r -> r.getIdTipoSala() == selecionado).collect(Collectors.toList()).getFirst();
             if(this.registro!=null){
                 this.estado=ESTADO_CRUD.MODIFICAR;
             }
    }

    public TipoSala getRegistro() {
        return registro;
    }

    public void setRegistro(TipoSala registro) {
        this.registro = registro;
    }

    public String getExpresionRegular() { // Getter para expresionRegular
        return expresionRegular;
    }

    public void setExpresionRegular(String expresionRegular) { // Setter para expresionRegular
        this.expresionRegular = expresionRegular;
    }


    public void btnGuardarHandler(ActionEvent actionEvent) {
       this.tsBean.create(registro);
        this.registro=null;
        this.registros=tsBean.findRange(0,1000000);
        this.estado=ESTADO_CRUD.NINGUNO;
    }

    public ESTADO_CRUD getEstado() {
        return estado;
    }

    public void btnModificarHandler(ActionEvent actionEvent) {
         TipoSala actualizado= tsBean.update(registro);
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
        tsBean.delete(registro.getIdTipoSala());
        mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
        mensaje.setSummary("Registro eliminado con exito");
        facesContext.addMessage(null, mensaje);
        this.registro=null;
        this.estado=ESTADO_CRUD.NINGUNO;
    }




}
