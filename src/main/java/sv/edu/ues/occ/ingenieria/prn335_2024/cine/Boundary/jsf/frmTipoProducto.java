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
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.TipoProductoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.TipoReservaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoProducto;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoReserva;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class frmTipoProducto implements Serializable {

    @Inject
    FacesContext facesContext;

    @Inject
    TipoProductoBean tpBean;

    ESTADO_CRUD estado;

    LazyDataModel<TipoProducto> modelo;


    @PostConstruct
    public void init(){
        estado=ESTADO_CRUD.MODIFICAR;
        modelo=new LazyDataModel<TipoProducto>() {

            @Override
            public String getRowKey(TipoProducto object) {
                if (object != null && object.getIdTipoProducto() != null) {
                    return object.getIdTipoProducto().toString(); }
                return null;
            }

            @Override
            public TipoProducto getRowData(String rowKey) {
                if (rowKey != null && getWrappedData() != null) {
                    return getWrappedData().stream().filter(r -> rowKey.equals(r.getIdTipoProducto().toString())).findFirst().orElse(null); }
                return null;
            }

            @Override
            public List<TipoProducto> getWrappedData() {
                return (List<TipoProducto>) super.getWrappedData();
            }


            @Override
            public int count(Map<String, FilterMeta> map) {
                try {

                    return  (int)tpBean.Count();
                }catch (Exception e){
                    e.printStackTrace();
                    ///TODO: Enviar mensaje de error de acceso
                }
                return 0;
            }

            @Override
            public List<TipoProducto> load(int desde, int max, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {


                try {
                    if(desde>=0 && max>0){
                        return tpBean.findRange(desde,max);

                    }

                }catch (Exception e){

                    e.printStackTrace();
                    ///TODO: enviar mensaje de error en el repositorio
                }

                return List.of();
            }
        };


    }

    TipoProducto registro;


    public LazyDataModel<TipoProducto> getModelo() {
        return modelo;

    }

    public void setModelo(LazyDataModel<TipoProducto> modelo) {
        this.modelo = modelo;
    }

    public ESTADO_CRUD getEstado() {
        return estado;
    }

    public void setEstado(ESTADO_CRUD estado) {
        this.estado = estado;
    }

    public FacesContext getFacesContext() {
        return facesContext;
    }

    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }

    public TipoProducto getRegistro() {
        return registro;
    }

    public void setRegistro(TipoProducto registro) {
        this.registro = registro;
    }

    public void onRowSelect(SelectEvent<TipoProducto> event) {
        registro = event.getObject();
        estado = ESTADO_CRUD.MODIFICAR;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fila Seleccionada", "ID: " + registro.getIdTipoProducto())); }

    public void btnNuevoHandler(ActionEvent actionEvent){
        this.registro = new TipoProducto();
        if(tpBean.obtenerMaxIdTipoProducto(registro)==null){
            this.registro.setIdTipoProducto(1);
            this.registro.setActivo(true);
            this.estado=ESTADO_CRUD.CREAR;
        }else{
            this.registro.setIdTipoProducto(tpBean.obtenerMaxIdTipoProducto(registro)+1);
            this.registro.setActivo(true);
            this.estado=ESTADO_CRUD.CREAR;
        }


    }

    public void btnGuardarHandler(ActionEvent actionEvent) {
        try {
            this.tpBean.Create(registro);
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
        tpBean.Delete(registro.getIdTipoProducto());
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
        TipoProducto actualizado= tpBean.Update(registro);
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


}
