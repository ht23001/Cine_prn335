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
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.TipoReservaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoReserva;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class frmTipoReserva implements Serializable {

    @Inject
    FacesContext facesContext;

    @Inject
    TipoReservaBean dataBean;

    ESTADO_CRUD estado;


    LazyDataModel<TipoReserva> modelo;


     @PostConstruct
    public void init(){
         estado=ESTADO_CRUD.MODIFICAR;
         modelo=new LazyDataModel<TipoReserva>() {

             @Override public String getRowKey(TipoReserva object) {
                 if (object != null && object.getIdTipoReserva() != null) {
                     return object.getIdTipoReserva().toString(); }
                 return null;
             }

             @Override
             public TipoReserva getRowData(String rowKey) {
                 if (rowKey != null && getWrappedData() != null) {
                     return getWrappedData().stream().filter(r -> rowKey.equals(r.getIdTipoReserva().toString())).findFirst().orElse(null); }
                 return null;
             }

             @Override
             public List<TipoReserva> getWrappedData() {
                 return (List<TipoReserva>) super.getWrappedData();
             }


             @Override
             public int count(Map<String, FilterMeta> map) {
                 try {

                     return  (int)dataBean.count();
                 }catch (Exception e){
                     e.printStackTrace();
                     ///TODO: Enviar mensaje de error de acceso
                 }
                 return 0;
             }

             @Override
             public List<TipoReserva> load(int desde, int max, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {


                 try {
                     if(desde>=0 && max>0){
                         return dataBean.findRange(desde,max);

                     }

                 }catch (Exception e){

                     e.printStackTrace();
                    ///TODO: enviar mensaje de error en el repositorio
                 }

                 return List.of();
             }
         };


    }

    TipoReserva registro;


    public LazyDataModel<TipoReserva> getModelo() {
        return modelo;

    }

    public void setModelo(LazyDataModel<TipoReserva> modelo) {
        this.modelo = modelo;
    }


    public TipoReserva getRegistro() {
        return registro;
    }

    public void setRegistro(TipoReserva registro) {
        this.registro = registro;
    }

    public void onRowSelect(SelectEvent<TipoReserva> event) {
        registro = event.getObject();
        estado = ESTADO_CRUD.MODIFICAR;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fila Seleccionada", "ID: " + registro.getIdTipoReserva())); }

    public void btnNuevoHandler(ActionEvent actionEvent){
        this.registro = new TipoReserva();
        this.registro.setIdTipoReserva(dataBean.obtenerMaxIdTipoReserva(registro)+1);
        this.registro.setActivo(true);
        this.estado=ESTADO_CRUD.CREAR;

    }

    public void btnGuardarHandler(ActionEvent actionEvent) {
        try {
            this.dataBean.create(registro);
            this.registro =null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro guardado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar el registro"));
            e.printStackTrace();
        }
        this.registro = null;
        this.estado = ESTADO_CRUD.MODIFICAR;
    }

    public void btnEliminarHandler(ActionEvent actionEvent){
        FacesMessage mensaje = new FacesMessage();
        dataBean.delete(registro.getIdTipoReserva());
        mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
        mensaje.setSummary("Registro eliminado con exito");
        facesContext.addMessage(null, mensaje);
        this.registro =null;
        this.estado=ESTADO_CRUD.MODIFICAR;
    }

    public void btnCancelarHandler(ActionEvent actionEvent){

        this.registro = null;
        this.estado=ESTADO_CRUD.MODIFICAR;
    }

    public void btnModificarHandler(ActionEvent actionEvent) {
        TipoReserva actualizado= dataBean.update(registro);
        FacesMessage mensaje= new FacesMessage();
        if(actualizado!=null){
            this.registro =null;
            this.estado=ESTADO_CRUD.MODIFICAR;
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            mensaje.setSummary("Registro modificado con exito");

        }else {
            mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            mensaje.setSummary("NO se pudo guardar el registro, revise datos");

        }
        facesContext.addMessage(null, mensaje);
    }

    public ESTADO_CRUD getEstado() {
        return estado;
    }

    public void setEstado(ESTADO_CRUD estado) {
        this.estado = estado;
    }
}
